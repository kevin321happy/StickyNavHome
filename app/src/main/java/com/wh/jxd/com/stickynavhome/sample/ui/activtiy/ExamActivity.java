package com.wh.jxd.com.stickynavhome.sample.ui.activtiy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import android.support.v7.widget.Toolbar;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.sample.ui.ExamFragment;
import com.wh.jxd.com.stickynavhome.sample.widget.ViewPagerIndicator;
import com.wh.jxd.com.stickynavhome.ui.adapter.PoponeAdapter;
import com.wh.jxd.com.stickynavhome.utils.StatusBarUtil;
import com.wh.jxd.com.stickynavhome.widget.pop.CustomPopWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/26.
 */

public class ExamActivity extends BaseActivtiy implements View.OnClickListener {

    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private List<String> mDatas = Arrays.asList("独立考试", "课程考试");
    private List<Fragment> mListFragment = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ImageView mIc_back;
    private ImageView mIc_order;
    private AppCompatEditText mEt_search_view;
    private View mListPopView;
    private View mMask;
    private RecyclerView mRcv_pop;
    private CustomPopWindow mCustomPopWindow;
    private Toolbar mToolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_exam;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setStatusBarColor(this,R.color.theme_blue);
        //隐藏父控件上面的toolBar
        hideToolBar();
        mIndicator = (ViewPagerIndicator) findViewById(R.id.exam_indicator);
        mIndicator.setTabItemTitles(mDatas);
        mToolbar = (Toolbar) findViewById(R.id.exam_tool_bar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mIc_back = (ImageView) findViewById(R.id.ic_back);
        mIc_order = (ImageView) findViewById(R.id.ic_order);
        mEt_search_view = (AppCompatEditText) findViewById(R.id.et_search_view);

        mIc_back.setOnClickListener(this);
        mIc_order.setOnClickListener(this);


        for (String data : mDatas) {
            mListFragment.add(ExamFragment.newInstance(data));
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mDatas == null ? 0 : mDatas.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mListFragment.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_back:
                finish();
                break;
            case R.id.ic_order:
                showOrderPop();
                break;
            case R.id.mask:
                if (mCustomPopWindow!=null){
                    mCustomPopWindow.dismiss();
                }
                break;
        }

    }
    /**
     * 显示排序的弹窗
     */
    private void showOrderPop() {
        mListPopView = View.inflate(this, R.layout.layout_list_pop, null);
        mMask = mListPopView.findViewById(R.id.mask);
        mMask.setOnClickListener(this);
        mRcv_pop = (RecyclerView) mListPopView.findViewById(R.id.rcv);
        mRcv_pop.setLayoutManager(new LinearLayoutManager(this));
        mRcv_pop.setAdapter(new PoponeAdapter());
        mCustomPopWindow = new CustomPopWindow.Builder(this)
                .setView(mListPopView)
                .setOutsideTouchable(true)
                .setAnimationStyle(R.style.AnimUp)
                .size(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                })
                .creat();
        mCustomPopWindow.showAtLocation(mToolbar, Gravity.CENTER, 0, 0);


    }
}
