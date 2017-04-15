package com.example.zeger.apps_si3004;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.zeger.apps_si3004.adapter.AdapterListView;
import com.example.zeger.apps_si3004.entity.Contact;
import com.example.zeger.apps_si3004.service.JsonIntentService;

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
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeger on 07/04/17.
 */

public class AsyncTaskActivity extends AppCompatActivity{

    // json data url
    private String url = "http://dreamfighter.id/android/data.json";

    private AdapterListView adapterListView;

    private class DownloadJsonTask extends AsyncTask<String, Integer, String>{


        @Override
        protected String doInBackground(String... params) {

            return requestJson(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            decodeJson(s);
        }
    }

    public String requestJson(String urlWeb){
        StringBuilder sb = new StringBuilder();
        try {

            // conect to server
            URL url = new URL(urlWeb);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // get data
            InputStream in = new BufferedInputStream(conn.getInputStream());


            Reader r = new InputStreamReader(conn.getInputStream());

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
            adapterListView.refresh(contacts);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class DownloadJsonReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent dataJson) {
            Bundle bundle = dataJson.getExtras();

            String json = bundle.getString("json");

            decodeJson(json);
        }
    }

    private DownloadJsonReciever downloadJsonReciever = new DownloadJsonReciever();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.async_task_activity);

        ListView listView = (ListView)findViewById(R.id.contact_listview);

        adapterListView = new AdapterListView(this);

        listView.setAdapter(adapterListView);

        //requestJson(url);


        IntentFilter intenFilter = new IntentFilter("DOWNLOAD_JSON");

        registerReceiver(downloadJsonReciever,intenFilter);


        Intent intent = new Intent(this, JsonIntentService.class);

        startService(intent);

        //DownloadJsonTask task = new DownloadJsonTask();
        //task.execute(url);

        //decodeJson(requestJson(url));

    }
}
