package com.wh.jxd.com.stickynavhome.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.utils.StatusBarUtil;
import com.wh.jxd.com.stickynavhome.widget.stickynavLayout.CustomViewPagerIndicator;
import com.wh.jxd.com.stickynavhome.widget.stickynavLayout.MyViewPagerIndicator;
import com.wh.jxd.com.stickynavhome.widget.stickynavLayout.StickyNavLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StickyNavLayout.onScrollListenre, CustomViewPagerIndicator.onTabClickListener, CustomViewPagerIndicator.onPagerChangeListener {

    private String[] mTitles = new String[]{"射手", "坦克", "法师", "辅助", "战士","打野"};
    private CustomViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private Toolbar toolbar;
    private StickyNavLayout sticky_layout;
    private View decorView;
    private View status_bar;
    private RelativeLayout topView;
    private ImageView tv_image;
    private RecyclerView rcv_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarColor(this, R.color.transparent);
        //设置状态栏黑色字体
        StatusBarUtil.StatusBarLightMode(this);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //设置透明的状态栏
//        StatusBarUtil.setStatusBarColor(this, R.color.transparent);
        initView();
        initDatas();
//        initEvents();
    }

    private void initEvents() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initDatas() {

        mIndicator.setTitles(mTitles);
        mIndicator.setmOnTabClickListener(this);

        mIndicator.setmViewPager(mViewPager,0);
        mIndicator.setmOnPagerChangeListener(this);

        for (int i = 0; i < mTitles.length; i++) {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    /**
     * 初始化
     */
    private void initView() {
        sticky_layout = (StickyNavLayout) findViewById(R.id.sticky_layout);
        topView = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
        mIndicator = (CustomViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        tv_image = (ImageView) findViewById(R.id.bg_image);
        rcv_course = (RecyclerView) findViewById(R.id.rcv_course);
        status_bar = findViewById(R.id.status_bar);
        status_bar.setAlpha(0);
        sticky_layout.setMonScrollListener(this);

        initRecycle();


    }

    private void initRecycle() {
        rcv_course.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        CourseClassAdapter classAdapter = new CourseClassAdapter();
        rcv_course.setAdapter(classAdapter);
    }

    /**
     * 滑动的监听
     *
     * @param precent
     */
    @Override
    public void onScrolling(float precent) {
        Toast.makeText(this, "回调出来的透明度：" + precent, Toast.LENGTH_SHORT).show();
//        ToastUtils.show("回调出来的透明度：" + precent);
        float offset = 1 - precent / 1000;
        if (0 == offset) {
            toolbar.setAlpha(1);
            status_bar.setAlpha(1);
            toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));//使用colors.xml文件中的颜色
        } else {
            toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.transparent));//使用colors.xml文件中的颜色
            //Toolbar背景色透明度
            toolbar.setAlpha(offset);
                    tv_image.setAlpha(offset);
            status_bar.setAlpha(1 - offset);
        }
    }

    /**
     * 指示器的点击监听
     *
     * @param position
     */
//    @Override
    @Override
    public void onTabClick(int position) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position);
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mViewPager!=null){
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
