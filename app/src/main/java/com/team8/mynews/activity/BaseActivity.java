package com.team8.mynews.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @introduction: BaseActivity
 * @author: T19
 * @time: 2022.08.19 14:49
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(initLayout());
        initView();
        initDate();
    }

    protected abstract int initLayout();
    protected abstract void initView();
    protected abstract void initDate();


    /**
     * 弹窗提示
     * @param msg 提示信息
     */
    public void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 子线程中处理消息
     * @param msg 提示信息
     */
    public void showToastSync(String msg){
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    /**
     * 跳转到activity
     * @param activity 目标Activity
     */
    public void navigateTo(Class activity){
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    public void navigateToWithFlag(Class activity, int flags){
        Intent intent = new Intent(mContext, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 保存token信息
     * @param key  "token"
     * @param val  token值
     */
    protected void saveStringToSp(String key, String val){
        SharedPreferences sp = getSharedPreferences("sp_tzh", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putString(key, val);
        editor.apply();
    }

    /**
     * 从XML文件中获取数据值
     * @param key 要获取数据的key
     * @return 要获取的数据
     */
    protected String getStringFromSp(String key){
        SharedPreferences sp = getSharedPreferences("sp_tzh", MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
