package com.team8.mynews.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @introduction: videoList响应实体类
 * @author: T19
 * @time: 2022.08.29 22:07
 */
public class VideoListResponse implements Serializable {

    /**
     * msg : success
     * code : 0
     * page : {"totalCount":26,"pageSize":10,"totalPage":3,"currPage":1,"list":[{"vid":1,"vtitle":"青龙战甲搭配机动兵，P城上空肆意1V4","author":"狙击手麦克","coverurl":"http://sf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/527d013205a74eb0a77202d7a9d5b511~tplv-crop-center:1041:582.jpg","headurl":"https://sf1-ttcdn-tos.pstatp.com/img/pgc-image/c783a73368fa4666b7842a635c63a8bf~360x360.image","commentNum":121,"likeNum":123,"collectNum":122,"playurl":"http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4","createTime":"2020-07-14 11:21:45","updateTime":"2020-07-19 12:05:33","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":2,"vtitle":"【仁王2】视频攻略 2-3 虚幻魔城","author":"黑桐谷歌","coverurl":"https://lf1-xgcdn-tos.pstatp.com/img/tos-cn-p-0000/9ff7fe6c89e44ca3a22aad5744e569e3~tplv-crop-center:1041:582.jpg","headurl":"https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/8110/752553978~360x360.image","commentNum":1300,"likeNum":500,"collectNum":120,"playurl":"http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4","createTime":null,"updateTime":"2020-07-19 12:05:34","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":3,"vtitle":"最猛暴击吕布教学，这才是战神该有的样子","author":"小凡解说游戏","coverurl":"https://sf1-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/83cc11d5e26047c6b0ead149f41a8266~tplv-crop-center:1041:582.jpg","headurl":"https://p3.pstatp.com/large/a14a000405f16e51842f","commentNum":10,"likeNum":19,"collectNum":5,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4","createTime":null,"updateTime":"2020-07-19 12:05:35","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":4,"vtitle":"拳皇14：小孩输掉一分，印尼选手得意忘形","author":"E游未尽小E","coverurl":"https://sf1-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/b9553b7a28d94f27a7115157797b52ff~tplv-crop-center:1041:582.jpg","headurl":"https://sf3-ttcdn-tos.pstatp.com/img/pgc-image/f6b840d23f9e465bb5ac9e570b28321d~360x360.image","commentNum":22,"likeNum":180,"collectNum":963,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4","createTime":null,"updateTime":"2020-07-19 12:05:37","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":5,"vtitle":"阿远花210块买了条20斤的鲅鱼","author":"食味阿远","coverurl":"https://lf6-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/b821f00833b54e25ac941c7d267c2b75~tplv-crop-center:1041:582.jpg","headurl":"https://p9.pstatp.com/large/6edc0000758b2daaa6cc","commentNum":36,"likeNum":3,"collectNum":56,"playurl":"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4","createTime":null,"updateTime":"2020-07-19 12:05:55","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":6,"vtitle":"10斤用新鲜牛腿肉分享","author":"美食作家王刚","coverurl":"https://sf3-xgcdn-tos.pstatp.com/img/p1901/d9d5ae15079a8073f5cdb04b6a80777a~tplv-crop-center:1041:582.jpg","headurl":"https://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/da860012437af2fd24c2~360x360.image","commentNum":96,"likeNum":700,"collectNum":89,"playurl":"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4","createTime":null,"updateTime":"2020-07-19 12:05:56","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":7,"vtitle":"面条这样吃才叫爽，放两斤花甲一拌","author":"山药视频","coverurl":"https://lf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/51109f43de0346f68b7fd93103658aa4~tplv-crop-center:1041:582.jpg","headurl":"https://p1.pstatp.com/large/719f0015d12364d07c5b","commentNum":9,"likeNum":56,"collectNum":123,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4","createTime":null,"updateTime":"2020-07-19 12:05:58","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":8,"vtitle":"2320买2只蓝色龙虾，一只清蒸，一只刺身","author":"半吨先生","coverurl":"https://sf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/5ecedb083288435cbbf51ef04723d991~tplv-crop-center:1041:582.jpg","headurl":"https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/dae9000ee0a875804aae~360x360.image","commentNum":98,"likeNum":546,"collectNum":23,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4","createTime":null,"updateTime":"2020-07-19 12:05:59","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":9,"vtitle":"122块钱买了一大堆海螺，想试试","author":"韩小浪","coverurl":"https://lf6-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/28b99fcd52bf4e45a7f4a28ab2f21685~tplv-crop-center:1041:582.jpg","headurl":"https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/b77400114e944ff697e4~360x360.image","commentNum":156,"likeNum":56,"collectNum":856,"playurl":"http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4","createTime":null,"updateTime":"2020-07-19 12:06:00","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":10,"vtitle":"10块钱的大鲍鱼随便搞50个来烧烤","author":"阿壮锅","coverurl":"https://sf6-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/edcc153551794b67a2de2683ff8b0ee2~tplv-crop-center:1041:582.jpg","headurl":"https://sf3-ttcdn-tos.pstatp.com/img/pgc-image/7cbcfbb82fa142058fd45549d3b63a5b~360x360.image","commentNum":85,"likeNum":4566,"collectNum":100,"playurl":"http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4","createTime":null,"updateTime":"2020-07-19 12:06:01","categoryId":3,"categoryName":"美食","videoSocialEntity":null}]}
     */

    private String msg;
    private int code;
    private PageBean page;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public static class PageBean {
        /**
         * totalCount : 26
         * pageSize : 10
         * totalPage : 3
         * currPage : 1
         * list : [{"vid":1,"vtitle":"青龙战甲搭配机动兵，P城上空肆意1V4","author":"狙击手麦克","coverurl":"http://sf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/527d013205a74eb0a77202d7a9d5b511~tplv-crop-center:1041:582.jpg","headurl":"https://sf1-ttcdn-tos.pstatp.com/img/pgc-image/c783a73368fa4666b7842a635c63a8bf~360x360.image","commentNum":121,"likeNum":123,"collectNum":122,"playurl":"http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4","createTime":"2020-07-14 11:21:45","updateTime":"2020-07-19 12:05:33","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":2,"vtitle":"【仁王2】视频攻略 2-3 虚幻魔城","author":"黑桐谷歌","coverurl":"https://lf1-xgcdn-tos.pstatp.com/img/tos-cn-p-0000/9ff7fe6c89e44ca3a22aad5744e569e3~tplv-crop-center:1041:582.jpg","headurl":"https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/8110/752553978~360x360.image","commentNum":1300,"likeNum":500,"collectNum":120,"playurl":"http://vfx.mtime.cn/Video/2019/03/21/mp4/190321153853126488.mp4","createTime":null,"updateTime":"2020-07-19 12:05:34","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":3,"vtitle":"最猛暴击吕布教学，这才是战神该有的样子","author":"小凡解说游戏","coverurl":"https://sf1-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/83cc11d5e26047c6b0ead149f41a8266~tplv-crop-center:1041:582.jpg","headurl":"https://p3.pstatp.com/large/a14a000405f16e51842f","commentNum":10,"likeNum":19,"collectNum":5,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4","createTime":null,"updateTime":"2020-07-19 12:05:35","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":4,"vtitle":"拳皇14：小孩输掉一分，印尼选手得意忘形","author":"E游未尽小E","coverurl":"https://sf1-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/b9553b7a28d94f27a7115157797b52ff~tplv-crop-center:1041:582.jpg","headurl":"https://sf3-ttcdn-tos.pstatp.com/img/pgc-image/f6b840d23f9e465bb5ac9e570b28321d~360x360.image","commentNum":22,"likeNum":180,"collectNum":963,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319212559089721.mp4","createTime":null,"updateTime":"2020-07-19 12:05:37","categoryId":1,"categoryName":"游戏","videoSocialEntity":null},{"vid":5,"vtitle":"阿远花210块买了条20斤的鲅鱼","author":"食味阿远","coverurl":"https://lf6-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/b821f00833b54e25ac941c7d267c2b75~tplv-crop-center:1041:582.jpg","headurl":"https://p9.pstatp.com/large/6edc0000758b2daaa6cc","commentNum":36,"likeNum":3,"collectNum":56,"playurl":"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318231014076505.mp4","createTime":null,"updateTime":"2020-07-19 12:05:55","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":6,"vtitle":"10斤用新鲜牛腿肉分享","author":"美食作家王刚","coverurl":"https://sf3-xgcdn-tos.pstatp.com/img/p1901/d9d5ae15079a8073f5cdb04b6a80777a~tplv-crop-center:1041:582.jpg","headurl":"https://sf3-ttcdn-tos.pstatp.com/img/mosaic-legacy/da860012437af2fd24c2~360x360.image","commentNum":96,"likeNum":700,"collectNum":89,"playurl":"http://vfx.mtime.cn/Video/2019/03/18/mp4/190318214226685784.mp4","createTime":null,"updateTime":"2020-07-19 12:05:56","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":7,"vtitle":"面条这样吃才叫爽，放两斤花甲一拌","author":"山药视频","coverurl":"https://lf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/51109f43de0346f68b7fd93103658aa4~tplv-crop-center:1041:582.jpg","headurl":"https://p1.pstatp.com/large/719f0015d12364d07c5b","commentNum":9,"likeNum":56,"collectNum":123,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319104618910544.mp4","createTime":null,"updateTime":"2020-07-19 12:05:58","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":8,"vtitle":"2320买2只蓝色龙虾，一只清蒸，一只刺身","author":"半吨先生","coverurl":"https://sf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/5ecedb083288435cbbf51ef04723d991~tplv-crop-center:1041:582.jpg","headurl":"https://sf1-ttcdn-tos.pstatp.com/img/mosaic-legacy/dae9000ee0a875804aae~360x360.image","commentNum":98,"likeNum":546,"collectNum":23,"playurl":"http://vfx.mtime.cn/Video/2019/03/19/mp4/190319125415785691.mp4","createTime":null,"updateTime":"2020-07-19 12:05:59","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":9,"vtitle":"122块钱买了一大堆海螺，想试试","author":"韩小浪","coverurl":"https://lf6-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/28b99fcd52bf4e45a7f4a28ab2f21685~tplv-crop-center:1041:582.jpg","headurl":"https://sf6-ttcdn-tos.pstatp.com/img/mosaic-legacy/b77400114e944ff697e4~360x360.image","commentNum":156,"likeNum":56,"collectNum":856,"playurl":"http://vfx.mtime.cn/Video/2019/03/17/mp4/190317150237409904.mp4","createTime":null,"updateTime":"2020-07-19 12:06:00","categoryId":3,"categoryName":"美食","videoSocialEntity":null},{"vid":10,"vtitle":"10块钱的大鲍鱼随便搞50个来烧烤","author":"阿壮锅","coverurl":"https://sf6-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/edcc153551794b67a2de2683ff8b0ee2~tplv-crop-center:1041:582.jpg","headurl":"https://sf3-ttcdn-tos.pstatp.com/img/pgc-image/7cbcfbb82fa142058fd45549d3b63a5b~360x360.image","commentNum":85,"likeNum":4566,"collectNum":100,"playurl":"http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4","createTime":null,"updateTime":"2020-07-19 12:06:01","categoryId":3,"categoryName":"美食","videoSocialEntity":null}]
         */

        private int totalCount;
        private int pageSize;
        private int totalPage;
        private int currPage;
        private List<VideoEntity> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public List<VideoEntity> getList() {
            return list;
        }

        public void setList(List<VideoEntity> list) {
            this.list = list;
        }
    }
}
