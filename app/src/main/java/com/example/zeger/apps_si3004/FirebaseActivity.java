package com.example.zeger.apps_si3004;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by zeger on 15/04/17.
 */

public class FirebaseActivity extends AppCompatActivity{

    private class FirebaseTokenTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            //FirebaseApp.initializeApp(getApplicationContext());
            //String token = FirebaseInstanceId.getInstance().getToken();

            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            String token = null;
            try {
                token = instanceID.getToken("646405248384",
                        GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

                Log.d("Firebase","token="+token);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new FirebaseTokenTask().execute();
    }
}
