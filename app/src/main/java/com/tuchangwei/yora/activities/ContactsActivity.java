package com.tuchangwei.yora.activities;

import android.os.Bundle;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.views.MainNavDrawer;

/**
 * Created by vale on 1/27/16.
 */
public class ContactsActivity extends BaseAuthenticatedActivity {
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contacts);
        setNavDrawer(new MainNavDrawer(this));
    }
}
