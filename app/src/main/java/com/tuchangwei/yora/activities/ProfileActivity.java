package com.tuchangwei.yora.activities;

import android.os.Bundle;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.views.MainNavDrawer;

/**
 * Created by vale on 1/27/16.
 */
public class ProfileActivity extends BaseAuthenticatedActivity {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_profile);
        setNavDrawer(new MainNavDrawer(this));
    }
}
