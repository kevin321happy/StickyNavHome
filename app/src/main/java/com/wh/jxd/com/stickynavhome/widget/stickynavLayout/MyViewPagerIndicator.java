package com.wh.jxd.com.stickynavhome.widget.stickynavLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

import java.util.List;

/**
 * Created by kevin321vip on 2017/12/21.
 * ViewPager指示器
 */

public class MyViewPagerIndicator extends LinearLayout {
    /**
     * 绘制指示器的画笔
     *
     * @param context
     */
    private Paint mPaint;

    /**
     * 指示器的高度
     *
     * @param context
     */
    private int mIndicatorHeight = 9;

    /**
     * 指示器的宽度
     *
     * @param context
     */
    private int mIndicatorWidth;

    /**
     * 指示器的偏移量
     *
     * @param context
     */
    private int mInitTranslationX;

    /**
     * 手滑动时候的偏移量
     *
     * @param context
     */
    private float mTranslationX;

    /**
     * 默认的tab的数量
     *
     * @param context
     */
    private static final int COUNT_DEFAULT_TAB = 4;

    /**
     * tab的数量
     *
     * @param context
     */
    private int mTabVisibleCount = COUNT_DEFAULT_TAB;

    /**
     * Tab的数据源
     *
     * @param context
     */
    private List<String> mTitles;

    /**
     * 与之绑定的ViewPager
     *
     * @param context
     */
    private ViewPager mViewPager;

    /**
     * 三角形的宽度为单个Tab的1/6
     */
    private static final float RADIO_TRIANGEL = 1.0f / 6;


    /**
     * 标题正常时的颜色
     */
    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
    /**
     * 标题选中时的颜色
     */
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;

    /**
     * 指示器滑块的最大宽度
     */
    private final int DIMENSION_TRIANGEL_WIDTH = (int) (getScreenWidth() / 3 * RADIO_TRIANGEL);
    private int tabWidth;

    public MyViewPagerIndicator(Context context) {
        this(context, null);
    }

    public MyViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyViewPagerIndicator);
        mTabVisibleCount = array.getInteger(R.styleable.MyViewPagerIndicator_item_count, COUNT_DEFAULT_TAB);
        if (mTabVisibleCount < 0) {
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        }
        array.recycle();
        init();


    }

    private void init() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        //设置一点点圆角
        mPaint.setPathEffect(new CornerPathEffect(3));


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LinearLayout.LayoutParams params = (LayoutParams) childView.getLayoutParams();
            params.weight = 1;
            params.width = getScreenWidth() / mTabVisibleCount;
            childView.setLayoutParams(params);
        }
        //设置每一个条目的点击事件

        setItemClickEvent();


    }

    /**
     * 设置点击事件
     */
    private void setItemClickEvent() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final int j = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO  将点击事件回调出去


                }
            });
        }
    }

    /**
     * 初始化指示器的宽度
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mIndicatorWidth = (int) (w / mTabVisibleCount * RADIO_TRIANGEL);
        mIndicatorWidth = Math.min(DIMENSION_TRIANGEL_WIDTH, mIndicatorWidth);
        // 初始时的偏移量
        mInitTranslationX = getWidth() / mTabVisibleCount / 2 - mIndicatorWidth
                / 2;
        tabWidth = getScreenWidth() / mTabVisibleCount;

        //  initIndicator();
    }

    /**
     * 绘制指示器
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(mTranslationX, getHeight() - 2);
        canvas.drawLine(0, 0, tabWidth, 0, mPaint);
        canvas.restore();
        super.dispatchDraw(canvas);
//      canvas.translate(mInitTranslationX+mIndicatorWidth,getHeight()+1);
//      canvas

    }

    // 设置关联的ViewPager
    public void setViewPager(ViewPager mViewPager, int pos) {
        this.mViewPager = mViewPager;

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 设置字体颜色高亮
                resetTextViewColor();
                highLightTextView(position);
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 滚动
                scroll(position, positionOffset);

                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(position,
                            positionOffset, positionOffsetPixels);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(state);
                }

            }
        });
        // 设置当前页
        mViewPager.setCurrentItem(pos);
        // 高亮
        highLightTextView(pos);
    }

    /**
     * 滚动指示器
     *
     * @param position
     * @param offset
     */
    private void scroll(int position, float offset) {
        /**
         * <pre>
         *  0-1:position=0 ;1-0:postion=0;
         * </pre>
         */
        // 不断改变偏移量，invalidate
        mTranslationX = getWidth() / mTabVisibleCount * (position + offset);

        tabWidth = getScreenWidth() / mTabVisibleCount;

        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (offset > 0 && position >= (mTabVisibleCount - 2)
                && getChildCount() > mTabVisibleCount) {
            if (mTabVisibleCount != 1) {
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth
                        + (int) (tabWidth * offset), 0);
            } else
            // 为count为1时 的特殊处理
            {
                this.scrollTo(
                        position * tabWidth + (int) (tabWidth * offset), 0);
            }
        }

        invalidate();

    }

    /**
     * 绘制字体颜色高亮
     *
     * @param position
     */
    private void highLightTextView(int position) {
        View view = getChildAt(position);
        if (view instanceof TextView)
        {
            ((TextView) view).setTextColor(COLOR_TEXT_HIGHLIGHTCOLOR);
        }



    }

    private void resetTextViewColor() {
        for (int i = 0; i < getChildCount(); i++)
        {
            View view = getChildAt(i);
            if (view instanceof TextView)
            {
                ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
            }
        }

    }

    /**
     * 初始化滑块指示器
     */
    private void initIndicator() {

    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 对外的ViewPager的回调接口
     *
     * @author zhy
     */
    public interface PageChangeListener {
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);
    }

    // 对外的ViewPager的回调接口
    private PageChangeListener onPageChangeListener;

    // 对外的ViewPager的回调接口的设置
    public void setOnPageChangeListener(PageChangeListener pageChangeListener) {
        this.onPageChangeListener = pageChangeListener;
    }

    /**
     * 设置tab的标题内容 可选，可以自己在布局文件中写死
     *
     * @param datas
     */
    public void setTabItemTitles(List<String> datas) {
        // 如果传入的list有值，则移除布局文件中设置的view
        if (datas != null && datas.size() > 0) {
            this.removeAllViews();
            this.mTitles = datas;

            for (String title : mTitles) {
                // 添加view
                addView(generateTextView(title));
            }
            // 设置item的click事件
            setItemClickEvent();
        }

    }

    /**
     * 返回TextView
     * @param title
     * @return
     */
    private View generateTextView(String title) {

        TextView tv = new TextView(getContext());
        LayoutParams lp = new LayoutParams(0,
                LayoutParams.MATCH_PARENT);
        lp.weight = 1;
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setText(title);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setLayoutParams(lp);

        return tv;

    }
}
