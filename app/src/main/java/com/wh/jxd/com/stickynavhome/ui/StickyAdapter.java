package com.wh.jxd.com.stickynavhome.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/19.
 */

public class StickyAdapter extends RecyclerView.Adapter{
    List<String> mDatas = new ArrayList<String>();

    public void setmDatas(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item=View.inflate(parent.getContext(), R.layout.item,null);
        ViewHodler hodler = new ViewHodler(item);
        return hodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHodler(View itemView) {
            super(itemView);
//            textView= (TextView) itemView.findViewById(R.id.id_info);
        }
    }
}
