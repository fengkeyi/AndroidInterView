package com.flutter.hybrid.androidinterview.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TODO
 *  Parcelable，android特有，实现比较复杂，程序建传递数据使用Parcelable
 *  Serializable 内存开销大，实现简单，序列化到存储设备使用
 *  总结：
 *      1、Serializable是java的序列化方式，Parcelable是android特有的序列化方法
 *      2、在使用内存的时候Parcelable比Serializable性能高
 *      3、Serializable在序列化的时候会产生大量的临时变量，从而引起频繁的GC
 *      4、Parcelable不能使用在将数据存储在磁盘上的情况
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
