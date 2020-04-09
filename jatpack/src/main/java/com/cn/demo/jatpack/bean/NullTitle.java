package com.cn.demo.jatpack.bean;

public class NullTitle {
    private int resId;
    private String status;
    private int count;

    public NullTitle() {
    }

    public NullTitle(int resId, String status, int count) {
        this.resId = resId;
        this.status = status;
        this.count = count;
    }

    public boolean isNormal() {
        return status.equals("normal");
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
