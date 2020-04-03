package com.flutter.hybrid.androidinterview.retrofit;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.schedulers.Schedulers;

/****
 * TODO
 *  数据解析器（GsonFactory）
 *  网络请求适配器（RxjavaCallAdapter)
 *  回调执行器
 *  工厂模式 Builder模式
 *  ServiceMethod ParameterHander
 *  Rotrofit
 *  1、解析网络请求接口的注解配置网络请求参数
 *  2、动态代理生成网络请求对象（Proxy.newProxyInstance）
 *  3、网络请求适配器将网络请求对象进行平台适配（callAdapterFactories）
 *  4、网络请求执行器（CallFactory）发送网络请求
 *  5、数据转换器解析服务器返回的数据（converterFactories）
 *  6、回到执行器切换线程（callbackExecutor）
 *  7、主线程处理结果
 *  ServiceMethod：包含了网络请求对象所有的信息
 *  1、callAdapter：根据方法注解和返回值类型得到网络请求适配器
 *  2、callFactory：网络请求执行器工厂，默认是okhttp3.Call.Factory
 *  3、responseConverter：根据方法注解和返回值类型得到数据转成工厂适配器（Converter<ResponseBody, R>）
 *  4、根据方法标注对ServiceMethod的域进行赋值（GET，POST，DELETE,...）
 *  5、parameterHandlers：对接口方法的标注进行解析，得到一个ParameterHandler<?>[]
 */
public class RetrofitDemo {

    private String TAG = "RetrofitDemo";

    public void simple() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        GitHubService service = retrofit.create(GitHubService.class);

        service.listRepos("octocat").subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Repo> repos) {

                    }
                });

//        repos.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
//                Log.i(TAG, "onResponse THREADID............."+android.os.Process.myTid()+" "+android.os.Process.myPid());
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//                Log.i(TAG, "onFailure THREADID............."+android.os.Process.myTid()+" "+android.os.Process.myPid());
//            }
//        });



    }


}
