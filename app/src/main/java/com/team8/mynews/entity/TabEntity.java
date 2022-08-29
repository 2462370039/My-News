package com.team8.mynews.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Tab封装实体类
 *  title          tab名
 *  selectedIcon   选择显示图标
 *  unSelectedIcon 未选择时显示图标
 */
public class TabEntity implements CustomTabEntity {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
