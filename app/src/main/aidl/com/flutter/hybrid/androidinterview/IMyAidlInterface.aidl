// IMyAidlInterface.aidl
package com.flutter.hybrid.androidinterview;

import com.flutter.hybrid.androidinterview.bean.UserBean;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
            UserBean getUser();
            void setUser(in UserBean user);
}
