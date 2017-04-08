package com.example.zeger.apps_si3004;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zeger on 25/03/17.
 */

public class ListFragment extends Fragment {
    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // create view dari layout
        View v = inflater.inflate(R.layout.fragment_list,container,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DetailAtivity.class);
                startActivity(i);
            }
        });
        return v;
    }
}
