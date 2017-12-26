package com.wh.jxd.com.stickynavhome.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.ui.adapter.ChooseLiveTypeAdapter;
import com.wh.jxd.com.stickynavhome.utils.AnimationLoader;
import com.wh.jxd.com.stickynavhome.utils.DisplayUtil;

/**
 * Created by kevin321vip on 2017/12/25.
 * 创建直播的Dialog
 *
 */

public class CreatLiveDialog extends Dialog implements AdapterView.OnItemClickListener {
    private Context mContext;
    private ListView mLv_list;
    private AnimationSet mInanimation;
    private AnimationSet mOutanimation;
    private View mDialogView;
    private Button mBtn_confirm;
    private  onDialogClickListener mOnDialogClickListener;
    private int mChooseItemPosition=0;
    private ChooseLiveTypeAdapter mLiveTypeAdapter;

    public void setOnDialogClickListener(onDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
    }

    public CreatLiveDialog(@NonNull Context context) {
        this(context,0);
    }

    public CreatLiveDialog(@NonNull Context context, int themeResId) {
       super(context,themeResId);
       this.mContext=context;
       initAnimation();
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        mInanimation = AnimationLoader.getInAnimation(getContext());
        mOutanimation = AnimationLoader.getOutAnimation(getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();
    }


    /**
     * 初始化对话框
     */
    private void init() {
        View contentView=View.inflate(mContext, R.layout.start_live_dialog_layout,null);
        mLv_list = (ListView) contentView.findViewById(R.id.lv_list);
        mLv_list.setOnItemClickListener(this);
        mBtn_confirm = (Button) contentView.findViewById(R.id.bt_confirm);
        //点击确定弹窗消失
        mBtn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDismiss();
                if (mOnDialogClickListener!=null){
                    mOnDialogClickListener.onConfirmClick(mChooseItemPosition);
                }
            }
        });

        mLiveTypeAdapter = new ChooseLiveTypeAdapter();
        mLv_list.setAdapter(mLiveTypeAdapter);
        //设置布局
        setContentView(contentView);
        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        resizeDialog();
    }
    //调整对话框的位置
    private void resizeDialog() {
        Window window = getWindow();
        window.setGravity(Gravity.CENTER_VERTICAL);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (DisplayUtil.getScreenSize(getContext()).x * 0.8);
        attributes.height = (int) (DisplayUtil.getScreenSize(getContext()).y * 0.5);
        getWindow().setAttributes(attributes);
    }

    /**
     * 在Start方法中来执行进入动画
     */
    @Override
    protected void onStart() {
        super.onStart();
        startWithAnimation(true);
    }

    /**
     * 进入时执行动画
     * @param
     */
    private void startWithAnimation(boolean isShowAnimation) {
        if (isShowAnimation) {
            mDialogView.startAnimation(mInanimation);
        }
    }

    //出场动画
    private void outWithAnimation(boolean isShowAnimation) {
        if (isShowAnimation) {
            mDialogView.startAnimation(mOutanimation);
        } else {
            super.dismiss();
        }
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        //结束动画的监听
        mOutanimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
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
     * Dialog消失
     */
    private void callDismiss() {
        super.dismiss();
    }

    @Override
    public void dismiss() {
        outWithAnimation(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.mChooseItemPosition=position;
        if (mLiveTypeAdapter!=null){
            mLiveTypeAdapter.setCurrentChoosePositon(mChooseItemPosition);
        }
    }

    //定义接口将选中的结果回调出去

    public interface  onDialogClickListener{
        void onConfirmClick(int position);
    }



}
