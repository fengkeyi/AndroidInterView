package com.cn.demo.jatpack.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.cn.demo.jatpack.R;
import com.cn.demo.jatpack.widget.WarnDialog;


public class DetectionService extends Service {

    private String TAG = "DetectionService";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WarnDialog.getInstance().setContent(R.string.app_name)
                .setDialogTitle(R.string.app_name).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
