package com.wh.jxd.com.stickynavhome.sample.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.BaseFragment;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.sample.ui.adapter.CourseExamAdapter;
import com.wh.jxd.com.stickynavhome.sample.ui.adapter.PersonaExamAdapter;

/**
 * Created by kevin321vip on 2017/12/26.
 */

public class ExamFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView mRcv_pager;
    private TableLayout mTableLayout;
    private TableRow mTableRow;
    private TextView mTv_end;
    private TextView mTv_all;
    private TextView mTv_unexam;
    private TextView mTv_unpass;
    private TextView mTv_reexam;
    private String mType;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //获取传递进来的参数,根据参数判断给列表加载不同的布局
        if (getArguments()!=null) {
            mType = getArguments().getString("type");
        }
        mRcv_pager = (RecyclerView) view.findViewById(R.id.rcv_pager);
        mTableLayout = (TableLayout) view.findViewById(R.id.tab_layout);
        mTableRow = (TableRow) view.findViewById(R.id.tab_row);

        mTv_end = (TextView) view.findViewById(R.id.tv_end);
        mTv_all = (TextView) view.findViewById(R.id.tv_all);
        mTv_unexam = (TextView) view.findViewById(R.id.tv_unexam);
        mTv_unpass = (TextView) view.findViewById(R.id.tv_unpass);
        mTv_reexam = (TextView) view.findViewById(R.id.tv_reexam);

        mTv_all.setOnClickListener(this);
        mTv_end.setOnClickListener(this);
        mTv_unexam.setOnClickListener(this);
        mTv_unpass.setOnClickListener(this);
        mTv_reexam.setOnClickListener(this);
        showHightLight(0);
        initRecycle();

    }

    /**
     * 初始化Recycle列表
     */
    private void initRecycle() {
        mRcv_pager.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mType.equals("独立考试")){
            PersonaExamAdapter personaExamAdapter = new PersonaExamAdapter();
            mRcv_pager.setAdapter(personaExamAdapter);
        }else if (mType.equals("课程考试")){
            CourseExamAdapter courseExamAdapter = new CourseExamAdapter();
            mRcv_pager.setAdapter(courseExamAdapter);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_exam;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_all:
                showHightLight(0);
                break;
            case R.id.tv_unexam:
                showHightLight(1);
                break;
            case R.id.tv_unpass:
                showHightLight(2);
                break;
            case R.id.tv_reexam:
                showHightLight(3);
                break;
            case R.id.tv_end:
                showHightLight(4);
                break;
        }
    }
    /**
     * 显示字体的高亮
     *
     * @param position
     */
    private void showHightLight(int position) {
        for (int i = 0; i < mTableRow.getChildCount(); i++) {
            TextView chidView = (TextView) mTableRow.getChildAt(i);
            //高亮显示
            if (position == i) {
                chidView.setTextColor(getResources().getColor(R.color.theme_blue));
            } else {
                chidView.setTextColor(getResources().getColor(R.color.color_666));
            }
        }
    }

    /**
     * 创建Fragment对象传递参数
     * @param text
     * @return
     */
    public static ExamFragment newInstance(String text) {
        ExamFragment fragment = new ExamFragment();
        Bundle args = new Bundle();
        args.putString("type", text);
        fragment.setArguments(args);
        return fragment;
    }
}
