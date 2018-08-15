package com.imooc.homework.service;

import com.imooc.homework.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDao {

    private static final List<User> userTable = new ArrayList<>();

    // 初始化超级管理员
    static {
        userTable.add(new User("imooc", "imooc", "超级管理员"));
    }

    /**
     * 通过name获取user
     * */
    public User getUserByName(String userName) {
        for (User user : userTable) {
            if (Objects.equals(user.getName(), userName)) {
                return user;
            }
        }
        return null;
    }

    /**
     * 通过name删除用户
     * */
    public void delUser(String userName) {
        User user = getUserByName(userName);
        userTable.remove(user);
    }

    /**
     * 通过name判断用户是否已经存在
     * */
    public boolean isUserExist(String userName){
        boolean flag = false;
        for (User user : userTable) {
            if (Objects.equals(user.getName(), userName)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 添加管理员，用户类型只能是普通管理员
     * */
    public void addUser(User user) {
        user.setType("普通管理员");
        userTable.add(user);
    }

    /**
     * 判断用户是否可以登录
     * */
    public boolean login(String userName, String password) {
        boolean flag = false;
        for (User u : userTable) {
            if (Objects.equals(u.getName(), userName) && Objects.equals(u.getPassword(), password)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取所有的用户
     * */
    public List<User> getAllUsers() {
        return new ArrayList<>(userTable);
    }
}
