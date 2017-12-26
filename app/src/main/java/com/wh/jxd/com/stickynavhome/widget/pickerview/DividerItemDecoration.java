package com.wh.jxd.com.stickynavhome.widget.pickerview;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecycleView的实现分割线
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public final static int GRID = 0;
    public final static int LIST = 1;

    private int direct = GRID;

    private int space;

    private int startPosition; //根据你想要的屏蔽不需要的边距的。

    public DividerItemDecoration(int space) {
        this.space = space;
    }

    public DividerItemDecoration(int space, int direct) {
        this.space = space;
        this.direct = direct;
    }

    public DividerItemDecoration(int space, int direct, int startPosition) {
        this.space = space;
        this.direct = direct;
        this.startPosition = startPosition;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (direct == GRID) {
            if ((parent.getChildAdapterPosition(view) > startPosition || parent.getChildAdapterPosition(view) >= startPosition + 1) ){
                outRect.left = 0;
                outRect.bottom = space*2 ;
                outRect.right = 0;
                if (parent.getChildAdapterPosition(view) == startPosition || parent.getChildAdapterPosition(view) == startPosition + 1) {
                    outRect.top = space*2;
                }
            }
        } else {
            if (parent.getChildAdapterPosition(view) >= startPosition){
                outRect.left = space*3;
                outRect.bottom = space * 3;
                outRect.right = space*3;
                if (parent.getChildAdapterPosition(view) == startPosition) {
                    outRect.top = space;
                }
            }
        }
    }
}