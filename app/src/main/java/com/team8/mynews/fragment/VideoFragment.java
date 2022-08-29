package com.team8.mynews.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.team8.mynews.R;
import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.adapter.VideoAdapter;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.entity.VideoListResponse;
import com.team8.mynews.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class VideoFragment extends BaseFragment {

    private String title;
    private RecyclerView recyclerView;

    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance(String title) {
        VideoFragment fragment = new VideoFragment();
        fragment.title = title;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //新建一个线性布局管理器   //getActivity() --> HomeActivity
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //线性布局管理器 设置item垂直排列
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //RV必选项1：布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);

        getVideoList();
        /*
        //创建item的数据集 datasets
        List<VideoEntity> datasets = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //创建videoEntity实体类
            VideoEntity videoEntity = new VideoEntity();

            videoEntity.setTitle("是人是咒都在秀，只有伏黑在挨揍");
            videoEntity.setName("到底留不留亦非");
            videoEntity.setLikeCount(i*2);
            videoEntity.setCollectCount(i*4);
            videoEntity.setCommentCount(i*6);

            datasets.add(videoEntity);
        }

        //创建Video适配器
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datasets);
        //RV必选项2：数据适配器
        recyclerView.setAdapter(videoAdapter);*/
    }

    private void getVideoList(){
        String token = getStringFromSp("token");
        //token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNjgiLCJpYXQiOjE2NjE3ODEzNTgsImV4cCI6MTY2MjM4NjE1OH0.NxQ4o2g-6HLwGRPyLZLCX3RDcXk4RY_icQtsRNYtr_E810WyVsIBqjKcGJCRSZbqB9HqQW_bpYHGwGmfhumQ6w";
        //Log.e("token", token);
        if (!StringUtils.isEmpty(token)) {
            HashMap<String, Object> params = new HashMap<>();
            params.put("token", token);
            Api.config(ApiConfig.VIDEO_LIST, params).getRequest(new TtitCallback() {
                @Override
                public void onSuccess(String res) {
                    VideoListResponse response = new Gson().fromJson(res, VideoListResponse.class);
                    if (null != response && response.getCode() == 0) {
                        List<VideoEntity> datasets = response.getPage().getList();
                        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datasets);
                        recyclerView.setAdapter(videoAdapter);
                    }
                }

                @Override
                public void onFailure(Exception e) {

                }
            });
        }else {
            navigateTo(LoginActivity.class);
            showToastSync("token为空,请先登录！");
        }

    }
}