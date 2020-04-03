package com.flutter.hybrid.androidinterview.view;

import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

/***
 * TODO
 *  WebView 硬件加速耗电，关闭硬件加速
 *  内存泄漏：WebView内部有自己的线程加载网页，该线程自己无法控制，WebView关联了Activity，
 *  可能会导致导致Activity销毁的时候WebView还在加载没有停止，由于持有Activity引用导致Activity无法被回收
 *  解决：独立进程，简单暴力，退出Activity的时候 eixt(0)关闭进程
 *  动态添加WebView,对传入WebView中使用Context的弱引用，Activity创建的时候add进来，停止的时候remove掉
 */
public class WebView {
    android.webkit.WebView view;

   public void demo() {
       WebViewClient client = new WebViewClient();
       //TODO 监听页面是否加载完毕
       //TODO 页面跳转多次调用
       client.onPageFinished(view,"");
       WebChromeClient chromeClient = new WebChromeClient();
       //TODO 只会调用一次
       chromeClient.onProgressChanged(view,100);
   }
}
