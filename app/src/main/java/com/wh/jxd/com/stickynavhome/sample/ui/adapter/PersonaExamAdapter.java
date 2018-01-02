package com.wh.jxd.com.stickynavhome.sample.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/27.
 * 独立考试的Adapter
 */

public class PersonaExamAdapter extends RecyclerView.Adapter{
    private Context mContext;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       this.mContext=parent.getContext();
        View itemView=View.inflate(parent.getContext(), R.layout.item_personal_exam,null);
       return new ViewHodler(itemView);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHodler hodler = (ViewHodler) holder;
        if (position%2==0) {
            hodler.btn_exam.setTextColor(mContext.getResources().getColor(R.color.camera_progress_split));
            hodler.btn_exam.setBackground(mContext.getResources().getDrawable(R.drawable.bg_shape_can_exam));
        }else {
            hodler.btn_exam.setTextColor(mContext.getResources().getColor(R.color.text__defaulttext));
            hodler.btn_exam.setBackground(mContext.getResources().getDrawable(R.drawable.bg_shape_cannot_exam));
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHodler extends RecyclerView.ViewHolder{

        private TextView btn_exam;
        public ViewHodler(View itemView) {
            super(itemView);
            btn_exam= (TextView) itemView.findViewById(R.id.btn_exam);
        }
    }
}
