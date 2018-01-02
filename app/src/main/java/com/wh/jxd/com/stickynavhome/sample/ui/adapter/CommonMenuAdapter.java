package com.wh.jxd.com.stickynavhome.sample.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.CommonMenuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/27.
 */

public class CommonMenuAdapter extends RecyclerView.Adapter {

    private List<CommonMenuBean> mCommonMenuBeans = new ArrayList<>();
    private String mType;
    private onRecycleListener mOnRecycleListener;

    public void setOnRecycleListener(onRecycleListener onRecycleListener) {
        mOnRecycleListener = onRecycleListener;
    }

    public void setCommonMenuBeans(List<CommonMenuBean> commonMenuBeans, String type) {
        this.mType=type;
        mCommonMenuBeans = commonMenuBeans;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(parent.getContext(), R.layout.item_common_menu, null);
        return new ViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CommonMenuBean menuBean = mCommonMenuBeans.get(position);
        if (menuBean == null) {
            return;
        }
        ViewHodler hodler = (ViewHodler) holder;
        hodler.iv_image.setImageResource(menuBean.getIv_image_res());
        hodler.tv_menu_title.setText(menuBean.getTv_menu_title());
        //将条目点击事件回调出去
        hodler.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecycleListener!=null){
                    mOnRecycleListener.onItemClickListener(position,mType);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mCommonMenuBeans == null ? 0 : mCommonMenuBeans.size();
    }
    public class ViewHodler extends RecyclerView.ViewHolder {
        private ImageView iv_image;
        private TextView tv_menu_title;
        private LinearLayout ll_item;

        public ViewHodler(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_menu_title = (TextView) itemView.findViewById(R.id.tv_name);
            ll_item= (LinearLayout) itemView.findViewById(R.id.ll_item);
        }
    }

    public interface  onRecycleListener{
        void onItemClickListener(int position,String type);
    }
}
