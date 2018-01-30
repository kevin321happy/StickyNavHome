package com.wh.jxd.com.stickynavhome.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.CommonMenuBean;
import com.wh.jxd.com.stickynavhome.sample.ui.activtiy.CallCentryActivtiy;
import com.wh.jxd.com.stickynavhome.sample.ui.activtiy.CampaignActivity;
import com.wh.jxd.com.stickynavhome.sample.ui.activtiy.ExamActivity;
import com.wh.jxd.com.stickynavhome.sample.ui.activtiy.SettingTwoCodeActivity;
import com.wh.jxd.com.stickynavhome.sample.ui.activtiy.TrainingHelperActivity;

import com.wh.jxd.com.stickynavhome.sample.widget.HPageIndicator;
import com.wh.jxd.com.stickynavhome.sample.widget.PageGridView;
import com.wh.jxd.com.stickynavhome.ui.adapter.CourseClassAdapter;
import com.wh.jxd.com.stickynavhome.utils.StatusBarUtil;
import com.wh.jxd.com.stickynavhome.widget.stickynavLayout.CustomViewPagerIndicator;
import com.wh.jxd.com.stickynavhome.widget.stickynavLayout.StickyNavLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StickyNavLayout.onScrollListenre, CustomViewPagerIndicator.onPagerChangeListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private String[] mTitles = new String[]{"射手", "坦克", "法师", "辅助", "战士", "打野"};
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
    private PageGridView rcv_course;
    private TextView study_info;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private CourseClassAdapter mClassAdapter;
    private HPageIndicator mHPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarColor(this, R.color.transparent);
        //设置状态栏黑色字体
        StatusBarUtil.StatusBarLightMode(this);
        setContentView(R.layout.activity_main);
        initView();
        initDatas();
    }

    private void initDatas() {
        mIndicator.setTitles(mTitles);
        mIndicator.setmViewPager(mViewPager, 0);
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
        //横向滑动的Recycle
        rcv_course = (PageGridView) findViewById(R.id.rcv_course);
        //水平的指示器
        mHPageIndicator = (HPageIndicator) findViewById(R.id.horizontaldicator);
        study_info = (TextView) findViewById(R.id.iv_study_info);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.nv_main_navigation);
        mNavigationView.setNavigationItemSelectedListener(this);

        study_info.setOnClickListener(this);
        status_bar = findViewById(R.id.status_bar);
        status_bar.setAlpha(0);
        sticky_layout.setMonScrollListener(this);
        initRecycle();
    }

    private void initRecycle() {
        mClassAdapter = new CourseClassAdapter();

        setData(mClassAdapter);


//        //设置指示器
//        rcv_course.setIndicator((Horizontaldicator) findViewById(R.id.horizontaldicator));
////        // 设置行数和列数
//        rcv_course.setPageSize(1, 1);
//
//        // 设置页间距
//        rcv_course.setPageMargin(40);
//        CourseClassAdapter classAdapter = new CourseClassAdapter();
//        rcv_course.setAdapter(classAdapter);



//        rcv_course.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        CourseClassAdapter classAdapter = new CourseClassAdapter();
//        rcv_course.setAdapter(classAdapter);
    }
    private void setData(CourseClassAdapter classAdapter) {
        List<CommonMenuBean> itemList = new ArrayList();
        for (int i = 0; i < 6; i++) {
            itemList.add(new CommonMenuBean(R.drawable.ic_lyon,"裘马"));
        }
        classAdapter.setMenuBeans(itemList);
        //设置适配器
        rcv_course.setAdapter(classAdapter);
        //设置指示器
        rcv_course.setPageIndicator(mHPageIndicator);


    }

    /**
     * 滑动的监听
     *
     * @param precent
     */
    @Override
    public void onScrolling(float precent) {
        Toast.makeText(this, "回调出来的透明度：" + precent, Toast.LENGTH_SHORT).show();
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
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CampaignActivity.class);
        startActivity(intent);
    }
    //抽屉菜单的监听
    /**
     * 侧拉菜单的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_one:
                //测试弹窗的
                startActivity(new Intent(this, TestViewActivity.class));
                break;
            case R.id.nav_two:
                //我的调研
                startActivity(new Intent(this, CampaignActivity.class));
                break;
            case R.id.nav_three:
                //我的考试
                startActivity(new Intent(this, ExamActivity.class));
                break;
            case R.id.nav_four:
                //内训助手
                intent = new Intent(this, TrainingHelperActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
                break;
            case R.id.nav_five:
                //企业活动
                intent = new Intent(this, TrainingHelperActivity.class);
                intent.putExtra("type", "2");
                startActivity(intent);
                break;
            case R.id.nav_call_centry:
                //跳转到呼叫中心
                intent=new Intent(this,CallCentryActivtiy.class);
                startActivity(intent);
                break;
            case R.id.nav_set_two_code:
                intent=new Intent(this,SettingTwoCodeActivity.class);
                startActivity(intent);
                break;

        }
        item.setChecked(true);
        mDrawerLayout.closeDrawers();
        return true;
    }
}
