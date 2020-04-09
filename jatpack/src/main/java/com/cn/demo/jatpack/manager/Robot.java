package com.cn.demo.jatpack.manager;

public class Robot {

    private static final String TAG = "Robot.aimbot";


    public static Robot getInstance() {
        return Instance.INSTANCE;
    }

    private Robot() {
    }

    private static class Instance{
        private static final Robot INSTANCE = new Robot();
    }

    public void sendMsg(String msg) {
    }


}
