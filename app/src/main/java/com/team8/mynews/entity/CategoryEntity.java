package com.team8.mynews.entity;

import java.io.Serializable;

/**
 * @introduction: VideoCategory实体类
 * @author: T19
 * @time: 2022.08.31 17:19
 */
public class CategoryEntity implements Serializable {
    /**
     * categoryId : 1
     * categoryName : 游戏
     */

    private int categoryId;
    private String categoryName;

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
}
