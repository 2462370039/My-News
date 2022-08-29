package com.team8.mynews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.team8.mynews.activity.BaseActivity;
import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.activity.RegisterActivity;

public class MainActivity extends BaseActivity {
    private Button btnLogin;
    private Button btnRegister;


    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //获取登录按钮
        btnLogin = findViewById(R.id.btnLogin);
        //获取注册按钮
        btnRegister = findViewById(R.id.btnRegister);
    }

    @Override
    protected void initDate() {
        //为登录按钮绑定点击事件
        /* 原方式
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/
        btnLogin.setOnClickListener(view -> {
    //        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
    //        startActivity(intent);
            navigateTo(LoginActivity.class);
        });
        //为注册按钮绑定点击事件
        btnRegister.setOnClickListener(view -> {
            navigateTo(RegisterActivity.class);
        });
    }
}