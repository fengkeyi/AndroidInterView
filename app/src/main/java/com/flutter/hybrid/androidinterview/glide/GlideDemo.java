package com.flutter.hybrid.androidinterview.glide;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flutter.hybrid.androidinterview.R;

import java.util.Observable;

/***
 * TODO
 *  Glide.with
 *  getRetriever(activity)-->RequestManagerRetriever-->RequestManager
 *  RequestManager.load-->RequestBuilder
 *  RequestBuilder.into-->buildRequest(Request)
 *  target.setRequest(request)->Request.begin
 *
 */
public class GlideDemo {

    public void simple(Activity activity, ImageView imageView) {
        Glide.with(activity).load("www.adb.png")
                .placeholder(R.mipmap.ic_launcher)
                .error(android.R.drawable.stat_notify_error)
                .override(50,50)
                .fitCenter()//view显示完整，蛋有可能不能填满整个View
                .centerCrop()//view 会填满，可能图片不完整
                .skipMemoryCache(true)//影响内存缓存,会存储到磁盘
                .diskCacheStrategy(DiskCacheStrategy.ALL)//
                .priority(Priority.HIGH)
                .into(imageView);
    }

    enum EnumDemo{
        INSTANCE;
        public void doSomething() {

        }
    }

}
