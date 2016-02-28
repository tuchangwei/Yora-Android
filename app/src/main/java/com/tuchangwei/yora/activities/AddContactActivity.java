package com.tuchangwei.yora.activities;

import android.os.Bundle;

import com.tuchangwei.yora.R;

/**
 * Created by vale on 2/28/16.
 */
public class AddContactActivity extends BaseAuthenticatedActivity{
    public static final String RESULT_CONTACT = "RESULT_CONTACT";
    @Override
    protected void onYoraCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_contact);
    }
}
