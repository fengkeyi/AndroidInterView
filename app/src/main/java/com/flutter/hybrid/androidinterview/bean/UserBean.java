package com.flutter.hybrid.androidinterview.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable，android特有，实现比较复杂，程序建传递数据使用Parcelable
 * Serializable 内存开销大，实现简单，序列化到存储设备使用
 */
public class UserBean implements Parcelable {

    private String name;
    private int age;

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
