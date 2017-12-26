package com.wh.jxd.com.stickynavhome.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by kevin321vip on 2017/12/25.
 * 选择试卷的Dialog
 */

public class ChoosePagerDialog extends Dialog{

    public ChoosePagerDialog(@NonNull Context context) {
        this(context,0);
    }

    public ChoosePagerDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }
}
