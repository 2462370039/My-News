package com.team8.mynews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.team8.mynews.R;
import com.team8.mynews.entity.NewsEntity;
import com.team8.mynews.entity.VideoEntity;
import com.team8.mynews.view.CircleTransform;

import java.util.List;

import xyz.doikki.videocontroller.component.PrepareView;

/**
 * @introduction: Video适配器
 * @author: T19
 * @time: 2022.08.29 18:25
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<NewsEntity> datasets;

    public void setDatasets(List<NewsEntity> datasets) {
        this.datasets = datasets;
    }

    public NewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public NewsAdapter(Context context, List<NewsEntity> datasets){
        this.mContext = context;
        this.datasets = datasets;
    }

    @Override
    public int getItemViewType(int position) {
        int type = datasets.get(position).getType();
        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1){
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_one, parent, false);
            return new ViewHolderOne(view);
        }else if(viewType == 2){
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_two, parent, false);
            return new ViewHolderTwo(view);
        }else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_three, parent, false);
            return new ViewHolderThree(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int type = datasets.get(position).getType();
        NewsEntity newsEntity = datasets.get(position);
        if (type == 1){
            ViewHolderOne vh = (ViewHolderOne) holder;

            vh.tvTitle.setText(newsEntity.getNewsTitle());
            vh.tvAuthor.setText(newsEntity.getAuthorName());
            vh.tvComment.setText(String.valueOf(newsEntity.getCommentCount()));
            vh.tvTime.setText(newsEntity.getReleaseDate());

            Picasso.with(mContext)
                    .load(newsEntity.getHeaderUrl())
                    .transform(new CircleTransform())
                    .into(vh.imgHeader);

            Picasso.with(mContext)
                    .load(newsEntity.getThumbEntities().get(0).getThumbUrl())
                    .into(vh.mThumb);
        }else if(type == 2){
            ViewHolderTwo vh = (ViewHolderTwo) holder;

            vh.tvTitle.setText(newsEntity.getNewsTitle());
            vh.tvAuthor.setText(newsEntity.getAuthorName());
            vh.tvComment.setText(String.valueOf(newsEntity.getCommentCount()));
            vh.tvTime.setText(newsEntity.getReleaseDate());

            Picasso.with(mContext)
                    .load(newsEntity.getHeaderUrl())
                    .transform(new CircleTransform())
                    .into(vh.imgHeader);

            Picasso.with(mContext)
                    .load(newsEntity.getThumbEntities().get(0).getThumbUrl())
                    .into(vh.pic1);
            Picasso.with(mContext)
                    .load(newsEntity.getThumbEntities().get(1).getThumbUrl())
                    .into(vh.pic2);
            Picasso.with(mContext)
                    .load(newsEntity.getThumbEntities().get(2).getThumbUrl())
                    .into(vh.pic3);
        }else{
            ViewHolderThree vh = (ViewHolderThree) holder;

            vh.tvTitle.setText(newsEntity.getNewsTitle());
            vh.tvAuthor.setText(newsEntity.getAuthorName());
            vh.tvComment.setText(String.valueOf(newsEntity.getCommentCount()));
            vh.tvTime.setText(newsEntity.getReleaseDate());

            Picasso.with(mContext)
                    .load(newsEntity.getHeaderUrl())
                    .transform(new CircleTransform())
                    .into(vh.imgHeader);

            Picasso.with(mContext)
                    .load(newsEntity.getThumbEntities().get(0).getThumbUrl())
                    .into(vh.mThumb);
        }
    }

    @Override
    public int getItemCount() {
        if (datasets != null && datasets.size() > 0) {
            return datasets.size();
        }
        return 0;
    }

    public  class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvComment;
        private TextView tvTime;
        private ImageView imgHeader;
        public ImageView mThumb;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvComment = itemView.findViewById(R.id.comment);
            tvTime = itemView.findViewById(R.id.time);

            imgHeader = itemView.findViewById(R.id.header);
            mThumb = itemView.findViewById(R.id.thumb);

            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }
    }
    public  class ViewHolderTwo extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvComment;
        private TextView tvTime;
        private ImageView imgHeader;
        public ImageView pic1;
        public ImageView pic2;
        public ImageView pic3;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvComment = itemView.findViewById(R.id.comment);
            tvTime = itemView.findViewById(R.id.time);

            imgHeader = itemView.findViewById(R.id.header);
            pic1 = itemView.findViewById(R.id.pic1);
            pic2 = itemView.findViewById(R.id.pic2);
            pic3 = itemView.findViewById(R.id.pic3);

            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);

        }
    }
    public  class ViewHolderThree extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvAuthor;
        private TextView tvComment;
        private TextView tvTime;
        private ImageView imgHeader;
        public ImageView mThumb;
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvComment = itemView.findViewById(R.id.comment);
            tvTime = itemView.findViewById(R.id.time);

            imgHeader = itemView.findViewById(R.id.header);
            mThumb = itemView.findViewById(R.id.thumb);

            //通过tag将ViewHolder和itemView绑定
            itemView.setTag(this);
        }
    }

}
