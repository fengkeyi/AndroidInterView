package com.cn.demo.jatpack.manager;

import com.cn.demo.jatpack.bean.Device;
import com.cn.demo.jatpack.bean.DeviceGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeviceCacheManager {

    private final Map<String, Integer> mDeviceStatus = new HashMap<>();
    private final Map<String, Device> mDeviceCache = new HashMap<>();


    public static DeviceCacheManager get() {
        return Instance.INSTANCE;
    }

    private DeviceCacheManager() {
    }

    public void add(Device device) {
        mDeviceCache.put(device.getKey(), device);
    }

    public void addAll(List<Device> devices) {
        for (Device device : devices) {
            mDeviceCache.put(device.getKey(), device);
        }
    }

    private void trimToStatus() {
        synchronized (mDeviceCache) {
            mDeviceStatus.clear();
            Iterator<Device> iterator = mDeviceCache.values().iterator();
            while (iterator.hasNext()) {
                Device device = iterator.next();
                if (!mDeviceStatus.containsKey(device.getName())) {
                    mDeviceStatus.put(device.getName(), 0);
                }
                int errorCount = mDeviceStatus.get(device.getName());
                if (!device.isNormal()) {
                    mDeviceStatus.put(device.getName(), ++errorCount);
                }
            }
        }
    }

    public void initDemoData() {
        add(new Device("lidar","normal","lidar1"));
        add(new Device("lidar","normal","lidar2"));
        add(new Device("lidar","normal","lidar3"));
        add(new Device("lidar","unnormal","lidar4"));
        add(new Device("lidar","unnormal","lidar5"));
        add(new Device("lidar","unnormal","lidar6"));
        add(new Device("lidar","unnormal","lidar7"));
        add(new Device("lidar","unnormal","lidar8"));

        add(new Device("floor_ir","normal","floor_ir1"));
        add(new Device("floor_ir","normal","floor_ir12"));
        add(new Device("floor_ir","normal","floor_ir13"));

        add(new Device("wall_ir","normal","wall_ir1"));
        add(new Device("wall_ir","normal","wall_ir2"));
        add(new Device("wall_ir","normal","wall_ir3"));

        add(new Device("geomagnetism","normal","geomagnetism"));
        add(new Device("odom","unnormal","odom"));
        add(new Device("qiuji2","unnormal","qiuji2"));
        add(new Device("anticollision","normal","anticollision"));
        add(new Device("elevator","normal","elevator"));

    }

    public void getDevices(List<DeviceGroup> normalGroups, List<DeviceGroup> errorGroups){
        trimToStatus();
        Map<String, List<Device>> devices = getDeviceFromCache();
        synchronized (mDeviceStatus) {
            Iterator<String> iterator = mDeviceStatus.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                int errorCount = mDeviceStatus.get(key);
                if (errorCount > 0) {
                    errorGroups.add(new DeviceGroup(key,"unnormal",devices.get(key)));
                } else {
                    normalGroups.add(new DeviceGroup(key,"normal" ,devices.get(key)));
                }
            }
        }
    }

    private Map<String,List<Device>> getDeviceFromCache(){
        synchronized (mDeviceCache) {
            Iterator<Device> iterator = mDeviceCache.values().iterator();
            Map<String, List<Device>> devices = new HashMap<>();
            while (iterator.hasNext()) {
                Device device = iterator.next();
                List<Device> list = devices.get(device.getName());
                if (list == null) {
                    list = new ArrayList<>();
                    devices.put(device.getName(), list);
                }
                list.add(device);
            }
            return devices;
        }
    }


    private static class Instance {
        private static final DeviceCacheManager INSTANCE = new DeviceCacheManager();
    }

}
