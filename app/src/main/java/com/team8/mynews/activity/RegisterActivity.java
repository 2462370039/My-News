package com.team8.mynews.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.team8.mynews.R;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.LoginResponse;
import com.team8.mynews.utils.StringUtils;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity {

    private EditText et_account;
    private EditText et_password;
    private Button button;


    @Override
    protected int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        et_account = findViewById(R.id.et_register_account);
        et_password = findViewById(R.id.et_register_password);
        button = findViewById(R.id.button_register);
    }

    @Override
    protected void initDate() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                register(account, password);
            }
        });
    }

    private void register(String account, String password) {
        if (StringUtils.isEmpty(account)) {
            Toast.makeText(this, "账号不能为空！", Toast.LENGTH_SHORT).show();
        }else if (StringUtils.isEmpty(password)) {
            showToast("密码不能为空！");
        }else {
            HashMap<String, Object> params = new HashMap<>();
            params.put("mobile", account);
            params.put("password", password);
            Api.config(ApiConfig.REGISTER, params).postRequest(mContext, new TtitCallback() {
                @Override
                public void onSuccess(String res) {
                    Log.d("onSuccess", res);
                    //showToastSync(res);
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e("onFailure", e.toString());
                }
            });
        }
    }

}