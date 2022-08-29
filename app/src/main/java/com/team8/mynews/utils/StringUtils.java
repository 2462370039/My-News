package com.team8.mynews.utils;

/**
 * @introduction: 字符串工具类
 * @author: T19
 * @time: 2022.08.19 14:36
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param str 判断的字符串
     * @return true为空，false不空
     */
    public static boolean isEmpty( String str){
        if (str == null || str.length() <= 0) {
            return true;
        }else{
            return false;
        }
    }
}
