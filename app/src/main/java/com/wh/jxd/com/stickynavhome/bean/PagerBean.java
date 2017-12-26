package com.wh.jxd.com.stickynavhome.bean;

/**
 * Created by kevin321vip on 2017/12/26.
 */

public class PagerBean {

    private String pagerTitle;
    private String pagerTime;

    public PagerBean(String pagerTitle, String pagerTime) {
        this.pagerTitle = pagerTitle;
        this.pagerTime = pagerTime;
    }

    public String getPagerTitle() {
        return pagerTitle;
    }

    public void setPagerTitle(String pagerTitle) {
        this.pagerTitle = pagerTitle;
    }

    public String getPagerTime() {
        return pagerTime;
    }

    public void setPagerTime(String pagerTime) {
        this.pagerTime = pagerTime;
    }

    @Override
    public String toString() {
        return "PagerBean{" +
                "pagerTitle='" + pagerTitle + '\'' +
                ", pagerTime='" + pagerTime + '\'' +
                '}';
    }
}
