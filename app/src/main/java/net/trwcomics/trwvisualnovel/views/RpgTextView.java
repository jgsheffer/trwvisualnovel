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
    public RpgTextView(Context context) {
        super(context);
    }

    public RpgTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText("", type);
        setTextOverTime(text, textSpeed);
    }

//    Speed is the number of milliseconds between characters
    public void setTextOverTime(final CharSequence text, final int speed){
        int nextCharSpeed = speed;
        final Handler handler = new Handler();
                for (int i = 0; i < text.length(); i++) {
                    final char c = text.charAt(i);

                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            append("" + c);
                        }
                    };

                    handler.postDelayed(runnable, nextCharSpeed);
                    nextCharSpeed=nextCharSpeed+speed;
                }

        }
}
