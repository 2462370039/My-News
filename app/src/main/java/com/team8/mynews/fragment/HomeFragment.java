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
        String token = getStringFromSp("token");
        if (!StringUtils.isEmpty(token)) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("token", token);

            Api.config(ApiConfig.VIDEO_CATEGORY_LIST, params).getRequest(new TtitCallback() {
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
            });
        }else {
            navigateTo(LoginActivity.class);
            showToastSync("请先登录！");
        }
        /*
        String token = getStringFromSp("token");
        //token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNjgiLCJpYXQiOjE2NjE3ODEzNTgsImV4cCI6MTY2MjM4NjE1OH0.NxQ4o2g-6HLwGRPyLZLCX3RDcXk4RY_icQtsRNYtr_E810WyVsIBqjKcGJCRSZbqB9HqQW_bpYHGwGmfhumQ6w";
        //Log.e("token", token);
        if (!StringUtils.isEmpty(token)) {
            HashMap<String, Object> params = new HashMap<>();

            params.put("token", token);
            //请求5条数据，实现分页
            params.put("page", pageNum);
            params.put("limit", ApiConfig.PAGE_SIZE);
            //请求数据
            Api.config(ApiConfig.VIDEO_LIST, params).getRequest(new TtitCallback() {
                @Override
                public void onSuccess(String res) {
                    getActivity().runOnUiThread(new Runnable() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void run() {
                            Log.e("onS", "有数据,page" + pageNum);
                            if (isRefresh) {
                                //获取到数据结束刷新效果
                                refreshLayout.finishRefresh();
                            }else {
                                //获取到数据结束加载效果
                                refreshLayout.finishLoadMore();
                            }

                            VideoListResponse response = new Gson().fromJson(res, VideoListResponse.class);
                            if (null != response && response.getCode() == 0) {
                                List<VideoEntity> list = response.getPage().getList();
                                if (list != null && list.size() > 0) {//响应有数据
                                    if (isRefresh) {
                                        Log.e("", "更新datasets");
                                        datasets.clear();
                                        datasets = list;
                                    } else {
                                        Log.e("", "添加list");
                                        datasets.addAll(list);
                                    }
                                    *//* 在onViewCreated()中创建Adapter,这里仅通过setDatasets()更新datasets
                                    videoAdapter = new VideoAdapter(getActivity(), datasets);*//*
                                    videoAdapter.setDatasets(datasets);
                                    videoAdapter.notifyDataSetChanged();
                                } else {
                                    if (isRefresh) {
                                        showToast("刷新不到新数据！");
                                    }else {
                                        showToast("加载不到更多数据！");
                                    }
                                }
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isRefresh) {
                                refreshLayout.finishRefresh(true);
                            } else {
                                refreshLayout.finishLoadMore(true);
                            }
                            showToast("数据加载失败！");
                        }
                    });
                }
            });
        }else {
            navigateTo(LoginActivity.class);
            showToastSync("请先登录！");
        }*/
    }
}