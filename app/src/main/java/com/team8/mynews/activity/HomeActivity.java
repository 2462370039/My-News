package com.team8.mynews.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.team8.mynews.R;
import com.team8.mynews.adapter.MyPagerAdapter;
import com.team8.mynews.entity.TabEntity;
import com.team8.mynews.fragment.CollectFragment;
import com.team8.mynews.fragment.HomeFragment;
import com.team8.mynews.fragment.MyFragment;
import com.team8.mynews.fragment.NewsFragment;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    private String[] mTitles = {"首页", "咨询", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_home_unselect,
            R.mipmap.tab_home_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_home_select,
            R.mipmap.tab_home_select};

    private ViewPager viewPager;
    private CommonTabLayout commonTabLayout;


    @Override
    protected int initLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.viewpager);
        commonTabLayout = findViewById(R.id.commonTabLayout);
    }

    @Override
    protected void initDate() {
        //添加三个Fragment
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(NewsFragment.newInstance());
        mFragments.add(MyFragment.newInstance());

        //添加Tab实体对象
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        //设置tab数据源
        commonTabLayout.setTabData(mTabEntities);
        //绑定tab的点击事件
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //切换fragment
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        //预加载
        viewPager.setOffscreenPageLimit(mFragments.size());

        //设置页面改变的监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //设置底部tab
                commonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //viewPager渲染
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mTitles, mFragments));
    }
}