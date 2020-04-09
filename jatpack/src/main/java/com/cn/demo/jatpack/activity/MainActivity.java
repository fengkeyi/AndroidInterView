package com.cn.demo.jatpack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.cn.demo.jatpack.R;
import com.cn.demo.jatpack.adapter.DetectionAdapter;
import com.cn.demo.jatpack.databinding.ActivityMainBinding;
import com.cn.demo.jatpack.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;
    private DetectionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel.Factory factory = new MainViewModel.Factory(this);
        mainViewModel = new ViewModelProvider(this, factory)
                .get(MainViewModel.class);
        initAdapter();
    }

    private void initAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        adapter = new DetectionAdapter(this);
        mainBinding.rclDetection.setAdapter(adapter);
        mainBinding.rclDetection.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainViewModel.getData().observe(this, new Observer<List>() {
            @Override
            public void onChanged(List list) {
                adapter.setData(list);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClickBack(View view) {
        finish();
    }

}
