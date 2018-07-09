package com.jappy.jappy_core.presentation;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/** * Created by jhonnybarrios on 11/29/17. * colaborate  irenecedeno on 06-02-18. */

public abstract class BaseCustomView extends FrameLayout {
    public BaseCustomView(Context context) {
        super(context);init(context);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init(context);
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);init(context);
    }

    protected abstract void init(Context context);
}
