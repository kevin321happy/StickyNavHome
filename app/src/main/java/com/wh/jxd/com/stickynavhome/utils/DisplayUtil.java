package com.wh.jxd.com.stickynavhome.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.wh.jxd.com.stickynavhome.AppcationEx;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 作者 : andy 日期 : 15/11/8 20:42 邮箱 : andyxialm@gmail.com 描述 : 换算工具类
 */
public class DisplayUtil {


    /**
     * 根据分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获得屏幕尺寸
     */
    public static Point getScreenSize(Context context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getSize(point);
        return point;
    }

    /**
     * 获得屏幕尺寸
     */
    public static int getScreenWidthSize(Context context) {
        Point point = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();


    }

    public static int getScreenWidth() {
        final Resources resources = AppcationEx.getAppContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = AppcationEx.getAppContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    /*
    * 动态测量listview-Item的高度
    * @param listView
    */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    /**
     * 给出url，获取视频的第一帧
     */
    public static Bitmap getVideoThumbnail(String url) {

//    final Bitmap[] firstThum = new Bitmap[1];
//    Observable.just("Labmda表达式来操作啊").map(s -> s + "这是Map来改造啊").subscribe(s -> tv.setText(s));
//    Observable.just(url)
//        .subscribeOn(Schedulers.newThread())//在新线程中实现该方法
//        .map(new Func1<String, Bitmap>() {
//          @Override
//          public Bitmap call(String s) {
//            Bitmap bitmap = null;
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            try {
//              bitmap = retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_NEXT_SYNC);
//            } catch (IllegalArgumentException e) {
//              e.printStackTrace();
//            } finally {
//              retriever.release();
//            }
//            return bitmap;
//          }
//        })
//        .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
//        .subscribe(new Subscriber<Bitmap>() {
//          @Override
//          public void onCompleted() {
//
//          }
//
//          @Override
//          public void onError(Throwable e) {
//
//          }
//
//          @Override
//          public void onNext(Bitmap bitmap) {
//            firstThum[0]=bitmap ;
//          }
//        });
//    return firstThum[0];


        return null;
    }

    public class getThumTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            return null;
        }
    }


    /**
     * 获取本地视频的第一帧
     */
    public static Bitmap getLocalVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(filePath);
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }

    public static String getPicturePath(String path) {
        String mPathPic = null;
        //设置视频路径
//    String path = Environment.getExternalStorageDirectory() + "/demo.mp4";
//    Log.v("Tony", "Path:"+ path);
        //将路径实例化为一个文件对象
        File file = new File(path);
        //判断对象是否存在，不存在的话给出Toast提示
        if (file.exists()) {
            //提供统一的接口用于从一个输入媒体中取得帧和元数据
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(path);
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
//      Log.v("Tony", "Time:"+ time);
            int seconds = Integer.valueOf(time) / 1000;
            seconds = 10;
            for (int i = 1; i < seconds; i++) {
                Bitmap bitmap = retriever
                        .getFrameAtTime(i * 1000 * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                mPathPic = Environment.getExternalStorageDirectory() + File.separator + i + ".jpg";
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(mPathPic);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
//          Log.v("Tony", "Picture Got");
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return mPathPic;
        } else {
            return null;
//      Toast.makeText(.this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}