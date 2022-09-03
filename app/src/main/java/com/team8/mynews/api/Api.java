package com.team8.mynews.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @introduction: 网络请求封装类
 * @author: T19
 * @time: 2022.08.19 16:35
 */
public class Api {

    private static OkHttpClient client;
    private static String requestUrl;
    private static HashMap<String, Object> mParams;
    public static Api api = new Api();

    public Api(){
    }

    public static Api config(String url, HashMap<String, Object> params){
        //1、创建OkHttp
        client = new OkHttpClient.Builder()
                .build();
        requestUrl = ApiConfig.BASE_URL + url;
        mParams = params;
        return api;
    }

    /**
     * post请求
     * @param callback 回调接口
     */
    public void postRequest(TtitCallback callback){
        JSONObject jsonObject = new JSONObject(mParams);
        String jsonStr = jsonObject.toString();
        RequestBody requestBodyJson =
                RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);

        //3、创建Request
        Request request = new Request.Builder()
                .url(requestUrl)
                .addHeader("contentType", "application/json; charset=UTF-8")
                .post(requestBodyJson)
                .build();

        //4、创建call回调对象
        final Call call = client.newCall(request);

        //5、发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("onFailure", e.getMessage());
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String result = response.body().string();
                callback.onSuccess(result);
            }
        });
    }

    /**
     * get请求
     * @param callback 回调接口
     */
    public void getRequest(Context context, final TtitCallback callback){
        //获取token
        SharedPreferences sp = context.getSharedPreferences("sp_tzh", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        String url = getAppendUrl(requestUrl, mParams);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("onFailure", e.getMessage());
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String res = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    String code = jsonObject.getString("code");
                    if (code.equals("401")){//token失效时跳转到登录页面
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        //这时候就不需要再在获取数据时进行token的判断
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onSuccess(res);
            }
        });
    }

    /**
     * 拼接 get请求的url
     * @param url 请求接口,例如：“http://localhost:8080/renren-fast” + “/app/videolist/list”
     * @param map 参数集合,[token: ***]
     * @return 完整url
     */
    private String getAppendUrl(String url, HashMap<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for ( Map.Entry<String, Object> entry : map.entrySet() ) {
                if (StringUtils.isEmpty(builder.toString())) {
                    // builder = "?" ;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                //builder = "?token=***" ;
                builder.append(entry.getKey()).append("=").append(entry.getValue());
            }
            //url = "http://localhost:8080/renren-fast/app/videolist/list?token=***" ;
            url += builder.toString();
        }
        return url;
    }
}
