package com.wh.jxd.com.stickynavhome.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.widget.CircleImageView;


/**
 * Created by kevin321vip on 2017/12/20.
 * 课程分类的adapter
 */

class CourseClassAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = View.inflate(parent.getContext(), R.layout.course_class_item, null);
        return new ViewHodler(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHodler hodler = (ViewHodler) holder;
        hodler.iv.setImageResource(R.drawable.ic_lyon);
        hodler.tv.setText("裘马");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private CircleImageView iv;
        private TextView tv;

        public ViewHodler(View itemView) {
            super(itemView);
            iv = (CircleImageView) itemView.findViewById(R.id.iv_image);
            tv = (TextView) itemView.findViewById(R.id.tv_text);
        }
    }
}
