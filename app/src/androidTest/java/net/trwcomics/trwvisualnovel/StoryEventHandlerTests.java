package net.trwcomics.trwvisualnovel;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class StoryEventHandlerTests extends ActivityInstrumentationTestCase2<FullscreenActivity> {
    StoryEventHandler subject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
       subject  = new StoryEventHandler(getActivity());
    }
    public StoryEventHandlerTests() {
        super(FullscreenActivity.class);
    }

    public void testIsConfiguredCorrectly(){
        Activity activity = getActivity();
        assertNotNull(activity);
    }

    public void testLoadJsonFileReturnsAJsonObject() throws JSONException {
            JSONObject sampleJSON = new JSONObject("{ \"event0\": { \"scenes\": { \"*\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": { \"scenes\": { \"*1-3\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ], \"*4\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": \"\" }");
        JSONObject loadedJSON = subject.loadJSON("events.json");
        assertNotNull(loadedJSON);
        assertEquals(JSONObject.class.getSimpleName(), subject.loadJSON("events.json").getClass().getSimpleName());
        assertEquals(sampleJSON.getJSONObject("event0").getJSONObject("scenes").get("*").toString(), loadedJSON.getJSONObject("event0").getJSONObject("scenes").get("*").toString());
    }

    public void testgetEventReturnsAJsonObject() throws JSONException {
        JSONObject sampleJSON = new JSONObject("{ \"event0\": { \"scenes\": { \"*\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": { \"scenes\": { \"*1-3\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ], \"*4\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": \"\" }");
        JSONObject loadedJSON = subject.loadJSON("events.json");
        assertNotNull(loadedJSON);
        assertEquals(JSONObject.class.getSimpleName(), subject.loadJSON("events.json").getClass().getSimpleName());
        assertEquals(sampleJSON.getJSONObject("event0").getJSONObject("scenes").get("*").toString(), subject.getEvent(0, "*").toString());
    }


}
