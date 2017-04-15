package com.example.zeger.apps_si3004.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by zeger on 15/04/17.
 */

public class GoogleMSReceiver extends WakefulBroadcastReceiver {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d("GCM", "Receive message from server");
        Log.d("GCM", "data:"+bundle);
    }
}
