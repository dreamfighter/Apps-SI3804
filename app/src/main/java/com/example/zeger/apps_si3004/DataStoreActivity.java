package com.example.zeger.apps_si3004;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zeger.apps_si3004.database.SqliteMyHelper;
import com.example.zeger.apps_si3004.entity.Mahasiswa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by zeger on 01/04/17.
 */

public class DataStoreActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datastore);

        final EditText ed = (EditText)findViewById(R.id.in_text);
        Button but =  (Button) findViewById(R.id.save_but);
        Button butO =  (Button) findViewById(R.id.open_but);

        SqliteMyHelper sqliteMyHelper = new SqliteMyHelper(this);
        //sqliteMyHelper.insert("113060037","komang ardisa");


        List<Mahasiswa> listMhs = sqliteMyHelper.selectMahasiswa();

        for(Mahasiswa mhs:listMhs){
            Log.d("DATABASE","id="+mhs.getId());
            Log.d("DATABASE","nim="+mhs.getNim());
            Log.d("DATABASE","nama="+mhs.getNama());
        }



        /*
        final SharedPreferences sharedPref = getSharedPreferences("session-si3804", 0);
        final SharedPreferences.Editor editor = sharedPref.edit();

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("userId",ed.getText().toString());

                editor.commit();
                //File f = new File(Environment.getExternalStorageDirectory()+"/"+"datastore.txt");

                //OutputStreamWriter ow = new OutputStreamWriter()
            }
        });

        butO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = sharedPref.getString("userId","not set");

                Log.d("test","test=>"+userId);
                ed.setText(userId);
            }
        });
        */
    }

    public void saveFile(final EditText ed){
        try {
            String txt = ed.getText().toString();
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(DataStoreActivity.this.openFileOutput("datastore.txt",
                            DataStoreActivity.this.MODE_PRIVATE));
            outputStreamWriter.write(txt);

            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile(final EditText ed){
        try {
            InputStream is = DataStoreActivity.this.openFileInput("datastore.txt");
            String line ="";
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String txt = sb.toString();
            ed.setText(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
