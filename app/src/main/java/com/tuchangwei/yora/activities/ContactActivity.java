package com.tuchangwei.yora.activities;

import android.os.Bundle;

import com.tuchangwei.yora.R;

/**
 * Created by vale on 2/28/16.
 */
public class ContactActivity extends BaseAuthenticatedActivity {
    public static final String EXTRA_USER_DETAILS = "EXTRA_USER_DETAILS";
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_contact);
    }
}
