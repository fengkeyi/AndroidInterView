package com.flutter.hybrid.androidinterview.brocast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class LocalBrocastDemo extends BroadcastReceiver {


    public static void sendLocalBrocast(Activity activity) {
        LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent("action_send_demo"));
        LocalBroadcastManager.getInstance(activity).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        },new IntentFilter("action_send_demo"));
    }

    public static void registerReceiver(Activity activity,BroadcastReceiver receiver, IntentFilter intentFilter) {
        activity.registerReceiver(receiver,intentFilter);
    }

    public static void unregisterReceiver(Activity activity, BroadcastReceiver receiver) {
        activity.unregisterReceiver(receiver);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
