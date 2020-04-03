package com.flutter.hybrid.androidinterview.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 * TODO
 *  一、RealCall 拦截器 dispatcher 线程池 队列（running ready） promoteCalls
 *  同步请求：
 *  RealCall只能被执行一次，会将RealCall通过请求分发器Dispatcher放到runningSyncCalls同步请求队列中
 *  调用拦截器链执行请求，最后通过Dispatcher的finished方法将请求从队列中移除
 *  异步请求：
 *  将Callback封装成RealCall.AsyncCall并放置到异步请求对立中，毁掉方法在子线程中执行
 *  二、线程池： 核心线程数量、最大线程数据量、等待任务队列、空闲时间、失败策略
 *  1.获取当前正在运行线程数是否小于核心线程池，是则新创建一个线程执行任务，否则将任务放到任务队列中
 *  2.当前核心线程池中全部线程都在运行workerCountOf(c) >= corePoolSize，所以此时将线程放到任务队列中
 *  3.插入队列不成功，且当前线程数数量小于最大线程池数量，此时则创建新线程执行任务，创建失败抛出异常
 *  三、Dispatcher
 *  readyAsyncCalls、runningAsyncCalls、runningSyncCalls
 *  promoteCalls()、runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost
 */
public class OkhttpDemo {

    private String TAG = "OkhttpDemo";

    public void simple() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://flutterchina.club/opensource.html").build();
        Call call = client.newCall(request);
        //TODO 异步
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "TESTuid:"+android.os.Process.myUid() + " tid:" + android.os.Process.myTid());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "TESTuid:"+android.os.Process.myUid() + " tid:" + android.os.Process.myTid());
            }
        });
        //TODO 同步
//        try {
//            Response response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
