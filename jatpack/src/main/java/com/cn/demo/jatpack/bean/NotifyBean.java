package com.cn.demo.jatpack.bean;

import com.google.gson.annotations.SerializedName;

public class NotifyBean {

    /**
     * title : notify_device_status
     * content : {"device_name":"lidar","status":"normal","message":"des"}
     */
    private String title;
    @SerializedName("content")
    private Device device;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device content) {
        this.device = content;
    }
}
