package com.ubtechinc.aimbot.democode.alibabaJson;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.ubtechinc.aimbot.democode.bean.Group;
import com.ubtechinc.aimbot.democode.bean.User;

public class FastJsonDemo {

    private static final String TAG = "FastJsonDemo";

    private static final String JSON_S = "{\"id\":1,\"name\":\"admin\",\"users\":[{\"id\":2,\"name\":\"guest\"},{\"id\":3,\"name\":\"root\"}]}";

    public static void test() {
        Group group = new Group();
        group.setId(0L);
        group.setName("admin");

        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addUser(guestUser);
        group.addUser(rootUser);
        String jsonString = JSON.toJSONString(group);
        Log.i(TAG, "JSON String:" + jsonString);

        Group gr = JSON.parseObject(JSON_S, Group.class);

        Log.i(TAG, "JSON JSON_S:" + JSON.toJSONString(gr));
    }

}
