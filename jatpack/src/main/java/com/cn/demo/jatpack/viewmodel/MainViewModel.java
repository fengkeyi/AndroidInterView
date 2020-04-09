package com.cn.demo.jatpack.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cn.demo.jatpack.R;
import com.cn.demo.jatpack.bean.DeviceGroup;
import com.cn.demo.jatpack.bean.NullTitle;
import com.cn.demo.jatpack.manager.DeviceCacheManager;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private Context mContext;

    private MutableLiveData<List> mutableLiveData = new MutableLiveData<>();

    public MainViewModel(Context context) {
        mContext = context;
        initDemoData();
    }

    private void initDemoData() {
        DeviceCacheManager.get().initDemoData();
        List<DeviceGroup> normalGroup = new ArrayList<>();
        List<DeviceGroup> errorGroup = new ArrayList<>();
        DeviceCacheManager.get().getDevices(normalGroup,errorGroup);
        List allGroup = new ArrayList<>();
        if (errorGroup.size() > 0) {
            int errorCount = errorGroup.size();
            allGroup.add(new NullTitle(R.string.str_error_title_count, "unnormal", errorCount));
        }
        allGroup.addAll(errorGroup);
        if (normalGroup.size() > 0) {
            int normalCount = normalGroup.size();
            allGroup.add(new NullTitle(R.string.str_normal_title_count, "normal", normalCount));
        }
        allGroup.addAll(normalGroup);
        mutableLiveData.postValue(allGroup);
    }

    public void OnResume() {

    }

    public void onPaunse() {

    }

    public MutableLiveData<List> getData() {
        return mutableLiveData;
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Context mContext;

        public Factory(@NonNull Context context) {
            mContext = context;
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MainViewModel(mContext);
        }
    }

}
