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

/**
 * TODO
 *   binder 理解
 *   link https://mp.weixin.qq.com/s?__biz=MzIwMTAzMTMxMg==&mid=2649496205&idx=2&sn=9307b2d4937ad45dbb3d7dc7258561d8&chksm=8eec9172b99b1864e5305ac3c11b913720a28cb3a4d1149794f45be3df3939bbab62d5ec3c29&mpshare=1&scene=1&srcid=&sharer_sharetime=1588904213656&sharer_shareid=a2256cd305676666fdd17905ad0d52b9&key=bb0ddf399b9bf75c9f7868c5a4ba59dd827f90f98e001f1ca08400e2da9cdfd8f47bfcfea3dddafa6b1c1f8b084738cf647f9fb71bd616e199c93b0ebffb1589c9b15d393151bcd2ad2adbfba3e165a3&ascene=1&uin=Mjg0MzYyOTU4Mw%3D%3D&devicetype=Windows+7&version=62070158&lang=zh_CN&exportkey=AWwPIM9A9j3YO%2FyQfqmGabg%3D&pass_ticket=IaAh9BHljDOJV4wWmIMG8XkOTOWekVAnjMiA3p9Am86tdC4M%2Btwn%2BzCFo4MM2dqv
 *
 */
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
