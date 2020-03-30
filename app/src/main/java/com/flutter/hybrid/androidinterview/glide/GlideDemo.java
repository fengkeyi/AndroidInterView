package com.flutter.hybrid.androidinterview.glide;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flutter.hybrid.androidinterview.R;

import java.util.Observable;

public class GlideDemo {

    public void simple(Activity activity, ImageView imageView) {
        Glide.with(activity).load("www.adb.png")
                .placeholder(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher_round)
                .override(50,50)
                .fitCenter()
                .centerCrop()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(imageView);
    }

    public void dialogDemo(Context context) {
        new AlertDialog.Builder(context).setIcon(R.mipmap.ic_launcher).create().show();
        RecyclerView recyclerView;
    }

    enum EnumDemo{
        INSTANCE;
        public void doSomething() {

        }
    }

}
