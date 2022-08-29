package com.team8.mynews.api;

/**
 * @introduction:
 * @author: T19
 * @time: 2022.08.19 16:33
 */
public interface TtitCallback {

    //请求成功
    void onSuccess(String res);
    //请求失败
    void onFailure(Exception e);
}
