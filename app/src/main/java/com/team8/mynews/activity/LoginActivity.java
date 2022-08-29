package com.team8.mynews.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.team8.mynews.R;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.LoginResponse;
import com.team8.mynews.utils.AppConfig;
import com.team8.mynews.utils.StringUtils;

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

public class LoginActivity extends BaseActivity {

    private EditText et_account;
    private EditText et_password;
    private Button button_login;


    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        button_login = findViewById(R.id.button_login);
    }

    @Override
    protected void initDate() {
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                login(account, password);
            }
        });
    }
    /*//封装前
    private void login(String account, String password){
        if (StringUtils.isEmpty(account)) {
            Toast.makeText(this, "账号不能为空！", Toast.LENGTH_SHORT).show();
        }else if (StringUtils.isEmpty(password)) {
            showToast("密码不能为空！");
        }else{

            //1、创建OKHttpClient
            OkHttpClient client = new OkHttpClient.Builder().build();

            Map<String, Object> m = new HashMap();
            m.put("mobile", "demoDate");
            m.put("password", "demoDate");

            JSONObject jsonObject = new JSONObject(m);
            String jsonStr = jsonObject.toString();
            RequestBody requestBodyJson =
                    RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonStr);

            //3、创建Request
            Request request = new Request.Builder()
                    .url(AppConfig.BASE_URL + AppConfig.LOGIN)
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
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String result = response.body().string();
                    *//* 转到主线程提示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(result);
                        }
                    });*//*
                }
            });
        }
    }*/

    //使用Api封装类实现登录
    private void login(String account, String password){
        if (StringUtils.isEmpty(account)) {
            Toast.makeText(this, "账号不能为空！", Toast.LENGTH_SHORT).show();
        }else if (StringUtils.isEmpty(password)) {
            showToast("密码不能为空！");
        }else{
            HashMap<String, Object> params = new HashMap<>();
            params.put("mobile", account);
            params.put("password", password);

            Api.config(ApiConfig.LOGIN, params).postRequest(new TtitCallback() {
                @Override
                public void onSuccess(String res) {
                    /*
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(res);
                        }
                    });*/
                    //利用Gson库将response响应字段转成实体对象
                    Gson gson = new Gson();
                    LoginResponse loginResponse = gson.fromJson(res, LoginResponse.class);
                    if(loginResponse.getCode() == 0 ){
                        String token = loginResponse.getToken();
                        /*
                        SharedPreferences sp = getSharedPreferences("sp_ttit", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", token);
                        //editor.commit();
                        editor.apply();*/
                        saveStringToSp("token", token);
                        navigateTo(HomeActivity.class);
                        showToastSync("登录成功！");
                        /*
                        loop之后的代码不会执行，所以跳转代码要放在showToast之前
                        navigateTo(HomeActivity.class);
                         */
                    }else{
                        showToastSync("登录失败！");
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e("onFailure", e.toString());
                }
            });
        }
    }

}