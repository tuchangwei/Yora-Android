package com.tuchangwei.yora.activities;

import android.animation.Animator;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.infrastructure.YoraApplication;
import com.tuchangwei.yora.views.NavDrawer;

public abstract class BaseActivity extends ActionBarActivity {
    protected YoraApplication application;
    protected Toolbar toolbar;
    protected NavDrawer navDrawer;
    protected boolean isTablet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getApplication();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        isTablet = displayMetrics.widthPixels/displayMetrics.density >= 600;

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.include_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
    }

    public void fadeOut(final FadeOutListener listener) {
        View rootView = findViewById(android.R.id.content);
        rootView.animate().alpha(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                listener.onFadeOutEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).setDuration(350).start();
    }
    protected void setNavDrawer(NavDrawer drawer) {
        this.navDrawer = drawer;
        this.navDrawer.create();

        overridePendingTransition(0,0);
        View rootView = findViewById(android.R.id.content);
        rootView.setAlpha(0);
        rootView.animate().alpha(1).setDuration(150).start();
    }
    public Toolbar getToolbar(){

        return toolbar;
    }

    public YoraApplication getYoraApplication() {
        return application;
    }


    public interface FadeOutListener {
        void onFadeOutEnd();
    }
}
