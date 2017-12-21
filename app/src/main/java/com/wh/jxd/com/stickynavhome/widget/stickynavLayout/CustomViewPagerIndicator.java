package com.wh.jxd.com.stickynavhome.widget.stickynavLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.widget.RedTipTextView;

/**
 * Created by kevin321vip on 2017/12/20.
 * 自定义的ViewpagerIndicator
 */

public class CustomViewPagerIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    /**
     * 标题正常时的颜色
     */
    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    /**
     * 标题选中时的颜色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;
    private static final int COLOR_INDICATOR_COLOR = R.color.myintegral_selector;
    private String[] mTitles;
    private int mTabCount;
    private int mIndicatorColor = COLOR_INDICATOR_COLOR;
    private float mTranslationX;
    private Paint mPaint = new Paint();
    private int mTabWidth;
    private onPagerChangeListener mOnPagerChangeListener;

    public void setmOnPagerChangeListener(onPagerChangeListener mOnPagerChangeListener) {
        this.mOnPagerChangeListener = mOnPagerChangeListener;
    }

    private ViewPager mViewPager;

    /**
     * 设置Viewpager的绑定
     *
     * @param mViewPager
     */
    public void setmViewPager(ViewPager mViewPager, int originPos) {
        this.mViewPager = mViewPager;
        mViewPager.setOnPageChangeListener(this);
        //设置当前页被选中
        mViewPager.setCurrentItem(originPos);
        //字体高亮显示
        highLightTextView(originPos);
    }


    public CustomViewPagerIndicator(Context context) {
        this(context, null);
    }

    @SuppressLint("ResourceAsColor")
    public CustomViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(mIndicatorColor);
        mPaint.setStrokeWidth(9.0F);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth = w / mTabCount;
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
        mTabCount = titles.length;
        generateTitleView();
    }

    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
    }

    /**
     * 绘制指示器
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - 2);
        canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
        canvas.restore();
    }

    public void scroll(int position, float offset) {
        /**
         * <pre>
         *  0-1:position=0 ;1-0:postion=0;
         * </pre>
         */
        mTranslationX = getWidth() / mTabCount * (position + offset);
        invalidate();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @SuppressLint("NewApi")
    private void generateTitleView() {
        if (getChildCount() > 0) {
            this.removeAllViews();
        }
        int count = mTitles.length;

        setWeightSum(count);
        for (int i = 0; i < count; i++) {
            //这里是模拟在Tab栏上显示小红点标示有新的内容,实际开发中可以根据传入的数据来判断是否显示
            RedTipTextView tv = new RedTipTextView(getContext());
            if (i==2||i==4){
                //设置红点提示显示
                tv.setmTipvisible(RedTipTextView.RED_TIP_VISIBLE);
            }
            LayoutParams lp = new LayoutParams(0,
                    LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setPadding(30,10,20,30);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(COLOR_TEXT_NORMAL);
            tv.setText(mTitles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setLayoutParams(lp);
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPagerChangeListener != null) {
                        mOnPagerChangeListener.onPageSelected(finalI);
                    }
                }
            });
            addView(tv);
        }
    }

    /**
     * 通过Indicator将ViewPager的事件回调出去
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        scroll(position, positionOffset);
        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        // 设置字体颜色高亮
        resetTextViewColor();
        highLightTextView(position);
        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener.onPageSelected(position);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {
        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener.onPageScrollStateChanged(state);
        }
    }
    /**
     * 设置字体的颜色高亮
     *
     * @param position
     */
    private void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(COLOR_INDICATOR_COLOR);
        }
    }
    /**
     * 重置TextView颜色
     */
    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }

    /**
     * 定义接口回调Pager的事件
     */
    public interface onPagerChangeListener {
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels);
        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }
}
