package com.wh.jxd.com.stickynavhome.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/21.
 * 测试自定义控件效果的界面
 */

public class RedTextActivity extends BaseActivtiy {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.wave_demo_layout;
    }

    @Override
    protected void initView() {
        setToolBarTitle("测试界面");
        showBack();



    }


}
