package com.wh.jxd.com.stickynavhome.widget.stickynavLayout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/20.
 */

public class StickyNavLayout extends LinearLayout {

    private View mTop;
    private View mNav;
    private ViewPager mViewPager;

    private int mTopViewHeight;
    private RecyclerView mInnerScrollView;
    private boolean isTopHidden = false;

    private OverScroller mScroller;//，OverScroller是个辅助类，用于自定移动时帮我们处理掉数学的计算部分
    private VelocityTracker mVelocityTracker;//mVelocityTracker相关几个变量，计算什么时候需要自动移动
    private int mTouchSlop;//mTouchSlop区别用户是点击还是拖拽
    private int mMaximumVelocity, mMinimumVelocity;

    private float mLastY;
    private boolean mDragging;
    private onScrollListenre monScrollListener;
    private int firstVisibleItemPosition;

    public void setMonScrollListener(onScrollListenre monScrollListener) {
        this.monScrollListener = monScrollListener;
    }

    public StickyNavLayout(Context context) {
        this(context, null);
    }

    public StickyNavLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(context);
        mVelocityTracker = VelocityTracker.obtain();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context)
                .getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context)
                .getScaledMinimumFlingVelocity();
    }
    /**
     * 布局加载完毕
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = findViewById(R.id.id_stickynavlayout_topview);
        mNav = findViewById(R.id.id_stickynavlayout_indicator);
        View view = findViewById(R.id.id_stickynavlayout_viewpager);
        if (!(view instanceof ViewPager)) {
            throw new RuntimeException(
                    "id_stickynavlayout_viewpager show used by ViewPager !");
        }
        mViewPager = (ViewPager) view;
        getCurrentScrollView();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //200是模拟的toolBar加上状态栏的高度,保证了指示器的位置在ToolBar的下面
        mTopViewHeight = mTop.getMeasuredHeight() - 235;
    }

    /**
     * 处理控件的移动
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int action = event.getAction();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mVelocityTracker.clear();
                mVelocityTracker.addMovement(event);
                mLastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;
                if (!mDragging && Math.abs(dy) > mTouchSlop) {
                    mDragging = true;
                }
                if (mDragging) {
                    scrollBy(0, (int) -dy);
                    mLastY = y;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_UP:
                mDragging = false;
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int velocityY = (int) mVelocityTracker.getYVelocity();
                if (Math.abs(velocityY) > mMinimumVelocity) {
                    fling(-velocityY);
                }
                mVelocityTracker.clear();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
    }
    /**
     * 滚动控制了只能滚动到TopView隐藏就不能滚了
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
        //这里是为了实现滑到到首页顶部的图片消失就Toolbar一直渐变
        //600是图片的高度,将当前位置距离顶部的距离/图片的高度,将需要渐变的百分比回调出去
        if (monScrollListener != null) {
            if (y > 600) {
                y = 600;
            }
            monScrollListener.onScrolling(y * 1000 / 600);
        }
        /**
         * 判断滑动后距离顶部的距离是否达到了TopView的高度,来判断是否Top完全影藏了
         */
        isTopHidden = getScrollY() == mTopViewHeight;
//        ToastUtils.show("是否影藏头部：" + isTopHidden + "getScaleY:" + getScrollY() + "mTopViewHeight:" + mTopViewHeight);
//
//        LogUtils.d("是否影藏头部：" + isTopHidden + "getScaleY:" + getScrollY() + "mTopViewHeight:" + mTopViewHeight + "第一个可见的条目" + firstVisibleItemPosition);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 处理滑动冲突
     * 在onInterceptTouchEvent处理StickyLayout的拦截事件,2种情况
     * 1、当TopView还没有完全消失的时候StickyLayout消化滑动事件
     * 2、当TopVIew完全消失,到那时内容视图滑动到了最顶部,且Recycle中的第一个条目可见，继续滑动就要拉出TopView的时候
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                getCurrentScrollView();
                //获得Recycle的第一个可见的条目的位置
                LinearLayoutManager layoutManager = (LinearLayoutManager) mInnerScrollView.getLayoutManager();
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                float dy = y - mLastY;
                if (Math.abs(dy) > mTouchSlop) {
                    mDragging = true;
                    if (!isTopHidden ||
                            (firstVisibleItemPosition == 0 && mInnerScrollView.getScrollY() == 0 && isTopHidden && dy > 0)) {
                        return true;
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    /**
     * 获取底部页面中的RecycleView,需要通过这个距离来判断是否滑到顶部
     */
    private void getCurrentScrollView() {
        int index = mViewPager.getCurrentItem();
        PagerAdapter adapter = mViewPager.getAdapter();
        if (adapter instanceof FragmentPagerAdapter) {
            Fragment fragment = ((FragmentPagerAdapter) adapter).getItem(index);
            mInnerScrollView = (RecyclerView) fragment.getView().findViewById(R.id.id_stickynavlayout_innerscrollview);
        }
    }

    /**
     * 定义接口,将滑动的进队回调出去
     */
    public interface onScrollListenre {
        void onScrolling(float precent);
    }
}