package com.wh.jxd.com.stickynavhome.sample.ui.activtiy;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.CommonMenuBean;
import com.wh.jxd.com.stickynavhome.sample.ui.adapter.CommonMenuAdapter;
import com.wh.jxd.com.stickynavhome.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/27.
 * 内训助手界面
 */

public class TrainingHelperActivity extends BaseActivtiy implements View.OnClickListener, CommonMenuAdapter.onRecycleListener {

    private ImageView mIv_finish;
    private RecyclerView mRcv_menu;
    private List<CommonMenuBean> mMenuBeans = new ArrayList<>();
    private String mType;
    private CommonMenuAdapter mCommonMenuAdapter;
    private TextView mTv_child_title;
    private TextView mTv_train_title;

    @Override
    protected int getLayoutId() {
        StatusBarUtil.setStatusBarColor(this, R.color.white);
        return R.layout.activity_training_helper;
    }
    @Override
    protected void initView() {
        mType = getIntent().getStringExtra("type");
        mIv_finish = (ImageView) findViewById(R.id.iv_finish);
        mRcv_menu = (RecyclerView) findViewById(R.id.rcv_menu);
        mTv_train_title = (TextView) findViewById(R.id.tv_traiin_title);
        mTv_child_title = (TextView) findViewById(R.id.tv_traiin_child_title);
        initData();
        //点击返回上级界面
        mIv_finish.setOnClickListener(this);
        mRcv_menu.setLayoutManager(new GridLayoutManager(this, 3));
        mCommonMenuAdapter = new CommonMenuAdapter();
        mCommonMenuAdapter.setCommonMenuBeans(mMenuBeans, mType);
        mRcv_menu.setAdapter(mCommonMenuAdapter);
        mCommonMenuAdapter.setOnRecycleListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mMenuBeans.clear();
        if ("1".equals(mType)) {
            mTv_train_title.setText("内训助手");
            mTv_child_title.setVisibility(View.VISIBLE);
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "有声课件"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "签到工具"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "及时问卷"));
        } else if ("2".equals(mType)) {
            mTv_train_title.setText("企业活动");
            mTv_child_title.setVisibility(View.GONE);
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "企业文化"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "市场策略"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "销售精英"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "今日头条"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "日精进"));
            mMenuBeans.add(new CommonMenuBean(R.drawable.ic_user, "员工风采"));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_finish:
                finish();
                break;
        }
    }
    /**
     * Recycle的条目点击事件
     * @param position
     * @param type
     */
    @Override
    public void onItemClickListener(int position, String type) {
        Intent intent;
        switch (position) {
            case 0:
                if ("1".equals(type)){
                    Toast.makeText(this, "有声课件", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this, "企业文化", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if ("1".equals(type)){
                    Toast.makeText(this, "签到工具", Toast.LENGTH_SHORT).show();
                    intent=new Intent(this,ToolSettingActivity.class);
                    intent.putExtra("type","1");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "市场策略", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if ("1".equals(type)){
                    Toast.makeText(this, "及时问卷", Toast.LENGTH_SHORT).show();
                    intent=new Intent(this,ToolSettingActivity.class);
                    intent.putExtra("type","2");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "销售精英", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                Toast.makeText(this, "今日头条", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "日精进", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "员工风采", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
