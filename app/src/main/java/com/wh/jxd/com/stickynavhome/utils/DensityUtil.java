package com.wh.jxd.com.stickynavhome.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * 屏幕尺寸及尺寸转换
 *
 * @author jiaohongyun
 * @version [版本号, 2014年11月21日]
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从dp的单位转成为px(像素)
     *
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px(像素)的单位转成为dp
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @param
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getSysbarHeight(Context context) {

        Class<?> target = null;
        Object obj = null;
        Field field = null;
        int temp = 0, sbar = 25; //默认应该是25dp
        try {
            target = Class.forName("com.android.internal.R$dimen");
            obj = target.newInstance();
            field = target.getField("status_bar_height");
            temp = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(temp);
//            Lg.d("sbar height:", DensityUtil.px2dip(context, sbar));
        } catch (Exception e1) {
//            Lg.d("get sbar height fail");
            e1.printStackTrace();
        }
        return sbar;
    }
}