package com.wh.jxd.com.stickynavhome.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.PagerBean;
import com.wh.jxd.com.stickynavhome.sample.widget.Horizontaldicator;
import com.wh.jxd.com.stickynavhome.ui.adapter.PoponeAdapter;
import com.wh.jxd.com.stickynavhome.widget.dialog.ChoosePagerDialog;
import com.wh.jxd.com.stickynavhome.widget.dialog.CreatLiveDialog;
import com.wh.jxd.com.stickynavhome.widget.multichoose.MultiLineChooseLayout;
import com.wh.jxd.com.stickynavhome.widget.pop.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/21.
 * 测试自定义控件效果的界面
 */

public class TestViewActivity extends BaseActivtiy implements View.OnClickListener {
    private Toolbar mToolBar;
    private Button mBt_one;
    private Button mBt_two;
    private Button mBt_three;
    private Button mBt_four;
    private RecyclerView mRcv_pop;
    private TextView mTop_view;
    private View mMask;
    private CustomPopWindow mCustomPopWindow;
    private CustomPopWindow mCustomPopWindow1;
    private View mListPopView;
    private View mCourseFilterView;
    private MultiLineChooseLayout mMulti_one;
    private MultiLineChooseLayout mMulti_two;
    private MultiLineChooseLayout mMulti_three;
    private View mMask1;
    private List<String> mListOne = new ArrayList<>();
    private List<String> mListTwo = new ArrayList<>();
    private List<String> mListThree = new ArrayList<>();
    private Button mBt_confirm;
    private CreatLiveDialog mLiveDialog;
    private ChoosePagerDialog mPagerDialog;
    private List<PagerBean> mPagerBeans = new ArrayList<>();
    private Button mBtn_change;
    private boolean mIsChoose;
    private Horizontaldicator mHorizontaldicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.wave_demo_layout;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        initData();
        setToolBarTitle("测试界面");
        showBack();
        mToolBar = getToolBar();
        mTop_view = (TextView) findViewById(R.id.top_view);
        mBt_one = (Button) findViewById(R.id.bt1);
        mBt_two = (Button) findViewById(R.id.bt2);
        mBt_three = (Button) findViewById(R.id.bt3);
        mBt_four = (Button) findViewById(R.id.bt4);
        mBtn_change = (Button) findViewById(R.id.btn_change);

        mHorizontaldicator = (Horizontaldicator) findViewById(R.id.horizontaldicator);
        mBt_one.setOnClickListener(this);
        mBt_two.setOnClickListener(this);
        mBt_three.setOnClickListener(this);
        mBt_four.setOnClickListener(this);
        mBtn_change.setOnClickListener(this);
        mHorizontaldicator.initIndicator(2);
    }

    /**
     * 初始化筛选的数据
     */
    private void initData() {
        mListOne.add("全部");
        mListOne.add("必修");
        mListOne.add("选修");

        mListTwo.add("全部");
        mListTwo.add("未学习");
        mListTwo.add("学习中");
        mListTwo.add("已完成");

        mListThree.add("全部");
        mListThree.add("不含考试");
        mListThree.add("考试未通过");
        mListThree.add("未考试");
        mListThree.add("考试通过");


        mPagerBeans.add(new PagerBean("销售培训班" + "2017-12-8", "2017-12-8"));
        mPagerBeans.add(new PagerBean("企业满意度调查模板" + "2017-12-6", "2017-12-6"));
        mPagerBeans.add(new PagerBean("员工幸福调查模板" + "2017-12-7", "2017-12-7"));
        mPagerBeans.add(new PagerBean("销售培训班" + "2017-12-7", "2017-12-7"));
        mPagerBeans.add(new PagerBean("员工幸福度调查" + "2017-12-9", "2017-12-9"));
        mPagerBeans.add(new PagerBean("企业经营理念" + "2017-12-10", "2017-12-10"));

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                //显示分类的弹窗
                showclazzPop();
                break;
            case R.id.bt2:
                //显示筛选的弹窗
                showFilterPop();
                break;
            case R.id.bt3:
                mLiveDialog = new CreatLiveDialog(this);
                mLiveDialog.show();
                mLiveDialog.setOnDialogClickListener(new CreatLiveDialog.onDialogClickListener() {
                    @Override
                    public void onConfirmClick(int position) {
                        Toast.makeText(TestViewActivity.this, "当前选中的位置为：" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.bt4:
                mPagerDialog = new ChoosePagerDialog(this);
                mPagerDialog.setPagerBeans(mPagerBeans);
                mPagerDialog.show();
                break;
            case R.id.mask:
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dismiss();
                }
                break;
            case R.id.mask1:
                if (mCustomPopWindow1 != null) {
                    mCustomPopWindow1.dismiss();
                }
                break;
            case R.id.bt_confirm:
                if (mCustomPopWindow1 != null) {
                    mCustomPopWindow1.dismiss();
                }
                break;

            case R.id.btn_change:
                if (mHorizontaldicator != null) {
                    int mChoosePosition = mIsChoose == true ? 0 : 1;
                    mHorizontaldicator.setChoosePosition(mChoosePosition);
                    mIsChoose = mIsChoose == true ? false : true;
                }
                break;
        }
    }

    /**
     * 显示筛选的弹窗
     */
    private void showFilterPop() {
        if (mCustomPopWindow1 == null) {
            mCourseFilterView = View.inflate(this, R.layout.course_filter_layout, null);
            mMulti_one = (MultiLineChooseLayout) mCourseFilterView.findViewById(R.id.multi_one);
            mMulti_two = (MultiLineChooseLayout) mCourseFilterView.findViewById(R.id.multi_two);
            mMulti_three = (MultiLineChooseLayout) mCourseFilterView.findViewById(R.id.multi_three);
            mBt_confirm = (Button) mCourseFilterView.findViewById(R.id.bt_confirm);
            setMultiData();
            mMask1 = mCourseFilterView.findViewById(R.id.mask1);
            mBt_confirm.setOnClickListener(this);
            mMask1.setOnClickListener(this);

            mCustomPopWindow1 = new CustomPopWindow.Builder(this)
                    .size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    .setView(mCourseFilterView)
                    .setAnimationStyle(R.style.AnimUp)
                    .setFocusable(true)
                    .creat();
            mCustomPopWindow1.showAtLocation(mToolBar, Gravity.CENTER, 0, 0);
        } else {
            mCustomPopWindow1.showAtLocation(mToolBar, Gravity.CENTER, 0, 0);
        }
    }
    /**
     * 设置Multi布局的数据
     */
    private void setMultiData() {
        mMulti_one.setList(mListOne);
        mMulti_one.setIndexItemSelected(1);
        mMulti_two.setList(mListTwo);
        mMulti_two.setIndexItemSelected(0);
        mMulti_three.setList(mListThree);
        mMulti_three.setIndexItemSelected(2);
        //设置条目点击事件
    }

    /**
     * 显示分类的弹窗
     */
    private void showclazzPop() {
        if (mCustomPopWindow == null) {
            mListPopView = View.inflate(this, R.layout.layout_list_pop, null);
            mMask = mListPopView.findViewById(R.id.mask);
            mMask.setOnClickListener(this);
            mRcv_pop = (RecyclerView) mListPopView.findViewById(R.id.rcv);
            mRcv_pop.setLayoutManager(new LinearLayoutManager(this));
            mRcv_pop.setAdapter(new PoponeAdapter());
            mCustomPopWindow = new CustomPopWindow.Builder(this)
                    .setView(mListPopView)
                    .setAnimationStyle(R.style.AnimUp)
                    .size(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                    .setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                        }
                    })
                    .creat();
            mCustomPopWindow.showAtLocation(mToolBar, Gravity.CENTER, 0, 0);
        } else {
            mCustomPopWindow.showAtLocation(mToolBar, Gravity.CENTER, 0, 0);
        }
    }
}