package com.team8.mynews.fragment;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.team8.mynews.R;
import com.team8.mynews.activity.LoginActivity;
import com.team8.mynews.adapter.VideoAdapter;
import com.team8.mynews.adapter.listener.OnItemChildClickListener;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.entity.VideoListResponse;
import com.team8.mynews.utils.StringUtils;
import com.team8.mynews.utils.Tag;
import com.team8.mynews.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videocontroller.component.ErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.TitleView;
import xyz.doikki.videocontroller.component.VodControlView;
import xyz.doikki.videoplayer.player.VideoView;


public class VideoFragment extends BaseFragment implements OnItemChildClickListener {


    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private    LinearLayoutManager linearLayoutManager;
    private VideoAdapter videoAdapter;

    private int pageNum = 1;
    private int categoryId;

    /**
     * DKPlayer
     */
    protected VideoView mVideoView;
    protected StandardVideoController mController;
    protected ErrorView mErrorView;
    protected CompleteView mCompleteView;
    protected TitleView mTitleView;

    /**
     * ?????????????????????
     */
    protected int mCurPos = -1;
    /**
     * ???????????????????????????????????????????????????????????????
     */
    protected int mLastPos = mCurPos;

    List<VideoEntity> datasets = new ArrayList<>();

    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance(int categoryId) {
        VideoFragment fragment = new VideoFragment();
        fragment.categoryId = categoryId;
        return fragment;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        initVideoView();

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

        videoAdapter = new VideoAdapter(getActivity());
        videoAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(videoAdapter);

        //view??????Window?????????VideoView
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                FrameLayout playerContainer = view.findViewById(R.id.player_container);
                View v = playerContainer.getChildAt(0);
                if (v != null && v == mVideoView && !mVideoView.isFullScreen()) {
                    releaseVideoView();
                }
            }
        });

        //??????????????????
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getVideoList(true);
                Log.e("RorL", "????????????");
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getVideoList(false);
                Log.e("RorL", "????????????");
            }
        });
        //?????????????????????????????????
        getVideoList(true);
        /*
        //??????item???????????? datasets
        List<VideoEntity> datasets = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //??????videoEntity?????????
            VideoEntity videoEntity = new VideoEntity();

            videoEntity.setTitle("?????????????????????????????????????????????");
            videoEntity.setName("?????????????????????");
            videoEntity.setLikeCount(i*2);
            videoEntity.setCollectCount(i*4);
            videoEntity.setCommentCount(i*6);

            datasets.add(videoEntity);
        }

        //??????Video?????????
        VideoAdapter videoAdapter = new VideoAdapter(getActivity(), datasets);
        //RV?????????2??????????????????
        recyclerView.setAdapter(videoAdapter);*/
    }

    /**
     * ??????????????????
     */
    protected void initVideoView() {
        mVideoView = new VideoView(getActivity());
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //??????VideoViewManager?????????????????????
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
        mController = new StandardVideoController(getActivity());
        mErrorView = new ErrorView(getActivity());
        mController.addControlComponent(mErrorView);
        mCompleteView = new CompleteView(getActivity());
        mController.addControlComponent(mCompleteView);
        mTitleView = new TitleView(getActivity());
        mController.addControlComponent(mTitleView);
        mController.addControlComponent(new VodControlView(getActivity()));
        mController.addControlComponent(new GestureView(getActivity()));
        mController.setEnableOrientation(true);
        mVideoView.setVideoController(mController);
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    /**
     * ??????onPause????????????super????????????????????????
     * ????????????????????????????????????onPause?????????
     */
    protected void pause() {
        releaseVideoView();
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    /**
     * ??????onResume????????????super????????????????????????
     * ????????????????????????????????????onResume?????????
     */
    protected void resume() {
        if (mLastPos == -1)
            return;
        //???????????????????????????
        startPlay(mLastPos);
    }

    /**
     * PrepareView?????????
     */
    @Override
    public void onItemChildClick(int position) {
        startPlay(position);
    }

    /**
     * ????????????
     * @param position ????????????
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        VideoEntity videoBean = datasets.get(position);
        //????????????
//        String proxyUrl = ProxyVideoCacheManager.getProxy(getActivity()).getProxyUrl(videoBean.getUrl());
//        mVideoView.setUrl(proxyUrl);

        mVideoView.setUrl(videoBean.getPlayurl());
        mTitleView.setTitle(videoBean.getVtitle());
        View itemView = linearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        VideoAdapter.ViewHolder viewHolder = (VideoAdapter.ViewHolder) itemView.getTag();
        //?????????????????????PrepareView??????????????????????????????isDissociate???????????????true, ???????????????isDissociate?????????
        mController.addControlComponent(viewHolder.mPrepareView, true);
        Utils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        //???????????????VideoView?????????VideoViewManager????????????????????????????????????
        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;

    }

    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if(getActivity().getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }

    /**
     * ??????VideoList??????
     * @param isRefresh true???????????????false????????????
     */
    private void getVideoList(boolean isRefresh){
        String token = getStringFromSp("token");
        //token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNjgiLCJpYXQiOjE2NjE3ODEzNTgsImV4cCI6MTY2MjM4NjE1OH0.NxQ4o2g-6HLwGRPyLZLCX3RDcXk4RY_icQtsRNYtr_E810WyVsIBqjKcGJCRSZbqB9HqQW_bpYHGwGmfhumQ6w";
        //Log.e("token", token);
        if (!StringUtils.isEmpty(token)) {
            HashMap<String, Object> params = new HashMap<>();
            //??????5????????????????????????
            params.put("page", pageNum);
            params.put("limit", ApiConfig.PAGE_SIZE);
            params.put("categoryId", categoryId);
            //????????????
            //Api.config(ApiConfig.VIDEO_LIST, params).getRequest(new TtitCallback() {
            Api.config(ApiConfig.VIDEO_LIST_BY_CATEGORY, params).getRequest(getActivity(), new TtitCallback() {
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

                            VideoListResponse response = new Gson().fromJson(res, VideoListResponse.class);
                            if (null != response && response.getCode() == 0) {
                                List<VideoEntity> list = response.getPage().getList();
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
                                    videoAdapter.setDatasets(datasets);
                                    videoAdapter.notifyDataSetChanged();
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
        }else {
            navigateTo(LoginActivity.class);
            showToastSync("token??????,???????????????");
        }

    }
}