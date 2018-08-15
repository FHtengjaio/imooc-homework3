package com.imooc.homework.service;

import com.imooc.homework.data.User;

import java.util.List;

public class UserDaoImpl {

    /*
    * 此类中的方法完全调用UserDao中的方法
    * */

    private static final UserDao courseDao = new UserDao();

    public static boolean login(String userName, String password) {
        return courseDao.login(userName, password);
    }

    public static boolean isUserExist(String userName) {
        return courseDao.isUserExist(userName);
    }

    public static void addUser(User user) {
        courseDao.addUser(user);
    }

    public static List<User> getAllUsers(){
        return courseDao.getAllUsers();
    }

    public static void delUser(String userName){
        courseDao.delUser(userName);
    }
}
