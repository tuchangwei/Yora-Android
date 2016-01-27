package com.tuchangwei.yora.activities;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.infrastructure.YoraApplication;
import com.tuchangwei.yora.views.NavDrawer;

public abstract class BaseActivity extends ActionBarActivity {
    protected YoraApplication application;
    protected Toolbar toolbar;
    protected NavDrawer navDrawer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getApplication();


    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
    }

    protected void setNavDrawer(NavDrawer drawer) {
        this.navDrawer = drawer;
        this.navDrawer.create();
    }
    public Toolbar getToolbar(){

        return toolbar;
    }

    public YoraApplication getYoraApplication() {
        return application;
    }
}
