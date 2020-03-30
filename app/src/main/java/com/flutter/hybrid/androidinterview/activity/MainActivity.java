package com.flutter.hybrid.androidinterview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.flutter.hybrid.androidinterview.R;
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
 *
 */
public class MainActivity extends AppCompatActivity implements FragmentDemo.IFragment {

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

    public void onClick(View view) {
        startActivity(new Intent("com.cruzr.map.homepage"));
    }
}

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
        listener.doSomething("fragment is clicked.");
    }

    interface IFragment{
        void doSomething(String stringFromFragment);
    }

}
