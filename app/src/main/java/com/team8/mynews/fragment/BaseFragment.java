package com.team8.mynews.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * @introduction: BaseFragment
 * @author: T19
 * @time: 2022.08.29 20:22
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 弹窗提示
     * @param msg 提示信息
     */
    public void showToast(String msg){
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 子线程中处理消息
     * @param msg 提示信息
     */
    public void showToastSync(String msg){
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    /**
     * 跳转到activity
     * @param activity 目标Activity
     */
    public void navigateTo(Class activity){
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    /**
     * 保存token信息到SharedPreferences存储对象中
     * @param key  "token"
     * @param val  token值
     */
    protected void saveStringToSp(String key, String val){
        SharedPreferences sp = getActivity().getSharedPreferences("sp_tzh", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, val);
        editor.apply();
    }

    /**
     * 从XML文件中获取数据值
     * @param key 要获取数据的key
     * @return 要获取的数据
     */
    protected String getStringFromSp(String key){
        SharedPreferences sp = getActivity().getSharedPreferences("sp_tzh", MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
