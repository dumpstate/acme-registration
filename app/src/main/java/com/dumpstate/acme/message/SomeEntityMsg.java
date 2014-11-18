package com.dumpstate.acme.message;

import android.text.TextUtils;
import android.util.Log;

import com.dumpstate.acme.model.SomeEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Message carrying the array of School objects.
 */
public class SomeEntityMsg implements Serializable {
    private static final String TAG = SomeEntityMsg.class.getSimpleName();

    private static final String OBJECTS = "objects";

    public SomeEntity[] mSomeEntities;

    /**
     * Retrieves the SchoolMsg from the JSON returned from ClassOwl API.
     */
    public static SomeEntityMsg fromJsonStr(final String jsonStr) {
        final SomeEntityMsg someEntityMsg = new SomeEntityMsg();
        if(!TextUtils.isEmpty(jsonStr)) {
            try {
                final JSONObject jObject = new JSONObject(jsonStr);
                final JSONArray schoolsArray = jObject.getJSONArray(OBJECTS);
                someEntityMsg.mSomeEntities = new SomeEntity[schoolsArray.length()];
                for(int i = 0; i < schoolsArray.length(); i++) {
                    someEntityMsg.mSomeEntities[i] = SomeEntity.fromJSON(schoolsArray.getJSONObject(i));
                }
            } catch (JSONException e) {
                Log.e(TAG, "Not a JSON format", e);
            }
        }
        return someEntityMsg;
    }

    public SomeEntityMsg() {}
    public SomeEntityMsg(final SomeEntity[] someEntities) {
        mSomeEntities = someEntities;
    }

    @Override
    public String toString() {
        final JSONArray jsonArray = new JSONArray();
        if(mSomeEntities != null) {
            for (SomeEntity someEntity : mSomeEntities) {
                jsonArray.put(someEntity);
            }
        }
        return jsonArray.toString();
    }
}