package com.wh.jxd.com.stickynavhome.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/21.
 * 带有小红点提示的TextView
 */

@SuppressLint("AppCompatCustomView")
public class RedTipTextView extends TextView {
    public static final int RED_TIP_INVISIBLE = 0;
    public static final int RED_TIP_VISIBLE = 1;
    public static final int RED_TIP_GONE = 2;
    private int mTipvisible;
    private Paint mPaint;

    public RedTipTextView(Context context) {
        this(context, null);
    }

    public RedTipTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RedTipTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置红色圆点是否可见
     *
     * @param mTipvisible
     */
    public void setmTipvisible(int mTipvisible) {
        this.mTipvisible = mTipvisible;
        invalidate();
    }

    /**
     * 初始化
     *
     * @param attrs
     */

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(R.styleable.RedTipTextView);
            mTipvisible = array.getInt(R.styleable.RedTipTextView_redTipVisibility, 0);
            array.recycle();
        }
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        //设置防抖动
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * 绘制红色圆点
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mTipvisible == 1) {
            int width = getWidth();
            int paddingRight = getPaddingRight();
            canvas.drawCircle(width - getPaddingRight() / 2, paddingRight, paddingRight / 3, mPaint);

        }
    }
}
