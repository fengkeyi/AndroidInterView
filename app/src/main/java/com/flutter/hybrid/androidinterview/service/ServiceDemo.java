package com.flutter.hybrid.androidinterview.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.flutter.hybrid.androidinterview.IMyAidlInterface;
import com.flutter.hybrid.androidinterview.bean.UserBean;

/**
 * TODO
 *  thread 新建new 就绪ready 运行running 阻塞lock 死亡dead
 *  启动服务优先级高于绑定服务
 *  先startService然后bindService，需要先unbindService再stopService才能停止服务
 *  先bindService再startService，也是需要unbindService在stopService才能停止服务
 *  开启服务不需要依托activity，bindService需要依赖activity
 */
public class ServiceDemo extends Service{


    private String mData;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mData = intent.getStringExtra("data");
        Log.i("FKY","service onBind "+ mData);
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mData = intent.getStringExtra("data");
        Log.i("FKY","service "+ mData);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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

    /**
     * TODO
     *  接收服务返回的数据
     */
    public interface ICallback{
        void onDataChange(String data);
    }

    /************AIDL start***********************/
    private UserBean userBean;

    public class AidlBinder extends IMyAidlInterface.Stub{


        @Override
        public UserBean getUser() throws RemoteException {
            return userBean;
        }

        @Override
        public void setUser(UserBean user) throws RemoteException {
            userBean = user;
        }
    }
    /************AIDL end***********************/


}
