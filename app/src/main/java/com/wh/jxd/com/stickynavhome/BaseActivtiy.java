package com.wh.jxd.com.stickynavhome;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


import com.wh.jxd.com.stickynavhome.utils.StatusBarUtil;
import com.zhy.autolayout.AutoLayoutActivity;


/**
 * Created by kevin321vip on 2017/9/27.
 */
public abstract class BaseActivtiy extends AutoLayoutActivity  {
    private static final String TAG = "BaseActivtiy";
    private SparseArray<Long> mLastClickTimes;
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;

    /**
     * 网络类型
     */
    private int netMobile;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(getLayoutId());
        init();

        initView();
    }
    /**
     * 抽象方法获取布局ID
     * @return
     */
    protected abstract int getLayoutId();



    /**
     * 判断有无网络
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;
        }
        return false;
    }
    protected abstract void initView();
    /**
     * 初始化界面操作
     */
    public void init() {
        // 设置了全屏的界面需要排除
        //设置状态栏颜色
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        StatusBarUtil.StatusBarLightMode(this);
        mToolbar = (Toolbar) findViewById(R.id.base_tool_bar);
        mToolbarTitle = (TextView) findViewById(R.id.common_toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.common_toolbar_subtitle);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (mToolbarSubTitle != null) {
            mToolbarSubTitle.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != getToolBar() && isShowBacking()) {
            showBack();
        }
    }
    /**
     * 显示返回键
     */
    public void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getToolBar().setNavigationIcon(R.drawable.fanhui);
        getToolBar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }

    public void setSubTitle(String subTitle) {
        TextView subTitle1 = getSubTitle();
        if (subTitle1 != null) {
            subTitle1.setVisibility(View.VISIBLE);
            subTitle1.setText(subTitle);
        }
    }
    /**
     * 设置头部标题
     *
     * @param title
     */
    public void setToolBarTitle(CharSequence title) {
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        } else {
            getToolBar().setTitle(title);
            setSupportActionBar(getToolBar());
        }
    }
    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    /**
     * 双击监听
     * @param res
     */
    public void setImmersionState(int res) {

    }
    /**
     * APP字体大小，不随系统的字体大小的变化而变化的方法
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 检查是否可执行点击操作 防重复点击
     * @return 返回true则可执行
     */
    protected boolean checkClick(int id) {
        Long lastTime = mLastClickTimes.get(id);
        Long thisTime = System.currentTimeMillis();
        mLastClickTimes.put(id, thisTime);
        return !(lastTime != null && thisTime - lastTime < 800);
    }
    /**
     * 隐藏ToolBar
     */
    public void hideToolBar() {
        Toolbar toolBar = getToolBar();
        if (toolBar != null) {
            toolBar.setVisibility(View.GONE);
        }
    }

    /**
     * 显示ToolBarO
     */
    public void showToolBar() {
        Toolbar toolBar = getToolBar();
        if (toolBar != null) {
            toolBar.setVisibility(View.VISIBLE);
        }
    }
    /**
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
//    public  boolean isSystemBarTranclucent();
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 获得toolbar
     *
     * @return
     */
    public Toolbar getToolBar() {
        return (Toolbar) findViewById(R.id.base_tool_bar);
    }
}
