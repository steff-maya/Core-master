package com.jappy.jappy_core.presentation.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.core.R;
import com.core.databinding.ViewInfoBinding;
import com.jappy.jappy_core.presentation.BaseCustomView;

/**
 * Created by irenecedeno on 11-02-18.
 */

public abstract class InfoView extends BaseCustomView {
    private View.OnClickListener actionListener;
    private ViewInfoBinding binder;

    public InfoView(Context context) {
        super(context);
    }

    public InfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init(Context context){
        binder = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_info,this,false);
        addView(binder.getRoot());
        binder.tvTitleWarning.setText(getTitle());
        binder.tvBodyWarning.setText(Html.fromHtml(getSubTitle()));

        binder.ivWarning.setImageDrawable(getImage());
    }



    protected abstract String getTitle();

    protected abstract String getSubTitle();

    protected abstract Drawable getImage();







}
