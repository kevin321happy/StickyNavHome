package com.wh.jxd.com.stickynavhome.widget.pop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;


/**
 * Created by kevin321vip on 2017/12/22.
 * 自定义的PopWindow
 * 构建者模式链式调用
 */

public class CustomPopWindow {
    private Context mContext;
    private int mHeight;
    private int mWidth;
    private boolean mIsFocusable = true;
    private boolean mIsOutside = true;

    private int mResLayoutId = -1;
    private View mContentView;
    private PopupWindow mPopupWindow;
    private int mAnimationStyle = -1;

    private boolean mClippEnable = true;//default is true
    private boolean mIgnoreCheekPress = false;
    private int mInputMode = -1;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private int mSoftInputMode = -1;
    private boolean mTouchable = true;//default is ture
    private View.OnTouchListener mOnTouchListener;

    public CustomPopWindow(Context context) {
        mContext = context;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public View getContentView() {
        return mContentView;
    }

    public void setContentView(View contentView) {
        mContentView = contentView;
    }

    /**
     * 显示PopWindow
     */
    public CustomPopWindow showAsDropDown(View anchor, int xoff, int yoff) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, xoff, yoff);
        }
        return this;
    }

    public CustomPopWindow showAsDropDown(View view) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(view);
        }
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public CustomPopWindow showAsDropDown(View anchor, int xOff, int yOff, int gravity) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchor, xOff, yOff, gravity);
        }
        return this;
    }

    /**
     * 相对于父控件的位置（通过设置Gravity.CENTER，下方Gravity.BOTTOM等 ），可以设置具体位置坐标
     *
     * @param parent
     * @param gravity
     * @param x       the popup's x location offset
     * @param y       the popup's y location offset
     * @return
     */
    public CustomPopWindow showAtLocation(View parent, int gravity, int x, int y) {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(parent, gravity, x, y);
        }
        return this;
    }

    /**
     * 消失POp
     */
    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss();
        }
    }
    /**
     * build方法返回一个PopWindow
     */
    private PopupWindow build() {
        if (mContentView == null) {
            mContentView = LayoutInflater.from(mContext).inflate(mResLayoutId, null);
        }
        if (mWidth != 0 && mHeight != 0) {
            mPopupWindow = new PopupWindow(mContentView, mWidth, mHeight);
        }  else {
            mPopupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (mAnimationStyle != -1) {
            mPopupWindow.setAnimationStyle(mAnimationStyle);
        }
        apply(mPopupWindow);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(mIsOutside);
        if (mWidth == 0 || mHeight == 0) {
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置窗体的高度
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            //如果外界没有设置宽高
            mWidth = mPopupWindow.getContentView().getMeasuredWidth();
            mHeight = mPopupWindow.getContentView().getMeasuredHeight();
        }
        mPopupWindow.update();
        return mPopupWindow;
    }
    /**
     * 设置Pop的一些属性
     *
     * @param popupWindow
     */
    private void apply(PopupWindow popupWindow) {
        popupWindow.setClippingEnabled(mClippEnable);
        if (mIgnoreCheekPress) {
            popupWindow.setIgnoreCheekPress();
        }
        if (mSoftInputMode != -1) {
            popupWindow.setSoftInputMode(mSoftInputMode);
        }
        if (mInputMode != -1) {
            popupWindow.setInputMethodMode(mInputMode);
        }
        if (mOnDismissListener != null) {
            popupWindow.setOnDismissListener(mOnDismissListener);
        }
        if (mOnTouchListener != null) {
            popupWindow.setTouchInterceptor(mOnTouchListener);
        }
        popupWindow.setTouchable(mTouchable);
        popupWindow.setFocusable(mIsFocusable);
    }

    public static class Builder {
        public CustomPopWindow mCustomPopWindow;
        private Context mContext;

        /**
         * 构造方法
         *
         * @param context
         */
        public Builder(Context context) {
            mContext = context;
            mCustomPopWindow = new CustomPopWindow(context);
        }

        /**
         * 设置弹窗的大小
         *
         * @param width
         * @param height
         * @return
         */
        public Builder size(int width, int height) {
            this.mCustomPopWindow.mWidth = width;
            this.mCustomPopWindow.mHeight = height;
            return this;
        }

        /**
         * 设置是否获取焦点
         *
         * @param focusable
         * @return
         */
        public Builder setFocusable(boolean focusable) {
            this.mCustomPopWindow.mIsFocusable = focusable;
            return this;
        }

        /**
         * 设置布局
         */
        public Builder setView(int reslayout) {
            this.mCustomPopWindow.mResLayoutId = reslayout;
            this.mCustomPopWindow.mContentView = null;
            return this;
        }

        public Builder setView(View view) {
            this.mCustomPopWindow.mContentView = view;
            this.mCustomPopWindow.mResLayoutId = -1;
            return this;
        }

        /**
         * 设置点击外部消失弹窗
         */
        public Builder setOutsideTouchable(boolean outsideTouchable) {
            this.mCustomPopWindow.mIsOutside = outsideTouchable;
            return this;
        }

        /**
         * 设置弹窗动画的样式
         */
        public Builder setAnimationStyle(int animationStyle) {
            this.mCustomPopWindow.mAnimationStyle = animationStyle;
            return this;
        }

        public Builder setIgnoreCheekPress(boolean ignoreCheekPress) {
            mCustomPopWindow.mIgnoreCheekPress = ignoreCheekPress;
            return this;
        }

        public Builder setInputMethodMode(int mode) {
            mCustomPopWindow.mInputMode = mode;
            return this;
        }


        /**
         * 设置弹窗消失的监听
         */
        public Builder setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
            this.mCustomPopWindow.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setSoftInputMode(int softInputMode) {
            mCustomPopWindow.mSoftInputMode = softInputMode;
            return this;
        }


        public Builder setTouchable(boolean touchable) {
            mCustomPopWindow.mTouchable = touchable;
            return this;
        }

        public Builder setTouchIntercepter(View.OnTouchListener touchIntercepter) {
            mCustomPopWindow.mOnTouchListener = touchIntercepter;
            return this;
        }

        /**
         * 构建完成返回CustomPopWindow对象
         *
         * @return
         */
        public CustomPopWindow creat() {
            //构建Coustom
            mCustomPopWindow.build();
            return mCustomPopWindow;
        }

    }
}
