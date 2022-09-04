package com.team8.mynews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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
public class MyCollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datasets;

    private OnItemChildClickListener mOnItemChildClickListener;

    private OnItemClickListener mOnItemClickListener;

    public void setDatasets(List<VideoEntity> datasets) {
        this.datasets = datasets;
    }

    public MyCollectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public MyCollectAdapter(Context context, List<VideoEntity> datasets){
        this.mContext = context;
        this.datasets = datasets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_collect_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datasets.get(position);

        vh.tvTitle.setText(videoEntity.getVtitle());
        vh.tvAuthor.setText(videoEntity.getAuthor());


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
        private ImageView imgHeader;

        public int mPosition;
        public FrameLayout mPlayerContainer;
        public ImageView mThumb;
        public PrepareView mPrepareView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);

            imgHeader = itemView.findViewById(R.id.img_header);

            mPlayerContainer = itemView.findViewById(R.id.player_container);
            mPrepareView = itemView.findViewById(R.id.prepare_view);
            mThumb = mPrepareView.findViewById(xyz.doikki.videocontroller.R.id.thumb);

            if (mOnItemChildClickListener != null) {
                mPlayerContainer.setOnClickListener(this);
            }
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }

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

    public void setOnItemChildClickListener(OnItemChildClickListener onItemChildClickListener) {
        mOnItemChildClickListener = onItemChildClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
