package com.jappy.jappy_core.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.core.R;


/**
 *
 * <p>
 * A fragment like an activity only will execute operations that affect the UI.
 * These operations are defined by a view model and are triggered by its presenter.
 */
public abstract class BaseFragment<BINDER extends ViewDataBinding> extends Fragment {
    private Toolbar mToolbar;
    protected Context CONTEXT;
    public StackNavigationController stackNavigationController;
    private int currentOrientation;
    private boolean orientationHasChange = false;
    protected BINDER binder;

    /**
     * Specify the layout of the fragment to be inflated in the [BaseFragment.onCreateView]
     */

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        orientationHasChange = false;
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        setRetainInstance(true);
        return binder.getRoot();
    }

    protected abstract @LayoutRes
    int getLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);
        }
        if (isAdded()) {
            initView();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null)
            CONTEXT = context;
    }


    protected boolean isBaseStackFragment() {
        return false;
    }

    protected BaseStackFragment getBaseStackFragment() {
        if (isBaseStackFragment())
            return (BaseStackFragment) this;
        while (true) {
            if (getParentFragment() == null) return null;
            BaseFragment parent = (BaseFragment) getParentFragment();
            if (parent.isBaseStackFragment())
                return (BaseStackFragment) parent;
        }
    }

    public boolean orientationHasChange() {
        return orientationHasChange;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(getMenuId(), menu);
        recreateMenu();
    }

    private Menu recreateMenu() {
        if (mToolbar == null) return null;
        mToolbar.getMenu().clear();
        mToolbar.inflateMenu(getMenuId());
        return mToolbar.getMenu();
    }

    public int getMenuId() {
        return R.menu.empty_menu;
    }

    public void startActivity(Class activityClass) {
        if (getActivity() != null) {
            Intent i = new Intent(getActivity(), activityClass);
            startActivity(i);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null)
            binder.unbind();
    }

    public void finishActivity() {
        if (getActivity() != null)
            getActivity().finish();
    }

    /**
     * Use this method to initialize view components. This method is called after [ ][BaseFragment.onViewCreated]
     */
    protected abstract void initView();

    /**
     * Override this method in case you need to inject dependencies
     */
    protected abstract void injectDependencies();

    /**
     * Provee al fragment la posibilidad de saber cuando se quiere hacer back,
     * ademas de decidir si puede o no ejecutar el back.
     *
     * @return true si la actividad puede hacer back
     */
    public boolean allowBackPressed() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void restartActivity() {
        ((BaseActivity) getActivity()).restartActivity();
    }

    public void popBackStack() {
        getActivity().onBackPressed();
    }

    public void clearBackStack() {
        try {
            BaseFragment base = getBaseStackFragment();
            base.stackNavigationController.clearBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = getResources().getConfiguration().orientation;
        orientationHasChange = orientation != currentOrientation;
        currentOrientation = orientation;
    }
}