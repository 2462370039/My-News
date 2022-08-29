package com.team8.mynews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.team8.mynews.R;
import com.team8.mynews.entity.VideoEntity;

import java.util.List;

/**
 * @introduction: Video适配器
 * @author: T19
 * @time: 2022.08.29 18:25
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<VideoEntity> datasets;

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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        VideoEntity videoEntity = datasets.get(position);

        vh.tvTitle.setText(videoEntity.getVtitle());
        vh.tvAuthor.setText(videoEntity.getAuthor());
        vh.tvLike.setText(String.valueOf(videoEntity.getLikeNum()));
        vh.tvComment.setText(String.valueOf(videoEntity.getCommentNum()));
        vh.tvCollect.setText(String.valueOf(videoEntity.getCollectNum()));

        Picasso.with(mContext).load(videoEntity.getHeadurl()).into(vh.imgHeader);
        Picasso.with(mContext).load(videoEntity.getCoverurl()).into(vh.imgCover);
    }

    @Override
    public int getItemCount() {
        return datasets.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvLike;
        private TextView tvComment;
        private TextView tvCollect;
        private ImageView imgHeader;
        private ImageView imgCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvLike = itemView.findViewById(R.id.like);
            tvComment = itemView.findViewById(R.id.comment);
            tvCollect = itemView.findViewById(R.id.collect);

            imgHeader = itemView.findViewById(R.id.img_header);
            imgCover = itemView.findViewById(R.id.img_cover);

        }
    }
}
