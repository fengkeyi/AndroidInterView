package com.flutter.hybrid.androidinterview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flutter.hybrid.androidinterview.R;
import com.flutter.hybrid.androidinterview.okhttp.OkhttpDemo;
import com.flutter.hybrid.androidinterview.retrofit.RetrofitDemo;
import com.flutter.hybrid.androidinterview.rxjava.RxjavaDemo;
import com.flutter.hybrid.androidinterview.service.ServiceDemo;

/**
 * TODO
 *  一、activity是与用户交互的接口
 *  二、Anderoid系统是以activity栈的形式来管理activity的
 *  1 activity 4 种形态
 *  Active：Activity处于栈顶，可见，能和用户进行交互
 *  Paused：可见但是不可交互（被非全屏的Activity挡住，正常情况不会被回收，只有当系统内存不足的时候会被回收）
 *  Stoped：不可见（一个Activity被另一个Activity完全覆盖，不是被透明的Activity覆盖）（在内存足够的时候，它的数据和成员变量都是被保存着的，当内存不足的时候，会被系统回收掉）
 *  Killed：系统回收掉
 *  扩展 ActivityThread ContextImpl ContextWrap
 *  异常情况：系统配置发生变化、内存不足，如屏幕方便发发生改变
 *  异常终止调用 onSaveInstanceState 恢复 onRestoreInstanceState->把bundle交给onCreate参数
 *  启动模式：standard singleTop
 *  singleTask singleInstance 根据taskAffinity查询是否存在任务栈，没有则创建
 *  singleInstance 独享一个任务栈，应用场景：呼叫来电
 */
public class MainActivity extends AppCompatActivity implements FragmentDemo.IFragment {

    private String TAG = "MainActivity";

    private ServiceDemo.MyBinder binder;

    /**
     * TODO
     *  Message MessageQueue Looper Handler
     *  threadLocal.get Looper.prepare Looper.myLooper
     *  ThreadLocal
     *  变量，线程局部变量，同一个 ThreadLocal 所包含的对象，在不同的 Thread 中有不同的副本。这里有几点需要注意：
     *  因为每个 Thread 内有自己的实例副本，且该副本只能由当前 Thread 使用。这是也是 ThreadLocal 命名的由来。
     *  既然每个 Thread 有自己的实例副本，且其它 Thread 不可访问，那就不存在多线程间共享的问题。
     *  ThreadLocal 提供了线程本地的实例。它与普通变量的区别在于，每个使用该变量的线程都会初始化一个完全独立的实例副本。
     *  ThreadLocal 变量通常被private static修饰。当一个线程结束时，它所使用的所有 ThreadLocal 相对的实例副本都可被回收。
     *
     */
    private Handler uiHandler;

    private Button button;

    private RecyclerView recyclerView;

    /**
     * TODO
     * acitivity 与Service通讯 ServiceConnection
     */
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
        uiHandler = new Handler();
        button = findViewById(R.id.btn_helloworld);
        bindService(new Intent(this, ServiceDemo.class), serviceConnection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "THREADID ............."+android.os.Process.myTid()+" "+android.os.Process.myPid());
//        new RetrofitDemo().simple();
        new RxjavaDemo().internalRequest();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }


    /**
     * @param outState
     */
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

    @Override
    public void doSomething(String str) {

    }

    @Override
    public String getTitleFromAcitity(){
        return "Activity TITLE";
    }

    public void onClick(View view) {
        startActivity(new Intent("com.cruzr.map.homepage"));
    }
}

/**
 * activity 与 Fragment 通讯
 * bundle
 * 接口回调
 */
class FragmentDemo extends Fragment implements View.OnClickListener {
    //TODO 2
    private IFragment listener;
    String value;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IFragment) {
            listener = (IFragment) activity;
        }
        value = getArguments().getString("parameter_from_activity");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        String title = listener.getTitleFromAcitity();
        listener.doSomething("fragment is clicked.");
    }

    interface IFragment{
        void doSomething(String stringFromFragment);

        String getTitleFromAcitity();
    }

}
