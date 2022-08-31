package com.team8.mynews.api;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.08.19 16:46
 */
public class ApiConfig {
    public static final String BASE_URL = "http://47.112.180.188:8080/renren-fast";
    public static final String LOGIN = "/app/login";//登录
    public static final String REGISTER = "/app/register";//注册
    public static final String VIDEO_LIST = "/app/videolist/list";//所有视频列表
    public static final String VIDEO_LIST_BY_CATEGORY = "/app/videolist/getListByCategoryId";//各类型视频列表
    public static final String VIDEO_CATEGORY_LIST = "/app/videocategory/list";//视频类型列表

    public static final int PAGE_SIZE = 5;
}
