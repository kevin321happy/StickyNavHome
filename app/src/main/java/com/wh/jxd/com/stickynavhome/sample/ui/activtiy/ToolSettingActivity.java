package com.wh.jxd.com.stickynavhome.sample.ui.activtiy;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.PagerBean;
import com.wh.jxd.com.stickynavhome.widget.dialog.ChoosePagerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/27.
 * 工具的设置的界面(签到设置+问卷设置)
 */

public class ToolSettingActivity extends BaseActivtiy implements View.OnClickListener {
    private TextView mTv_one;
    private TextView mTv_two;
    private TextView mTv_three;
    private ImageView mIv_one;
    private ImageView mIv_two;
    private ImageView mIv_three;
    private LinearLayout mLl_one;
    private LinearLayout mLl_two;
    private LinearLayout mLl_three;
    private String mType;//type值  1为设置签到  2为设置问卷
    private List<TextView> mTitles = new ArrayList<>();
    private List<PagerBean> mPagerBeans = new ArrayList<>();

    private List<PagerBean> mSignHistorys = new ArrayList<>();
    private ChoosePagerDialog mPagerDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign_setting;
    }

    @Override
    protected void initView() {
        initData();
        mType = getIntent().getStringExtra("type");
        setFinalTitle(mType);

        mLl_one = (LinearLayout) findViewById(R.id.ll_one);
        mLl_two = (LinearLayout) findViewById(R.id.ll_two);
        mLl_three = (LinearLayout) findViewById(R.id.ll_three);

        mTv_one = (TextView) findViewById(R.id.tv_one);
        mTv_two = (TextView) findViewById(R.id.tv_two);
        mTv_three = (TextView) findViewById(R.id.tv_three);

        mTitles.add(mTv_one);
        mTitles.add(mTv_two);
        mTitles.add(mTv_three);

        mIv_one = (ImageView) findViewById(R.id.iv_one);
        mIv_two = (ImageView) findViewById(R.id.iv_two);
        mIv_three = (ImageView) findViewById(R.id.iv_three);


        mLl_one.setOnClickListener(this);
        mLl_two.setOnClickListener(this);
        mLl_three.setOnClickListener(this);


        setView(mType);

    }

    /**
     * 初始化一些测试数据
     */
    private void initData() {
        mPagerBeans.add(new PagerBean("销售培训班" + "2017-12-8", "2017-12-8"));
        mPagerBeans.add(new PagerBean("企业满意度调查模板" + "2017-12-6", "2017-12-6"));
        mPagerBeans.add(new PagerBean("员工幸福调查模板" + "2017-12-7", "2017-12-7"));
        mPagerBeans.add(new PagerBean("销售培训班" + "2017-12-7", "2017-12-7"));
        mPagerBeans.add(new PagerBean("员工幸福度调查" + "2017-12-9", "2017-12-9"));
        mPagerBeans.add(new PagerBean("企业经营理念" + "2017-12-10", "2017-12-10"));

        mSignHistorys.add(new PagerBean("湖南省湘潭市岳塘区下摄司街道半边街社区", "2017-12-8"));
        mSignHistorys.add(new PagerBean("湖南省湘潭市岳塘区宝塔街道云峰村西北方向" + "2017-12-6", "2017-12-6"));
        mSignHistorys.add(new PagerBean("湘潭市岳塘区板塘街道农联村西", "2017-12-7"));
        mSignHistorys.add(new PagerBean("湘潭市岳塘区板塘街道农联村西", "2017-12-7"));
        mSignHistorys.add(new PagerBean("湘潭市雨湖区楠竹山镇罗金塘村西南方向", "2017-12-9"));
        mSignHistorys.add(new PagerBean("湖南省湘潭市岳塘区霞城乡岳塘村西北方向", "2017-12-10"));
    }
    /**
     * 设置控件
     * @param type
     */
    private void setView(String type) {
        if ("1".equals(type)) {
            mLl_three.setVisibility(View.INVISIBLE);
            mTv_one.setText("新建签到");
            mTv_two.setText("历史签到");
        } else if ("2".equals(type)) {
            mLl_three.setVisibility(View.VISIBLE);
            mTv_one.setText("新建空白考卷");
            mTv_two.setText("选择历史考卷");
            mTv_three.setText("选择考试模板");
        }
    }
    /**
     * 设置最终的标题
     * @param type
     */
    private void setFinalTitle(String type) {
        if ("1".equals(type)) {
            setToolBarTitle("设置签到");
        } else if ("2".equals(type)) {
            setToolBarTitle("设置问卷");
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_one:
                showHightLight(0);
                break;
            case R.id.ll_two:
                showHightLight(1);
                showQuestionTemplate();
                break;
            case R.id.ll_three:
                showHightLight(2);
                break;
            default:
                break;
        }
    }
    /**
     * 显示问卷模板的弹窗
     */
    private void showQuestionTemplate() {
        mPagerDialog = new ChoosePagerDialog(this);
        if ("1".equals(mType)) {
            mPagerDialog.setPagerBeans(mSignHistorys);
            mPagerDialog.show();
            mPagerDialog.setTitle("选择历史签到");
        } else {
            mPagerDialog.setPagerBeans(mPagerBeans);
            mPagerDialog.show();
            mPagerDialog.setTitle("选择历史试卷");
        }
    }

    /**
     * 显示字体的高亮
     *
     * @param position
     */
    private void showHightLight(int position) {
        if (mTitles.size() > 0) {
            for (int i = 0; i < mTitles.size(); i++) {
                if (i == position) {
                    mTitles.get(i).setTextColor(getResources().getColor(R.color.theme_blue));
                } else {
                    mTitles.get(i).setTextColor(getResources().getColor(R.color.text__defaulttext));
                }
            }
        }
    }
}
