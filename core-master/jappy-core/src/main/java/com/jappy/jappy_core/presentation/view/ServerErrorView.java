package com.jappy.jappy_core.presentation.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.core.R;

/**
 * Created by irenecedeno on 11-02-18.
 */

public class ServerErrorView extends InfoView{

    public ServerErrorView(Context context) {
        super(context);
    }

    public ServerErrorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ServerErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ServerErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected String getTitle() {
        return getContext().getString(R.string.titleServerError);
    }

    @Override
    protected String getSubTitle() {
        return getContext().getString(R.string.subtitleServerError);
    }



    @Override
    protected Drawable getImage() {
        return getContext().getResources().getDrawable(R.drawable.ic_warning_connection);
    }
}

