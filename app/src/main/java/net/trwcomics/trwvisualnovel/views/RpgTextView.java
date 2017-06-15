package net.trwcomics.trwvisualnovel.views;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jared on 6/14/2017.
 */

public class RpgTextView extends android.support.v7.widget.AppCompatTextView {
    public int textSpeed = 30;
    private boolean isLastCharacterDisplayed = false;
    private final Handler handler = new Handler();
    int numberOfCharactersDisplayed = 0;
    BufferType bufferType;

    public RpgTextView(Context context) {
        super(context);
    }

    public RpgTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        bufferType = type;
        super.setText("", type);
        isLastCharacterDisplayed = false;
        setTextOverTime(text, textSpeed);
    }

    //    Speed is the number of milliseconds between characters
    public void setTextOverTime(final CharSequence text, final int speed) {
        numberOfCharactersDisplayed = 0;
        int nextCharSpeed = speed;
        for (int i = 0; i < text.length(); i++) {
            final char c = text.charAt(i);
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    numberOfCharactersDisplayed++;
                    append("" + c);
                    if(numberOfCharactersDisplayed == text.length()){
                        isLastCharacterDisplayed = true;
                    }
                    else{
                        isLastCharacterDisplayed = false;
                    }
                }

            };

            handler.postDelayed(runnable, nextCharSpeed);
            nextCharSpeed = nextCharSpeed + speed;
        }


    }


    public boolean forceLoad(CharSequence text) {
            boolean forcedFinish = false;
            if(!isLastCharacterDisplayed) {
                handler.removeCallbacksAndMessages(null);
                super.setText(text, bufferType);
                isLastCharacterDisplayed = true;
                forcedFinish = true;
            }
            return forcedFinish;
    }
}
