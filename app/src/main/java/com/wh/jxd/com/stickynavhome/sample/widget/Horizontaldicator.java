package com.wh.jxd.com.stickynavhome.sample.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/29.
 * 水平的指示器
 */

public class Horizontaldicator extends LinearLayout {
    private Context mContext = null;
    private int dotSize = 10; // 指示器的大小（dp）
    private int margins = 10; // 指示器间距（dp）
    private List<View> mIndicatorViews = null; // 存放指示器
    private Paint mPaint;
    private int mCount = 2;
    private int mChoosePosition = 0;//选中的位置
    private LayoutParams mNormalParams;
    private LayoutParams mChooseParams;


    public Horizontaldicator(Context context) {
        this(context, null);
    }

    public Horizontaldicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public Horizontaldicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setDotSize(int dotSize) {
        this.dotSize = dotSize;
    }

    public void setMargins(int margins) {
        this.margins = margins;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setChoosePosition(int choosePosition) {
        mChoosePosition = choosePosition;
        initIndicator(mCount);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        dotSize = DisplayUtil.dp2px(context, dotSize);
        margins = DisplayUtil.dp2px(context, margins);
    }

    /**
     * 初始化指示器
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void initIndicator(int count) {
        if (mContext == null) {
            mContext = getContext();
        }
        if (mIndicatorViews == null) {
            mIndicatorViews = new ArrayList<>();
        } else {
            mIndicatorViews.clear();
            removeAllViews();
        }
        //添加指示器
        View view;
        //正常状态的布局参数
        mNormalParams = new LayoutParams(3 * dotSize, dotSize);
        //选中状态下的布局参数
        mChooseParams = new LayoutParams(5 * dotSize, dotSize);

        mNormalParams.setMargins(margins, margins, margins, margins);
        mChooseParams.setMargins(margins, margins, margins, margins);
        for (int i = 0; i < count; i++) {
            view = new View(mContext);
            if (i == mChoosePosition) {
                view.setBackgroundResource(R.drawable.bg_horizontal_choosed);
                addView(view, mChooseParams);
            } else {
                view.setBackgroundResource(R.drawable.bg_horizontal_normal);
                addView(view, mNormalParams);
            }
            mIndicatorViews.add(view);
        }
    }

    /**
     * 设置选中页的位置
     */
    @SuppressLint("NewApi")
    public void setSelectedPage(int selectedPage) {
        mChoosePosition = selectedPage;
        initIndicator(mCount);
    }

}
