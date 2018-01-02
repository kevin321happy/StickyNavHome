package com.wh.jxd.com.stickynavhome.sample.widget;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2018/1/2.
 * 水平的指示器
 */

public class HPageIndicator extends LinearLayout implements PageGridView.PageIndicator {
    public HPageIndicator(Context context) {
        this(context, null);
    }

    public HPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void InitIndicatorItems(int itemsNumber) {
        removeAllViews();
        //根据页面的个数添加相应的指示器的个数
        for (int i = 0; i < itemsNumber; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.dot_unselected);
            imageView.setPadding(10, 0, 10, 0);
            addView(imageView);
        }
    }
    /**
     * 在Page的滑动变化的回调中,改变指示器的样式
     * @param pageIndex
     */
    @Override
    public void onPageSelected(int pageIndex) {
        ImageView imageView = (ImageView) getChildAt(pageIndex);
        if(imageView!=null) {
            imageView.setImageResource(R.drawable.dot_selected);
        }
    }
    @Override
    public void onPageUnSelected(int pageIndex) {
        ImageView imageView = (ImageView) getChildAt(pageIndex);
        if(imageView!=null) {
            imageView.setImageResource(R.drawable.dot_unselected);
        }
    }
}
