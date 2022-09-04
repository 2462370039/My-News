package com.team8.mynews.api;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.08.19 16:46
 */
public class ApiConfig {

    //public static final String BASE_URL = "http://192.168.43.182:8080/renren-fast";
    public static final String BASE_URL = "http://192.168.137.1:8080/renren-fast";
    //public static final String BASE_URL = "http://47.112.180.188:8080/renren-fast";
    public static final String LOGIN = "/app/login";//登录
    public static final String REGISTER = "/app/register";//注册

    public static final String VIDEO_LIST = "/app/videolist/list";//所有视频列表
    public static final String VIDEO_LIST_BY_CATEGORY = "/app/videolist/getListByCategoryId";//各类型视频列表
    public static final String VIDEO_CATEGORY_LIST = "/app/videocategory/list";//视频类型列表

    public static final String NEWS_LIST = "/app/news/api/list";//咨询列表接口

    public static final String VIDEO_UPDATE_COUNT = "/app/videolist/updateCount";//视频点赞收藏更新

    public static final String VIDEO_MY_COLLECT = "/app/videolist/mycollect";//我的收藏接口

    public static final int PAGE_SIZE = 5;
}
