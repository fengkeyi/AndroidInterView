package com.cn.demo.jatpack.bean;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class DeviceGroup {

    public final static int TYPE_GROUP = 0x12;
    public final static int TYPE_DETAIL = 0x13;

    private String name;

    private String status;

    private boolean expand;

    private List<Device> devices;

    private int type = TYPE_GROUP;

    public DeviceGroup() {
    }

    public DeviceGroup(String name,String status, List<Device> devices) {
        this.name = name;
        this.status = status;
        this.devices = devices;
    }

    public boolean showGroup() {
        return type == TYPE_GROUP;
    }

    public boolean showDetail() {
        return type == TYPE_DETAIL;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isNormal() {
        return status.equals("normal");
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @NonNull
    @Override
    public DeviceGroup clone() throws CloneNotSupportedException {
        List<Device> deviceList = new ArrayList<>();
        deviceList.addAll(devices);
        DeviceGroup group = new DeviceGroup(name,status,deviceList);
        return group;
    }
}
