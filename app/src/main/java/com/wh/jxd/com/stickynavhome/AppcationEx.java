package com.wh.jxd.com.stickynavhome;

import android.app.Application;
import android.content.Context;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by kevin321vip on 2017/12/11.
 */

public class AppcationEx extends Application {
    private static AppcationEx mAppcationEx;
    @Override
    public void onCreate() {
        mAppcationEx=this;
        //初始化百分比布局
        AutoLayoutConifg.getInstance().useDeviceSize();
        //初始化二维码扫描
        ZXingLibrary.initDisplayOpinion(this);


        super.onCreate();
    }
    public static Context getAppContext(){
        return mAppcationEx;
    }
}
