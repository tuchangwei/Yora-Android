package com.tuchangwei.yora.activities;

import android.os.Bundle;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.views.MainNavDrawer;

/**
 * Created by vale on 1/4/16.
 */
public class MainActivity extends BaseAuthenticatedActivity {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Yora");
        setNavDrawer(new MainNavDrawer(this));
    }
}
