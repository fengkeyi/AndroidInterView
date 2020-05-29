package com.ubtechinc.aimbot.democode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.ubtechinc.aimbot.democode.alibabaJson.FastJsonDemo;
import com.ubtechinc.aimbot.democode.okio.OkioDemo;

import java.io.File;
import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            okiotest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void okiotest() throws IOException {
        final File file = new File(getCacheDir(), "okiodemo.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        Flowable.range(1,1)
                .subscribeOn(Schedulers.io()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws IOException {
                Log.i("OkioDemo", "start " + integer);
                OkioDemo.copyFile(getAssets().open("okiodemo.txt"), file);
                OkioDemo.readFile(file);
                FastJsonDemo.test();
        }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("OkioDemo", "accept error: " + throwable.toString());
            }
        });
    }
}
