package com.team8.mynews.utils;

/**
 * @introduction: 网络接口信息
 * @author: T19
 * @time: 2022.08.19 15:32
 */
public class AppConfig {
    public static final String BASE_URL = "http://47.112.180.188:8080/renren-fast";
    /*
    不是https请求头，需要禁用明文流量请求检查
    见文件 network_security_config.xml
     */
    public static final String LOGIN = "/app/login";
}
