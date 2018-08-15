package com.imooc.homework.utils;

public class StringUtil {
    /**
     * String类中的trim()无法除去中文的空格，此方法提供去除中文空格
     * */
    public static String trim(String sourceString) {
        if (sourceString == null) {
            throw new NullPointerException("你提供的字符串是null值");
        }
        int start = 0;
        int end = sourceString.length() - 1;
        while (start < end) {
            if (sourceString.charAt(start) != ' ' && sourceString.charAt(start) != (char) 160) {
                break;
            }
            start++;
        }
        while (end > start) {
            if (sourceString.charAt(end) != ' ' && sourceString.charAt(end) != (char) 160) {
                break;
            }
            end--;
        }
        return sourceString.substring(start, (end + 1));
    }
}
