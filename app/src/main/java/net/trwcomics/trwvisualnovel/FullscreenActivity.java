package net.trwcomics.trwvisualnovel;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import net.trwcomics.trwvisualnovel.views.RpgTextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 30000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private void setSplashButtons(){
        findViewById(R.id.continue_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.splash_screen).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.continue_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //                Show dialog to confirm
//                Reset event number and decision string
                findViewById(R.id.splash_screen).setVisibility(View.GONE);
            }
        });;


    }


    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        int thirdOfScreen = getWindowManager().getDefaultDisplay().getWidth()/3;

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        (findViewById(R.id.sprite_left)).getLayoutParams().width = thirdOfScreen;
        (findViewById(R.id.sprite_right)).getLayoutParams().width = thirdOfScreen;


//        // Set up the user interaction to manually show or hide the system UI.
//        mContentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toggle();
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        loadEvent();
        setSplashButtons();
    }

    public void fadeView(View imageView, boolean fadeIn, int duration){
        Animation animation;
        if(fadeIn){
            animation = new AlphaAnimation(0, 1);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.setDuration(duration);
        }
        else{
            animation = new AlphaAnimation(1, 0);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(duration);
        }
        imageView.setAnimation(animation);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    @Override
    protected void onResume() {
        super.onResume();
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    //    Initial scene load
    private void loadEvent() {
        updateEvent(0);
        handleTap();
    }

    private int scene = 0;
    private StoryEventHandler storyEventHandler = new StoryEventHandler(this);

    private void handleTap() {
        findViewById(R.id.speech_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MYTEST", "Clicked" + getCurrentScenes());
                scene = scene + 1;
                int numberOfScenes = getCurrentScenes().length() - 1;
                if (numberOfScenes < scene) {
                    scene = 0;
                }
                updateEvent(scene);
            }
        });
    }

    private void updateEvent(int sceneNumber) {
        JSONArray scenes = getCurrentScenes();
        try {
            setBackground(scenes.getJSONObject(sceneNumber).getString("background"));
            setSpeaker(scenes.getJSONObject(sceneNumber).getString("speaker"));
            setSpeechText(scenes.getJSONObject(sceneNumber).getString("text"));
            setSprites("left", scenes.getJSONObject(sceneNumber).getString("left_sprite"));
            setSprites("center", scenes.getJSONObject(sceneNumber).getString("center_sprite"));
            setSprites("right", scenes.getJSONObject(sceneNumber).getString("right_sprite"));
            setSpeakerDirection(scenes.getJSONObject(sceneNumber).getString("speaker_location"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONArray getCurrentScenes() {
        //        Load event number and decision path from share pref
        return storyEventHandler.getEvent(0, "*");
    }

    private void setSpeaker(String speaker) {
        TextView speakerTextField = ((TextView) findViewById(R.id.speech_speaker_text_field));
        fadeView(speakerTextField, false, 0);
        fadeView(speakerTextField, true, 300);
        speakerTextField.setText(speaker);
    }

    private void setSpeechText(String sheechContent) {
        ((RpgTextView) findViewById(R.id.speech_content_text_field)).setText(sheechContent);
    }

    private void setSpeakerDirection(String speakerDirection){
        TextView speechTextView = (TextView) findViewById(R.id.speech_speaker_text_field);
        if(speakerDirection.toLowerCase().equals("left")){
            speechTextView.setGravity(Gravity.LEFT);
            speechTextView.setPadding(50,10,0,0);
        }
//        else if(speakerDirection.toLowerCase().equals("right")){
//            speechTextView.setPadding(0,10,50,0);
//            speechTextView.setGravity(Gravity.RIGHT);
//        }
//        else {
//            speechTextView.setPadding(0,10,0,0);
//            speechTextView.setGravity(Gravity.CENTER);
//
//        }
    }

    private void setSprites(String position, String resource) {
        position = position.toLowerCase();

        int containerId;
        if (position == "left") {
            containerId = R.id.sprite_left;
        } else if (position == "right") {
            containerId = R.id.sprite_right;

        } else {
            containerId = R.id.sprite_center;
        }
        ImageView sprite = (ImageView) findViewById(containerId);
        if (!resource.isEmpty()) {
            int id = getResources().getIdentifier(resource, "drawable", getPackageName());
            Drawable drawable = getDrawable(id);
            fadeView(sprite, true, 300);
            sprite.setImageDrawable(drawable);
        } else {
            fadeView(sprite, false, 300);
            sprite.setImageDrawable(getDrawable(R.drawable.empty_m));
        }


    }

    private void setBackground(String resource) {
        int id = getResources().getIdentifier(resource, "drawable", getPackageName());
        Drawable drawable = getDrawable(id);
        View background = findViewById(R.id.trw_background);
        background.setBackground(drawable);
    }
}
