package com.cn.demo.jatpack.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.cn.demo.jatpack.service.DetectionService;

public class App extends Application {

    private static Context mAppContext;

    public static Context getAppContext() {
        return mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        startCoreService();
    }

    private void startCoreService() {
        startService(new Intent(this, DetectionService.class));
    }
}
