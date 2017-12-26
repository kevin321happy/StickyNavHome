package com.wh.jxd.com.stickynavhome.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

/**
 * Created by kevin321vip on 2017/12/25.
 */

public class ChooseLiveTypeAdapter extends BaseAdapter {
    //选中的位置,默认为0
    private int mChoosedPosition=0;

    String [] mTypes= new String[]{"1v1课堂","小班课","大班课"};
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHodler hodler = null;
        if (convertView == null) {
            hodler = new ViewHodler();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_type, null);
            hodler.mTvContent = (TextView) convertView.findViewById(R.id.tv_content);
            hodler.mTvType=(TextView) convertView.findViewById(R.id.tv_type);
            hodler.mLlItem= (LinearLayout) convertView.findViewById(R.id.ll_item);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }

        if (position==mChoosedPosition){
            hodler.mLlItem.setBackgroundColor(parent.getContext().getResources().getColor(R.color.theme_blue));
        }else {
            hodler.mLlItem.setBackgroundColor(parent.getContext().getResources().getColor(R.color.white));
        }
        hodler.mTvContent.setText(mTypes[position]);
        if (position==0){
            hodler.mTvType.setText("(1个学员,双向语音)");
        }else if (position==1){
            hodler.mTvType.setText("(5个学员,多向语音)");
        }else if (position==2){
            hodler.mTvType.setText("(200个学员,单向语音)");
        }
//        hodler.mLlItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mChoosedPosition=position;
//                notifyDataSetChanged();
//            }
//        });
        return convertView;
    }

    public void setCurrentChoosePositon(int positon){
        this.mChoosedPosition=positon;
        notifyDataSetChanged();
    }

    private class ViewHodler {
        private TextView mTvContent;
        private TextView mTvType;
        private LinearLayout mLlItem;

    }
}
