package com.cn.demo.jatpack.manager;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static final Map<String,String> DES_AIMBOT_EN = new HashMap<String,String>(){
        {
            put("lidar","lidar");
            put("rgbd","rgbd");
            put("floor_ir","floor_ir");
            put("charge_ir","charge_ir");
            put("wall_ir","wall_ir");
            put("geomagnetism","geomagnetism");
            put("odom","odom");
            put("yuntai","yuntai");
            put("qiuji1","qiuji1");
            put("qiuji2","qiuji2");
            put("qiuji3","qiuji3");
            put("anticollision","anticollision");
            put("elevator","elevator");
        }
    };

    public static final Map<String,String> DES_AIMBOT_CN = new HashMap<String,String>(){
        {
            put("lidar","雷达");
            put("rgbd","深度摄像机");
            put("floor_ir","地检传感器");
            put("charge_ir","回充红外传感器");
            put("wall_ir","墙检红外传感器");
            put("geomagnetism","地磁传感器");
            put("odom","里程计");
            put("yuntai","云台");
            put("qiuji1","球机1");
            put("qiuji2","球机2");
            put("qiuji3","球机3");
            put("anticollision","防撞条");
            put("elevator","升降杆");
        }
    };




}
