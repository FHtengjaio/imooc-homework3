package com.imooc.homework.service;

import com.imooc.homework.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDao {

    private static final List<User> userTable = new ArrayList<>();

    static {
        userTable.add(new User("imooc", "imooc", "超级管理员"));
    }

    public User getUserByName(String userName) {
        for (User user : userTable) {
            if (Objects.equals(user.getName(), userName)) {
                return user;
            }
        }
        return null;
    }

    public void delUser(String userName) {
        User user = getUserByName(userName);
        userTable.remove(user);
    }

    public boolean isUserExist(String userName){
        boolean flag = false;
        for (User user : userTable) {
            if (Objects.equals(user.getName(), userName)) {
                flag = true;
            }
        }
        return flag;
    }

    public void addUser(User user) {
        user.setType("普通管理员");
        userTable.add(user);
    }

    public boolean login(String userName, String password) {
        boolean flag = false;
        for (User u : userTable) {
            if (Objects.equals(u.getName(), userName) && Objects.equals(u.getPassword(), password)) {
                flag = true;
            }
        }
        return flag;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userTable);
    }
}
