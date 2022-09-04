package com.team8.mynews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.security.identity.AccessControlProfileId;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.team8.mynews.R;
import com.team8.mynews.adapter.listener.OnItemChildClickListener;
import com.team8.mynews.adapter.listener.OnItemClickListener;
import com.team8.mynews.api.Api;
import com.team8.mynews.api.ApiConfig;
import com.team8.mynews.api.TtitCallback;
import com.team8.mynews.entity.BaseResponse;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.view.CircleTransform;

import java.util.HashMap;
import java.util.List;

import xyz.doikki.videocontroller.component.PrepareView;

/**
 * @introduction: Video适配器
 * @author: T19
 * @time: 2022.08.29 18:25
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datasets;

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public void setDatasets(List<VideoEntity> datasets) {
        this.datasets = datasets;
    }

    public VideoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public VideoAdapter(Context context, List<VideoEntity> datasets){
        this.mContext = context;
        this.datasets = datasets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datasets.get(position);
        VideoEntity.VideoSocialEntity videoSocial = videoEntity.getVideoSocialEntity();

        vh.tvTitle.setText(videoEntity.getVtitle());
        vh.tvAuthor.setText(videoEntity.getAuthor());

        //vh.tvLike.setText(String.valueOf(videoEntity.getLikeNum()));
        //vh.tvComment.setText(String.valueOf(videoEntity.getCommentNum()));
        //vh.tvCollect.setText(String.valueOf(videoEntity.getCollectNum()));
        if (videoSocial != null){
            int likeNum = videoSocial.getLikenum();
            int commentNum = videoSocial.getCommentnum();
            int collectNum = videoSocial.getCollectnum();
            vh.tvLike.setText(String.valueOf(likeNum));
            vh.tvComment.setText(String.valueOf(commentNum));
            vh.tvCollect.setText(String.valueOf(collectNum));
            vh.flagLike = videoSocial.isFlagLike();
            vh.flagCollect = videoSocial.isFlagCollect();
            if (vh.flagLike){
                vh.tvLike.setTextColor(Color.parseColor("#E21918"));
                vh.imgLike.setImageResource(R.mipmap.like_select);
            }
            if (vh.flagCollect){
                vh.tvCollect.setTextColor(Color.parseColor("#E21918"));
                vh.imgCollect.setImageResource(R.mipmap.collect_select);
            }
        }

        Picasso.with(mContext)
                .load(videoEntity.getHeadurl())
                .transform(new CircleTransform())
                .into(vh.imgHeader);
        Picasso.with(mContext)
                .load(videoEntity.getCoverurl())
                .into(vh.mThumb);

        vh.mPosition = position;
    }

    @Override
    public int getItemCount() {
        if (datasets != null && datasets.size() > 0) {
            return datasets.size();
        }
        return 0;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvLike;
        private TextView tvComment;
        private TextView tvCollect;
        private ImageView imgLike;
        private ImageView imgComment;
        private ImageView imgCollect;
        private ImageView imgHeader;
//        private ImageView imgCover;
        private boolean flagCollect;
        private boolean flagLike;

        public int mPosition;
        public FrameLayout mPlayerContainer;
        public ImageView mThumb;
        public PrepareView mPrepareView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvLike = itemView.findViewById(R.id.like);
            tvComment = itemView.findViewById(R.id.comment);
            tvCollect = itemView.findViewById(R.id.collect);

            imgLike = itemView.findViewById(R.id.img_like);
            imgComment = itemView.findViewById(R.id.img_comment);
            imgCollect = itemView.findViewById(R.id.img_collect);

            imgHeader = itemView.findViewById(R.id.img_header);
            //imgCover = itemView.findViewById(R.id.img_cover);

            mPlayerContainer = itemView.findViewById(R.id.player_container);
            mPrepareView = itemView.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(xyz.doikki.videocontroller.R.id.thumb);

            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }

            imgCollect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int collectNum = Integer.parseInt(tvCollect.getText().toString());
                    if (flagCollect){//已收藏
                        tvCollect.setText(String.valueOf(--collectNum));
                        tvCollect.setTextColor(Color.parseColor("#161616"));
                        imgCollect.setImageResource(R.mipmap.collect);
                        updateCount(datasets.get(mPosition).getVid(), 1, !flagCollect);
                    } else {//未收藏
                        tvCollect.setText(String.valueOf(++collectNum));
                        tvCollect.setTextColor(Color.parseColor("#E21918"));
                        imgCollect.setImageResource(R.mipmap.collect_select);
                        updateCount(datasets.get(mPosition).getVid(), 1, !flagCollect);
                    }
                    flagCollect = !flagCollect;
                }
            });
            imgLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int collectNum = Integer.parseInt(tvLike.getText().toString());
                    if (flagLike){//已点赞
                        tvLike.setText(String.valueOf(--collectNum));
                        tvLike.setTextColor(Color.parseColor("#161616"));
                        imgLike.setImageResource(R.mipmap.like);
                        updateCount(datasets.get(mPosition).getVid(), 2, !flagLike);
                    } else {//未点赞
                        tvLike.setText(String.valueOf(++collectNum));
                        tvLike.setTextColor(Color.parseColor("#E21918"));
                        imgLike.setImageResource(R.mipmap.like_select);
                        updateCount(datasets.get(mPosition).getVid(), 2, !flagLike);
                    }
                    flagLike = !flagLike;
                }
            });

            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.player_container) {
                if (mOnItemChildClickListener != null) {
                    mOnItemChildClickListener.onItemChildClick(mPosition);
                }
            } else {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(mPosition);
                }
            }

        }
    }

    private void updateCount(int vid, int type, boolean flag){
        HashMap<String, Object> params = new HashMap<>();
        params.put("vid", vid);
        params.put("type", type);
        params.put("flag", flag);
        Api.config(ApiConfig.VIDEO_UPDATE_COUNT, params).postRequest(mContext, new TtitCallback() {
            @Override
            public void onSuccess(String res) {
                Gson gson = new Gson();
                BaseResponse response = gson.fromJson(res, BaseResponse.class);
                Log.e("onSuccess","点赞收藏请求"+ response.getMsg());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("onFailure","点赞收藏请求");
            }
        });
    }

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
