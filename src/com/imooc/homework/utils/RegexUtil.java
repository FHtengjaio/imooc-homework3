package com.imooc.homework.utils;

public class RegexUtil {
    /**
     * 用户名正则表达式验证
     * */
    public static boolean isUserNameRight(String userName) {
        String regex = "^[A-Za-z0-9_]{3,12}$";
        return userName.matches(regex);
    }

    /**
     * 密码正则表达式验证
     * */
    public static boolean isPasswordRight(String password) {
        String regex = "^[A-Za-z0-9_]{5,12}$";
        return password.matches(regex);
    }

    /**
     * 课程Id正则表达式验证
     * */
    public static boolean isCourseIdRight(String id) {
        String regex = "^\\d{3,5}$";
        return id.matches(regex);
    }

    /**
     * 课程时长正则表达式验证
     * */
    public static boolean isTimeRight(String time) {
        String regex = "^\\d+\\.\\d{2}$";
        return time.matches(regex);
    }

}
