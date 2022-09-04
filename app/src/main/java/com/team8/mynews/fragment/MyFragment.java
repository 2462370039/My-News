package com.team8.mynews.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.team8.mynews.R;
import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.activity.MyCollectActivity;
import com.team8.mynews.adapter.MyCollectAdapter;

import butterknife.BindView;
import butterknife.OnClick;


public class MyFragment extends BaseFragment {

    @BindView(R.id.img_header)
    ImageView imgHeader;

    public MyFragment() {
        // Required empty public constructor
    }


    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {

    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.img_header, R.id.rl_collect, R.id.rl_skin, R.id.rl_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_header:
                showToast("更换头像");
                break;
            case R.id.rl_collect:
                navigateTo(MyCollectActivity.class);
                break;
            case R.id.rl_skin:
                showToast("换肤");
                break;
            case R.id.rl_logout:
                removeByKey("token");
                navigateToWithFlag(LoginActivity.class,
                        Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                break;
        }
    }
}