package com.flutter.hybrid.androidinterview.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.flutter.hybrid.androidinterview.IMyAidlInterface;
import com.flutter.hybrid.androidinterview.bean.UserBean;

public class AidlClient {

    private IMyAidlInterface mAidl;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAidl = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void unRegister(Activity activity) {
        activity.unbindService(serviceConnection);
    }

    public UserBean getUser() throws RemoteException {
        if (mAidl == null) {
            throw new NullPointerException("register service first!");
        }
        return mAidl.getUser();
    }

    public void setUser(UserBean user) throws RemoteException {
        if (mAidl == null) {
            throw new NullPointerException("register service first!");
        }
        mAidl.setUser(user);
    }


    public static AidlClient getInstance() {
        return Instance.INSTANCE;
    }

    public void register(Activity activity) {
        Intent intent = new Intent("com.aidl.service.demoservice");
        activity.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }



    private static class Instance{
        private static final AidlClient INSTANCE = new AidlClient();
    }

}
