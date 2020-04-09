package com.cn.demo.jatpack.bean;

import com.google.gson.annotations.SerializedName;

public class Device {

    /**
     * device_name : lidar
     * status : normal
     * message : des
     */

    @SerializedName("device_name")
    private String name;
    private String status;
    private String message;

    public Device() {
    }

    public Device(String name, String status, String message) {
        this.name = name;
        this.status = status;
        this.message = message;
    }

    public boolean isNormal() {
        return status.equals("normal");
    }

    public String getKey() {
        return name + "_" + message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
