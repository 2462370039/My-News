package com.team8.mynews.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.team8.mynews.R;
import com.team8.mynews.adapter.MyCollectAdapter;
import com.team8.mynews.adapter.listener.OnItemChildClickListener;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.MyCollectResponse;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.entity.VideoListResponse;
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

public class MyCollectActivity extends BaseActivity implements OnItemChildClickListener {


    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyCollectAdapter myCollectAdapter;

    /**
     * DKPlayer
     */
    protected VideoView mVideoView;
    protected StandardVideoController mController;
    protected ErrorView mErrorView;
    protected CompleteView mCompleteView;
    protected TitleView mTitleView;

    /**
     * 当前播放的位置
     */
    protected int mCurPos = -1;
    /**
     * 上次播放的位置，用于页面切回来之后恢复播放
     */
    protected int mLastPos = mCurPos;

    List<VideoEntity> datasets = new ArrayList<>();



    @Override
    protected int initLayout() {
        return R.layout.activity_my_collect;
    }

    @Override
    protected void initView() {
        initVideoView();
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void initDate() {
        //新建一个线性布局管理器   //getActivity() --> HomeActivity
        linearLayoutManager = new LinearLayoutManager(this);
        //线性布局管理器 设置item垂直排列
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //RV必选项1：布局管理器
        recyclerView.setLayoutManager(linearLayoutManager);

        myCollectAdapter = new MyCollectAdapter(this);
        myCollectAdapter.setOnItemChildClickListener(this);
        recyclerView.setAdapter(myCollectAdapter);

        //view离开Window时释放VideoView
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
        getMyCollectList();
    }

    /**
     * 初始化播放器
     */
    protected void initVideoView() {
        mVideoView = new VideoView(this);
        mVideoView.setOnStateChangeListener(new VideoView.SimpleOnStateChangeListener() {
            @Override
            public void onPlayStateChanged(int playState) {
                //监听VideoViewManager释放，重置状态
                if (playState == VideoView.STATE_IDLE) {
                    Utils.removeViewFormParent(mVideoView);
                    mLastPos = mCurPos;
                    mCurPos = -1;
                }
            }
        });
        mController = new StandardVideoController(this);
        mErrorView = new ErrorView(this);
        mController.addControlComponent(mErrorView);
        mCompleteView = new CompleteView(this);
        mController.addControlComponent(mCompleteView);
        mTitleView = new TitleView(this);
        mController.addControlComponent(mTitleView);
        mController.addControlComponent(new VodControlView(this));
        mController.addControlComponent(new GestureView(this));
        mController.setEnableOrientation(true);
        mVideoView.setVideoController(mController);
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    /**
     * 由于onPause必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onPause的逻辑
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
     * 由于onResume必须调用super。故增加此方法，
     * 子类将会重写此方法，改变onResume的逻辑
     */
    protected void resume() {
        if (mLastPos == -1)
            return;
        //恢复上次播放的位置
        startPlay(mLastPos);
    }

    /**
     * PrepareView被点击
     */
    @Override
    public void onItemChildClick(int position) {
        startPlay(position);
    }

    /**
     * 开始播放
     * @param position 列表位置
     */
    protected void startPlay(int position) {
        if (mCurPos == position) return;
        if (mCurPos != -1) {
            releaseVideoView();
        }
        VideoEntity videoBean = datasets.get(position);

        mVideoView.setUrl(videoBean.getPlayurl());
        mTitleView.setTitle(videoBean.getVtitle());
        View itemView = linearLayoutManager.findViewByPosition(position);
        if (itemView == null) return;
        MyCollectAdapter.ViewHolder viewHolder = (MyCollectAdapter.ViewHolder) itemView.getTag();
        //把列表中预置的PrepareView添加到控制器中，注意isDissociate此处只能为true, 请点进去看isDissociate的解释
        mController.addControlComponent(viewHolder.mPrepareView, true);
        Utils.removeViewFormParent(mVideoView);
        viewHolder.mPlayerContainer.addView(mVideoView, 0);
        //播放之前将VideoView添加到VideoViewManager以便在别的页面也能操作它
        getVideoViewManager().add(mVideoView, Tag.LIST);
        mVideoView.start();
        mCurPos = position;

    }

    private void releaseVideoView() {
        mVideoView.release();
        if (mVideoView.isFullScreen()) {
            mVideoView.stopFullScreen();
        }
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        mCurPos = -1;
    }

    /**
     * 获取VideoList数据
     */
    private void getMyCollectList(){
        HashMap<String, Object> params = new HashMap<>();
        //请求数据
        //Api.config(ApiConfig.VIDEO_LIST, params).getRequest(this, new TtitCallback() {
        Api.config(ApiConfig.VIDEO_MY_COLLECT, params).getRequest(this, new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                runOnUiThread(new Runnable() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void run() {
                        //VideoListResponse response = new Gson().fromJson(res, VideoListResponse.class);
                        MyCollectResponse response = new Gson().fromJson(res, MyCollectResponse.class);
                        if (null != response && response.getCode() == 0) {
                            //datasets = response.getPage().getList();
                            datasets = response.getList();
                            myCollectAdapter.setDatasets(datasets);
                            myCollectAdapter.notifyDataSetChanged();
                        } else {
                            showToast("你的收藏为空！");
                        }
                    }
                });
            }
            @Override
            public void onFailure(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("onFailure", "数据加载失败");
                        showToast("数据加载失败！");
                    }
                });
            }
        });

    }
}