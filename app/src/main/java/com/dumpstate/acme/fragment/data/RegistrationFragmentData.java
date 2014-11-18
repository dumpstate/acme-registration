package com.dumpstate.acme.fragment.data;

import android.os.Bundle;
import android.util.Log;

import com.dumpstate.acme.model.SomeEntity;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * RegistrationFragmentData holder - fragment should save state via this class.
 */
public class RegistrationFragmentData {
    private static final String TAG = RegistrationFragmentData.class.getSimpleName();

    private static final String ARG_ENTITY = "reg_entity";
    private static final String ARG_USER_TYPE = "reg_user_type";
    private static final String ARG_FIRST_NAME = "reg_first_name";
    private static final String ARG_LAST_NAME = "reg_last_name";
    private static final String ARG_EMAIL = "reg_email";
    private static final String ARG_PASSWORD = "reg_password";
    private static final String ARG_ENTITIES = "reg_schools";

    /**
     * Factory method, for making an RegistrationFragmentData out of
     * bundle (e.g. savedInstanceState).
     */
    public static RegistrationFragmentData get(final Bundle bundle) {
        final RegistrationFragmentData data = new RegistrationFragmentData();

        if(bundle != null) {
            data.mEntity = bundle.getInt(ARG_ENTITY);
            data.mUserType = bundle.getInt(ARG_USER_TYPE);
            data.mFirstName = bundle.getString(ARG_FIRST_NAME);
            data.mLastName = bundle.getString(ARG_LAST_NAME);
            data.mEmail = bundle.getString(ARG_EMAIL);
            data.mPassword = bundle.getString(ARG_PASSWORD);
            data.mSomeEntities = (SomeEntity[])bundle.getSerializable(ARG_ENTITIES);
        }

        return data;
    }

    /**
     * Appending all the fields data into bundle.
     */
    public Bundle appendToBundle(final Bundle bundle) {
        Assert.assertNotNull(bundle);

        bundle.putInt(ARG_ENTITY, mEntity);
        bundle.putInt(ARG_USER_TYPE, mUserType);
        bundle.putString(ARG_FIRST_NAME, mFirstName);
        bundle.putString(ARG_LAST_NAME, mLastName);
        bundle.putString(ARG_EMAIL, mEmail);
        bundle.putString(ARG_PASSWORD, mPassword);
        bundle.putSerializable(ARG_ENTITIES, mSomeEntities);

        return bundle;
    }

    public JSONObject toJSON() {
        final JSONObject jObject = new JSONObject();

        try {
            jObject.put(ARG_ENTITY, mEntity);
            jObject.put(ARG_USER_TYPE, mUserType);
            jObject.put(ARG_FIRST_NAME, mFirstName);
            jObject.put(ARG_LAST_NAME, mLastName);
            jObject.put(ARG_EMAIL, mLastName);
            jObject.put(ARG_PASSWORD, mPassword);
            jObject.put(ARG_ENTITIES, mSomeEntities);
        } catch(JSONException e) {
            Log.e(TAG, "JSONException", e);
        }

        return jObject;
    }

    @Override
    public String toString() {
        return toJSON().toString();
    }

    public int mEntity = -1;
    public int mUserType = -1;
    public String mFirstName;
    public String mLastName;
    public String mEmail;
    public String mPassword;
    public SomeEntity[] mSomeEntities;
}