package com.flutter.hybrid.androidinterview.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/***
 * RealCall 拦截器 dispatcher 线程池 队列（running ready） promoteCalls
 */
public class OkhttpDemo {


    public void simple() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("www.abc.png").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

}
