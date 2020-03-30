package com.flutter.hybrid.androidinterview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Button;

import com.flutter.hybrid.androidinterview.R;
import com.flutter.hybrid.androidinterview.service.ServiceDemo;

/**
 *  1 activity 4 种形态
 *  Active：Activity处于栈顶，可见，能和用户进行交互
 *  Paused：可见但是不可交互（被非全屏的Activity挡住，正常情况不会被回收，只有当系统内存不足的时候会被回收）
 *  Stoped：不可见（一个Activity被另一个Activity完全覆盖，不是被透明的Activity覆盖）（在内存足够的时候，它的数据和成员变量都是被保存着的，当内存不足的时候，会被系统回收掉）
 *  Killed：系统回收掉
 *
 */
public class MainActivity extends AppCompatActivity {

    private ServiceDemo.MyBinder binder;

    private Handler uiHandler;

    private Button button;

    private RecyclerView recyclerView;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (ServiceDemo.MyBinder) iBinder;
            binder.getService().setCallback(new ServiceDemo.ICallback() {
                @Override
                public void onDataChange(final String data) {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            button.setText(data);
                        }
                    });
                }
            });
            binder.setData("UPDATE");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }



    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

}
