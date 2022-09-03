package com.team8.mynews.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.team8.mynews.R;
import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.adapter.NewsAdapter;
import com.team8.mynews.adapter.VideoAdapter;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.NewsEntity;
import com.team8.mynews.entity.NewsListResponse;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.entity.VideoListResponse;
import com.team8.mynews.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NewsFragment extends BaseFragment{

    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;

    private int pageNum = 1;

    List<NewsEntity> datasets = new ArrayList<>();

    public NewsFragment() {
        // Required empty public constructor
    }


    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {

        recyclerView = mRootView.findViewById(R.id.recyclerView);
        refreshLayout = mRootView.findViewById(R.id.refreshLayout);

        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
    }

    @Override
    protected void initDate() {
        //新建一个线性布局管理器   //getActivity() --> HomeActivity
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //线性布局管理器 设置item垂直排列
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //RV必选项1：布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);

        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setDatasets(datasets);
        recyclerView.setAdapter(newsAdapter);

        //下拉刷新事件
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getNewsList(true);
                Log.e("RorL", "刷新咨询");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getNewsList(false);
                Log.e("RorL", "加载咨询");
            }
        });
        //第一次进入页面获取数据
        getNewsList(true);
    }


    /**
     * 获取NewsList
     * @param isRefresh true下拉刷新,false上拉加载
     */
    private void getNewsList(boolean isRefresh){
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
            Api.config(ApiConfig.NEWS_LIST, params).getRequest(new TtitCallback() {
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

                            NewsListResponse response = new Gson().fromJson(res, NewsListResponse.class);
                            if (null != response && response.getCode() == 0) {
                                List<NewsEntity> list = response.getPage().getList();
                                if (list != null && list.size() > 0) {//响应有数据
                                    if (isRefresh) {
                                        Log.e("", "更新datasets");
                                        datasets.clear();
                                        datasets = list;
                                    } else {
                                        Log.e("", "添加list");
                                        datasets.addAll(list);
                                    }
                                    /* 在onViewCreated()中创建Adapter,这里仅通过setDatasets()更新datasets
                                    videoAdapter = new VideoAdapter(getActivity(), datasets);*/
                                    newsAdapter.setDatasets(datasets);
                                    newsAdapter.notifyDataSetChanged();
                                } else {
                                    if (isRefresh) {
                                        showToast("刷新不到新数据！");
                                    }else {
                                        showToast("加载不到更多数据！");
                                        pageNum--;
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
                                pageNum--;
                            }
                            showToast("数据加载失败！");
                        }
                    });
                }
            });
        }else {
            navigateTo(LoginActivity.class);
            showToastSync("token为空,请先登录！");
        }

    }
}