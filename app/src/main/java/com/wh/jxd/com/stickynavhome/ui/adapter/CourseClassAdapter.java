package com.wh.jxd.com.stickynavhome.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;
import com.wh.jxd.com.stickynavhome.bean.CommonMenuBean;
import com.wh.jxd.com.stickynavhome.sample.widget.PageGridView;
import com.wh.jxd.com.stickynavhome.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kevin321vip on 2017/12/20.
 * 课程分类的adapter
 */

public class CourseClassAdapter extends PageGridView.PagingAdapter implements PageGridView.OnItemClickListener {

    private List<CommonMenuBean> mMenuBeans = new ArrayList<>();

    public void setMenuBeans(List<CommonMenuBean> menuBeans) {
        mMenuBeans = menuBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = View.inflate(parent.getContext(), R.layout.course_class_item, null);
        return new ViewHodler(item);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonMenuBean menuBean = mMenuBeans.get(position);
        if (menuBean != null) {
            ViewHodler hodler = (ViewHodler) holder;
            hodler.iv.setImageResource(menuBean.getIv_image_res());
            hodler.tv.setText(menuBean.getTv_menu_title());
        }
    }
    @Override
    public int getItemCount() {
        return mMenuBeans.size();
    }

    @Override
    public void onItemClick(PageGridView pageGridView, int position) {

    }

    @Override
    public List getData() {
        return mMenuBeans;
    }

    @Override
    public Object getEmpty() {
        return null;
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
