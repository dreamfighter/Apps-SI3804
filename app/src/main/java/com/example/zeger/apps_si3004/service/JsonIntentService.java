package com.example.zeger.apps_si3004.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.zeger.apps_si3004.entity.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeger on 08/04/17.
 */

public class JsonIntentService extends IntentService{

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private String url = "http://dreamfighter.id/android/data.json";


    public JsonIntentService() {
        super("JsonIntentService");
    }

    public JsonIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        decodeJson(requestJson(url));
    }

    public String requestJson(String urlWeb){
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlWeb);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Reader r = new InputStreamReader(conn.getInputStream());
            String line;


            char[] chars = new char[4*1024];
            int len;
            while((len = r.read(chars))>=0) {
                sb.append(chars, 0, len);
            }
            Log.d("JSON",sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void decodeJson(String json){
        try {
            JSONObject jObj = new JSONObject(json);
            JSONArray jArr = jObj.getJSONArray("data");

            List<Contact> contacts = new ArrayList<Contact>();

            for(int i=0;i<jArr.length();i++){
                JSONObject c = jArr.getJSONObject(i);
                Contact cnt = new Contact();

                cnt.setNama(c.getString("name"));
                cnt.setNoHp(c.getString("phone"));
                cnt.setAvatarUrl(c.getString("avatarUrl"));

                contacts.add(cnt);
            }
            //adapterListView.refresh(contacts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
