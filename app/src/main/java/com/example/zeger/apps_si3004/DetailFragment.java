package com.example.zeger.apps_si3004;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zeger on 25/03/17.
 */

public class DetailFragment extends Fragment {

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail,container,false);
        return v;
    }
}
