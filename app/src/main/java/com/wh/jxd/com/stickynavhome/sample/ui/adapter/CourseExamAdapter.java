package com.wh.jxd.com.stickynavhome.sample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/27.
 */

public class CourseExamAdapter extends RecyclerView.Adapter{
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(parent.getContext(), R.layout.item_course_exam,null);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private class ViewHodler extends RecyclerView.ViewHolder{
        public ViewHodler(View itemView) {
            super(itemView);
        }
    }
}
