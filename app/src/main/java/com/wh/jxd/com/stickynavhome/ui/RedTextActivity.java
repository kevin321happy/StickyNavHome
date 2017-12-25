package com.wh.jxd.com.stickynavhome.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.widget.pop.CustomPopWindow;

/**
 * Created by kevin321vip on 2017/12/21.
 * 测试自定义控件效果的界面
 */

public class RedTextActivity extends BaseActivtiy implements View.OnClickListener {
    private Toolbar mToolBar;
    private Button mBt_one;
    private Button mBt_two;
    private Button mBt_three;
    private Button mBt_four;
    private RecyclerView mRcv_pop;
    private TextView mTop_view;
    private View mMask;
    private CustomPopWindow mCustomPopWindow;

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
        mToolBar = getToolBar();
        mTop_view = (TextView) findViewById(R.id.top_view);
        mBt_one = (Button) findViewById(R.id.bt1);
        mBt_two = (Button) findViewById(R.id.bt2);
        mBt_three = (Button) findViewById(R.id.bt3);
        mBt_four = (Button) findViewById(R.id.bt4);
        mBt_one.setOnClickListener(this);
        mBt_two.setOnClickListener(this);
        mBt_three.setOnClickListener(this);
        mBt_four.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                View listPopView=View.inflate(this,R.layout.layout_list_pop,null);
                mMask = listPopView.findViewById(R.id.mask);
                mMask.setOnClickListener(this);
                mRcv_pop = (RecyclerView) listPopView.findViewById(R.id.rcv);
                mRcv_pop.setLayoutManager(new LinearLayoutManager(this));
                mRcv_pop.setAdapter(new PoponeAdapter());
                mCustomPopWindow = new CustomPopWindow.Builder(this)
                        .setView(listPopView)
                        .size(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
                        .setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {

                            }
                        })
                        .creat();
                mCustomPopWindow.showAtLocation(mToolBar,Gravity.CENTER,0,0);



                break;
            case R.id.bt2:
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
            case R.id.mask:
                if (mCustomPopWindow!=null){
                    mCustomPopWindow.dismiss();
                }
                break;
        }
    }
}