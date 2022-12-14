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
        //?????????????????????????????????   //getActivity() --> HomeActivity
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //????????????????????? ??????item????????????
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //RV?????????1??????????????????
        recyclerView.setLayoutManager(linearLayoutManager);

        newsAdapter = new NewsAdapter(getActivity());
        newsAdapter.setDatasets(datasets);
        recyclerView.setAdapter(newsAdapter);

        //??????????????????
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getNewsList(true);
                Log.e("RorL", "????????????");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getNewsList(false);
                Log.e("RorL", "????????????");
            }
        });
        //?????????????????????????????????
        getNewsList(true);
    }


    /**
     * ??????NewsList
     * @param isRefresh true????????????,false????????????
     */
    private void getNewsList(boolean isRefresh){
        HashMap<String, Object> params = new HashMap<>();
        //??????5????????????????????????
        params.put("page", pageNum);
        params.put("limit", ApiConfig.PAGE_SIZE);
        //????????????
        Api.config(ApiConfig.NEWS_LIST, params).getRequest(getActivity(), new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                getActivity().runOnUiThread(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        Log.e("onS", "?????????,page" + pageNum);
                        if (isRefresh) {
                            //?????????????????????????????????
                            refreshLayout.finishRefresh();
                        }else {
                            //?????????????????????????????????
                            refreshLayout.finishLoadMore();
                        }

                        NewsListResponse response = new Gson().fromJson(res, NewsListResponse.class);
                        if (null != response && response.getCode() == 0) {
                            List<NewsEntity> list = response.getPage().getList();
                            if (list != null && list.size() > 0) {//???????????????
                                if (isRefresh) {
                                    Log.e("", "??????datasets");
                                    datasets.clear();
                                    datasets = list;
                                } else {
                                    Log.e("", "??????list");
                                    datasets.addAll(list);
                                }
                                /* ???onViewCreated()?????????Adapter,???????????????setDatasets()??????datasets
                                videoAdapter = new VideoAdapter(getActivity(), datasets);*/
                                newsAdapter.setDatasets(datasets);
                                newsAdapter.notifyDataSetChanged();
                            } else {
                                if (isRefresh) {
                                    showToast("????????????????????????");
                                }else {
                                    showToast("???????????????????????????");
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
                        showToast("?????????????????????");
                    }
                });
            }
        });
    }
}