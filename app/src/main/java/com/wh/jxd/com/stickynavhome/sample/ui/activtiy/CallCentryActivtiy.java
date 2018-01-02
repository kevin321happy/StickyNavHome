package com.wh.jxd.com.stickynavhome.sample.ui.activtiy;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.sample.ui.adapter.CommonProblemAdapter;
import com.wh.jxd.com.stickynavhome.utils.StatusBarUtil;
import com.wh.jxd.com.stickynavhome.widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/28.
 * 呼叫中心的页面
 */

public class CallCentryActivtiy extends BaseActivtiy implements AdapterView.OnItemClickListener {

    private NoScrollListView mNLv;
    private CommonProblemAdapter mProblemAdapter;
    private List<String> mProblems = new ArrayList<>();
    private ImageView mIv_back;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_call_centry;
    }
    @Override
    protected void initView() {
        initData();
        StatusBarUtil.setStatusBarColor(this, R.color.blue_shen);
        hideToolBar();
        mNLv = (NoScrollListView) findViewById(R.id.nlv_common_problem);
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mProblemAdapter = new CommonProblemAdapter();

        mProblemAdapter.setCommonProblem(mProblems);
        mNLv.setAdapter(mProblemAdapter);
        mNLv.setOnItemClickListener(this);

    }
    private void initData() {
        mProblems.add("如何进行微课录制");
        mProblems.add("如何下载裘马草堂");
        mProblems.add("如何开始发起直播课程");
        mProblems.add("如何进行课程学习");
    }

    /**
     * 常见问题列表的条目点击
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
