package com.wh.jxd.com.stickynavhome.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.PagerBean;
import com.wh.jxd.com.stickynavhome.utils.AnimationLoader;
import com.wh.jxd.com.stickynavhome.utils.DisplayUtil;
import com.wh.jxd.com.stickynavhome.widget.pickerview.OnItemSelectedListener;
import com.wh.jxd.com.stickynavhome.widget.pickerview.WheelAdapter;
import com.wh.jxd.com.stickynavhome.widget.pickerview.WheelView;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by kevin321vip on 2017/12/25.
 * 选择试卷的Dialog
 */

public class ChoosePagerDialog extends Dialog implements OnItemSelectedListener {

    private Context mContext;
    private AnimationSet mInanimation;
    private AnimationSet mOutanimation;
    private View mDialogView;
    private WheelView mNumberPicker;
    private Button mBtn_confirm;
    private List<PagerBean> mPagerBeans;
    private WheelView mNumberPick1;
    private String mTitle;
    private TextView mTv_title;


    public void setTitle(String title) {
        mTitle = title;
        if (mTv_title!=null){
            mTv_title.setText(title);
        }
    }

    public List<PagerBean> getPagerBeans() {
        return mPagerBeans;
    }

    public void setPagerBeans(List<PagerBean> pagerBeans) {
        mPagerBeans = pagerBeans;
        if (mNumberPicker != null) {
            mNumberPicker.getAdapter().notify();
        }
        if (mNumberPick1 != null) {
            mNumberPick1.getAdapter().notify();
        }
    }

    public ChoosePagerDialog(@NonNull Context context) {
        this(context, 0);
    }

    public ChoosePagerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        initAnimation();
    }

    private void initAnimation() {
        mInanimation = AnimationLoader.getInAnimation(getContext());
        mOutanimation = AnimationLoader.getOutAnimation(getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    /**
     * 在Star中执行进入的动画
     */
    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(true);
    }

    private void startWithAnimation(boolean isShowAnimation) {
        if (isShowAnimation) {
            mDialogView.startAnimation(mInanimation);
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mOutanimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //TODO 对话框消失
                mDialogView.post(new Runnable() {
                    @Override
                    public void run() {
                        callDismiss();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 初始化视图
     */
    private void initView() {
        View contentView = View.inflate(mContext, R.layout.choose_pager_dialog_layout, null);
        mNumberPicker = (WheelView) contentView.findViewById(R.id.number_pick);
        mBtn_confirm = (Button) contentView.findViewById(R.id.btn_confirm);
        mTv_title = (TextView) contentView.findViewById(R.id.tv_title);

        mNumberPicker.setOnItemSelectedListener(this);


        mBtn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDismiss();
            }
        });
        /**
         * 设置适配器
         */
        mNumberPicker.setAdapter(new WheelAdapter<String>() {
            private int mIndex=0;
            @Override
            public int getItemsCount() {
                return mPagerBeans == null ? 0 : mPagerBeans.size();
            }

            @Override
            public String getItem(int index) {
                this.mIndex=index;
                return mPagerBeans == null ? null : mPagerBeans.get(index).getPagerTitle();
            }

            @Override
            public int indexOf(String o) {
                return mIndex;
            }
        });
        //设置布局
        setContentView(contentView);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);

        resizeDialog();
    }
    private void resizeDialog() {
        Window window = getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (DisplayUtil.getScreenSize(getContext()).x * 0.8);
        attributes.height = (int) (DisplayUtil.getScreenSize(getContext()).y * 0.6);
        getWindow().setAttributes(attributes);
    }

    /**
     * 弹窗消失
     */
    private void callDismiss() {
        super.dismiss();

    }

    @Override
    public void dismiss() {
        outWithAnimation(true);
    }

    /**
     * 退出时伴随动画
     *
     * @param isShowAnimation
     */
    private void outWithAnimation(boolean isShowAnimation) {
        if (isShowAnimation) {
            mDialogView.startAnimation(mOutanimation);
        } else {
            super.dismiss();
        }
    }

    /**
     * 监听第一个滚轮的事件,第二个滚轮同步滚动到那个位置
     * @param index
     */
    @Override
    public void onItemSelected(int index) {
        PagerBean pagerBean = mPagerBeans.get(index);
        Log.d("text",pagerBean.toString());
    }
}
