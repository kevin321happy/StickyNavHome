package com.wh.jxd.com.stickynavhome.sample.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.wh.jxd.com.stickynavhome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin321vip on 2017/12/28.
 * 常见问题的Adapter
 */


public class CommonProblemAdapter extends BaseAdapter {
    private List<String> mCommonProblem = new ArrayList<>();

    public void setCommonProblem(List<String> commonProblem) {
        mCommonProblem = commonProblem;
    }

    @Override
    public int getCount() {
        return mCommonProblem == null ? 0 : mCommonProblem.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommonProblem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.item_common_problem, null);
            viewHolder.tv_problem = (TextView) convertView.findViewById(R.id.tv_problem);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        if (mCommonProblem.get(position)!=null){
            viewHolder.tv_problem.setText(mCommonProblem.get(position));
        }
        return convertView;
    }

    private class ViewHolder {
        private ImageView iv_hongxing;
        private TextView tv_problem;
    }
}
