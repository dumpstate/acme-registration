package com.dumpstate.acme.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.dumpstate.acme.message.Constants;
import com.dumpstate.acme.message.SomeEntityMsg;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * IntentService for retrieving UI dependent data.
 */
public class UiDataFeedIntentService extends IntentService {
    private static final String TAG = UiDataFeedIntentService.class.getSimpleName();

    public static final String REST_API_URL = "http://www.mocky.io/v2/546b73111dc00b22152cf186";

    private final LocalBroadcastManager mBroadcastManager;

    public UiDataFeedIntentService() {
        super(UiDataFeedIntentService.class.getSimpleName());
        mBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    /**
     * Retrieves string with data under given http url.
     */
    private static String get(final String url) {
        InputStream is = null;
        try {
            final HttpClient httpClient = new DefaultHttpClient();
            final HttpResponse response = httpClient.execute(new HttpGet(url));
            is = response.getEntity().getContent();
            if(is != null) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                final StringBuilder sb = new StringBuilder();
                String line = null;
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch(IOException e) {
            Log.e(TAG, "Failed to retrieve: " + url);
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    Log.e(TAG, "Failed to close stream.");
                }
            }
        }
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent.hasExtra(Constants.MSG_TYPE)) {
            switch(intent.getIntExtra(Constants.MSG_TYPE, -1)) {
                case Constants.MSG_GET_DATA: {
                    // get the schools data, and broadcast intent with result
                    final Intent i = new Intent(Constants.ACTION);
                    i.putExtra(
                            Constants.DATA,
                            SomeEntityMsg.fromJsonStr(get(REST_API_URL)));
                    mBroadcastManager.sendBroadcast(i);
                    break;
                }
                default: {
                    Log.w(TAG, "Unknown message: " + intent);
                }
            }
        }
    }
}
