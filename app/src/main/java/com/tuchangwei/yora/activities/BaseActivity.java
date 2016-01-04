package com.tuchangwei.yora.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;

import com.tuchangwei.yora.infrastructure.YoraApplication;

public abstract class BaseActivity extends ActionBarActivity {
    protected YoraApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getApplication();

    }
}
