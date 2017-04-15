package com.example.zeger.apps_si3004;

import android.content.ClipData;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = (ListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.list_fragment);
        //Fragment detailFragment = (Fragment)getSupportFragmentManager().findFragmentById(R.id.detail);

        Button button = (Button) findViewById(R.id.button_add_fragment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment dFragment = new DetailFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.framelayout,dFragment).commit();
            }
        });

        if(listFragment!=null){

        }else{
            //Intent i = new Intent(this,ListActivity.class);
            //startActivity(i);
            listFragment = new ListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout,listFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.in_text){
            //session.logout();
        }
        return super.onOptionsItemSelected(item);
    }
}
