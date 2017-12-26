package com.wh.jxd.com.stickynavhome.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/22.
 */

public class PoponeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private int mChoosePosition = 0;
    String[] mTitles = new String[]{"推荐排序", "最新课程", "学习进度", "学习人数", "课程评分", "考试成绩"};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();

        View item=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pop_one,null);
//        View item = View.inflate(parent.getContext(), R.layout.item_pop_one, null);
        ViewHodler hodler = new ViewHodler(item);
        return hodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHodler Viewhodler = (ViewHodler) holder;
        Viewhodler.tv_title.setText(mTitles[position]);
        if (position == mChoosePosition) {
            Viewhodler.iv_gouxuan.setVisibility(View.VISIBLE);
            Viewhodler.tv_title.setTextColor(mContext.getResources().getColor(R.color.myintegral_selector));
        } else {
            Viewhodler.iv_gouxuan.setVisibility(View.INVISIBLE);
            Viewhodler.tv_title.setTextColor(mContext.getResources().getColor(R.color.text__defaulttext));
        }
        Viewhodler.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChoosePosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    private class ViewHodler extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private ImageView iv_gouxuan;
        private LinearLayout ll_item;

        public ViewHodler(View itemView) {
            super(itemView);
            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_itemone);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_gouxuan = (ImageView) itemView.findViewById(R.id.iv_gouxuan);
        }
    }

}
