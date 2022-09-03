package com.team8.mynews.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;
import com.team8.mynews.R;
import com.team8.mynews.activity.HomeActivity;
import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.adapter.HomeAdapter;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.CategoryEntity;
import com.team8.mynews.entity.VideoCategoryResponse;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.entity.VideoListResponse;
import com.team8.mynews.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends BaseFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private  String[] mTitles;

    private HomeAdapter mAdapter;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }



    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        viewPager = mRootView.findViewById(R.id.fixedViewPager);
        slidingTabLayout = mRootView.findViewById(R.id.slidingTabLayout);
    }

    @Override
    protected void initDate() {
        getVideoCategoryList();
    }

    /**
     * 获取视频类型列表
     */
    private void getVideoCategoryList(){
        /*//在响应成功时，会对code进行401判断，这里就不再需要进行token判断
        String token = getStringFromSp("token");
        if (!StringUtils.isEmpty(token)) {*/
            HashMap<String, Object> params = new HashMap<>();
            Api.config(ApiConfig.VIDEO_CATEGORY_LIST, params).getRequest(getActivity(), new TtitCallback() {
                @Override
                public void onSuccess(String res) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            VideoCategoryResponse response = new Gson().fromJson(res, VideoCategoryResponse.class);
                            if (response != null && response.getCode() == 0) {
                                List<CategoryEntity> list = response.getPage().getList();
                                if (list != null && list.size() > 0) {
                                    mTitles = new String[list.size()];
                                    for (int i = 0; i < list.size(); i++) {
                                        mTitles[i] = list.get(i).getCategoryName();
                                        mFragments.add(VideoFragment.newInstance(list.get(i).getCategoryId()));
                                    }

                                    //预加载
                                    viewPager.setOffscreenPageLimit(mFragments.size());

                                    mAdapter = new HomeAdapter(getFragmentManager(), mTitles, mFragments);
                                    viewPager.setAdapter(mAdapter);
                                    slidingTabLayout.setViewPager(viewPager);
                                }
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Exception e) {

                }
            });/*
        }else {
            navigateTo(LoginActivity.class);
            showToastSync("请先登录！");
        }*/
    }
}