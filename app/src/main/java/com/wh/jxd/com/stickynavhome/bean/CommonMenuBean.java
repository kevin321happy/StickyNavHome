package com.wh.jxd.com.stickynavhome.bean;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by kevin321vip on 2017/12/27.
 *
 */

public class CommonMenuBean {

    private int iv_image_res;
    private String tv_menu_title;

    @Override
    public String toString() {
        return "CommonMenuBean{" +
                "iv_image_res=" + iv_image_res +
                ", tv_menu_title='" + tv_menu_title + '\'' +
                '}';
    }

    public CommonMenuBean(int iv_image_res, String tv_menu_title) {
        this.iv_image_res = iv_image_res;
        this.tv_menu_title = tv_menu_title;
    }

    public int getIv_image_res() {
        return iv_image_res;
    }

    public void setIv_image_res(int iv_image_res) {
        this.iv_image_res = iv_image_res;
    }

    public String getTv_menu_title() {
        return tv_menu_title;
    }

    public void setTv_menu_title(String tv_menu_title) {
        this.tv_menu_title = tv_menu_title;
    }
}
