package com.team8.mynews.entity;

import java.io.Serializable;

/**
 * @introduction: Video的实体类
 * @author: T19
 * @time: 2022.08.29 18:23
 */

public class VideoEntity implements Serializable {
    /**
     * vid : 15
     * vtitle : 中国新歌声：男子开口唱得太奇怪！
     * author : 灿星音乐现场
     * coverurl : https://p9-xg.byteimg.com/img/tos-cn-i-0004/19c44751e9124b069d23cddbc46e29fb~tplv-crop-center:1041:582.jpg
     * headurl : https://sf1-ttcdn-tos.pstatp.com/img/user-avatar/d58021eb3b4d5a6066eaf84fb793b360~360x360.image
     * commentNum : 12
     * likeNum : 45
     * collectNum : 6
     * playurl : http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4
     * createTime : 2020-07-19 16:05:38
     * updateTime : 2020-07-19 16:05:38
     * categoryId : 2
     * categoryName : 音乐
     * videoSocialEntity : {"commentnum":0,"likenum":0,"collectnum":0,"flagLike":false,"flagCollect":false}
     */

    private int vid;
    private String vtitle;
    private String author;
    private String coverurl;
    private String headurl;
    private int commentNum;
    private int likeNum;
    private int collectNum;
    private String playurl;
    private String createTime;
    private String updateTime;
    private int categoryId;
    private String categoryName;
    private VideoSocialEntity videoSocialEntity;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public VideoSocialEntity getVideoSocialEntity() {
        return videoSocialEntity;
    }

    public void setVideoSocialEntity(VideoSocialEntity videoSocialEntity) {
        this.videoSocialEntity = videoSocialEntity;
    }

    public static class VideoSocialEntity implements Serializable {
        /**
         * commentnum : 0
         * likenum : 0
         * collectnum : 0
         * flagLike : false
         * flagCollect : false
         */

        private int commentnum;
        private int likenum;
        private int collectnum;
        private boolean flagLike;
        private boolean flagCollect;

        public int getCommentnum() {
            return commentnum;
        }

        public void setCommentnum(int commentnum) {
            this.commentnum = commentnum;
        }

        public int getLikenum() {
            return likenum;
        }

        public void setLikenum(int likenum) {
            this.likenum = likenum;
        }

        public int getCollectnum() {
            return collectnum;
        }

        public void setCollectnum(int collectnum) {
            this.collectnum = collectnum;
        }

        public boolean isFlagLike() {
            return flagLike;
        }

        public void setFlagLike(boolean flagLike) {
            this.flagLike = flagLike;
        }

        public boolean isFlagCollect() {
            return flagCollect;
        }

        public void setFlagCollect(boolean flagCollect) {
            this.flagCollect = flagCollect;
        }
    }

    /**
     * vid : 1
     * vtitle : 青龙战甲搭配机动兵，P城上空肆意1V4
     * author : 狙击手麦克
     * coverurl : http://sf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/527d013205a74eb0a77202d7a9d5b511~tplv-crop-center:1041:582.jpg
     * headurl : https://sf1-ttcdn-tos.pstatp.com/img/pgc-image/c783a73368fa4666b7842a635c63a8bf~360x360.image
     * commentNum : 121
     * likeNum : 123
     * collectNum : 122
     * playurl : http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4
     * createTime : 2020-07-14 11:21:45
     * updateTime : 2020-07-19 12:05:33
     * categoryId : 1
     * categoryName : 游戏
     * videoSocialEntity : null
     */
    /*private int vid;
    private String vtitle;
    private String author;
    private String coverurl;
    private String headurl;
    private int commentNum;
    private int likeNum;
    private int collectNum;
    private String playurl;
    private String createTime;
    private String updateTime;
    private int categoryId;
    private String categoryName;
    private Object videoSocialEntity;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Object getVideoSocialEntity() {
        return videoSocialEntity;
    }

    public void setVideoSocialEntity(Object videoSocialEntity) {
        this.videoSocialEntity = videoSocialEntity;
    }*/

}
