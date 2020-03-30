package com.flutter.hybrid.androidinterview.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


/**
 *
 */
public class ServiceDemo extends Service{


    private String mData;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mData = intent.getStringExtra("data");
        Log.i("FKY","service onBind "+ mData);
        return new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mData = intent.getStringExtra("data");
        Log.i("FKY","service "+ mData);
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder{
        public String getData() {
            return mData;
        }

        public void setData(String data) {
            mData = data;
            iCallback.onDataChange("new Data:"+mData);
        }

        public ServiceDemo getService() {
            return ServiceDemo.this;
        }

    }

    private ICallback iCallback;

    public void setCallback(ICallback iCallback) {
        this.iCallback = iCallback;
    }

    public ICallback getCallback() {
        return iCallback;
    }

    public interface ICallback{
        void onDataChange(String data);
    }

}
