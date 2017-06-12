package net.trwcomics.trwvisualnovel;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Activity fullScreenActivity = Robolectric.setupActivity(FullscreenActivity.class);
    }
}
//
//class StoryEventHandlerTest{
//
//    @Test
//    fun robolectricIsSetupCorrectly(){
//        var fullScreenActivity = Robolectric.setupActivity(FullscreenActivity::class.java)
//    }
//    val sampleJSON =JSONObject("{ \"event0\": { \"scenes\": { \"*\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": { \"scenes\": { \"*1-3\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ], \"*4\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": \"\" }")
////    val activity = Robolectric.setupActivity(FullscreenActivity::class.java)
////    var subject = StoryEventHandler(activity = activity)
//    @Before
//    fun setup(){
//
//    }
//
//    @Test
//    fun storyEventHandler_isInitializedCorrectly() {
//        assertNotNull("")
//    }
//
//    @Test
//    fun givenIcallLoadJSON_ThenAJSONObjectShouldBeReturned() {
//    val sampleJSON =JSONObject("{ \"event0\": { \"scenes\": { \"*\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": { \"scenes\": { \"*1-3\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ], \"*4\": [ { \"speaker\": \"Grant\", \"text\": \"What!\", \"sfx\": \"shake\", \"left_sprite\": \"\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\", \"music_file\": \"\", \"music_start\": true, \"music_end\": false }, { \"speaker\": \"Grant\", \"text\": \"How can this watch be so cheap?\", \"left_sprite\": \"sprite1\", \"right_sprite\": \"\", \"center_sprite\": \"\", \"background\": \"background1\" } ] } }, \"event2\": \"\" }")

//        var eventNumber = 0
//        var decisionString = "*2"
//        var expectedEvent = sampleJSON.getJSONObject("event0").getJSONObject("scenes").getJSONObject("*1-3")
//
////        assertNotNull(subject.loadJSON("events.json"))
//
//    }


