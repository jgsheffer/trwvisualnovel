package net.trwcomics.trwvisualnovel;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by Jared on 6/12/2017.
 */

public class StoryEventHandler {
    private Activity activity;

    public StoryEventHandler(Activity activity) {
        this.activity = activity;
    }

    public JSONArray getEvent(int eventNumber, String decisionPath) {
        JSONObject json = loadJSON("events.json");
        JSONArray eventScenes = null;
        try {
            eventScenes = json.getJSONObject("event"+eventNumber).getJSONObject("scenes").getJSONArray(decisionPath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventScenes;
    }

    public JSONObject loadJSON(String fileName) {
        String json_string;
        try {
            InputStream input = activity.getApplicationContext().getAssets().open(fileName);

            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            json_string = new String(buffer);
            return new JSONObject(json_string);
        } catch (Exception e) {
            Log.e("TRWERROR", "Failed to load events JSON");
            return null;
        }
    }


}
