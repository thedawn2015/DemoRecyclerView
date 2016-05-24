package com.simon.demorecyclerview.utils;

import com.simon.demorecyclerview.R;
import com.simon.demorecyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xw on 2016/5/24.
 */
public class UserUtil {
    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(101, "李理", R.drawable.draw1));
        userList.add(new User(102, "汪海", R.drawable.draw2));
        userList.add(new User(103, "吴翔", R.drawable.draw3));
        userList.add(new User(104, "张三", R.drawable.draw1));
        userList.add(new User(105, "胡丽", R.drawable.draw2));
        userList.add(new User(106, "陈香", R.drawable.draw3));
        return userList;
    }
}
