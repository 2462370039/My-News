package com.team8.mynews.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @introduction: 取消页面滑动切换效果的ViewPager
 * @author: T19
 * @time: 2022.08.25 06:29
 */
public class FixedViewPager extends ViewPager {
    public FixedViewPager(@NonNull Context context) {
        super(context);
    }

    public FixedViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setCurrentItem(int item) {
        //true 滑动效果， false 取消滑动效果
        super.setCurrentItem(item, false);
    }
}
