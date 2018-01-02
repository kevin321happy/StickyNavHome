package com.wh.jxd.com.stickynavhome.sample.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/26.
 */

public class CampaignAdapter extends RecyclerView.Adapter {
    private Context mContext;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       this.mContext=parent.getContext();
        View item=View.inflate(parent.getContext(), R.layout.item_compaign,null);
        ViewHodler viewHodler = new ViewHodler(item);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHodler hodler = (ViewHodler) holder;
        if (position%3==0){
            hodler.type.setBackgroundColor(mContext.getResources().getColor(R.color.theme_blue));
        }else if (position%3==1){
            hodler.type.setBackgroundColor(mContext.getResources().getColor(R.color.theme_green));
        }else {
            hodler.type.setBackgroundColor(mContext.getResources().getColor(R.color.title_red));
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public  class ViewHodler extends RecyclerView.ViewHolder{
        private TextView tv_questions_count;
        private TextView tv_end_time;
        private TextView tv_apply;
        private TextView tv_title;
        private View type;



        public ViewHodler(View itemView) {
            super(itemView);
            tv_apply= (TextView) itemView.findViewById(R.id.tv_apply);
            tv_end_time= (TextView) itemView.findViewById(R.id.tv_end_time);
            tv_questions_count= (TextView) itemView.findViewById(R.id.tv_questions_count);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            type=itemView.findViewById(R.id.view_type);
        }
    }
}
