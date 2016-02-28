package com.tuchangwei.yora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.infrastructure.Auth;
import com.tuchangwei.yora.services.Account;

/**
 * Created by vale on 2/21/16.
 */
public class AuthenticationActivity extends BaseActivity{

    private Auth auth;
    public static final String EXTRA_RETURN_TO_ACTIVITY = "EXTRA_RETURN_TO_ACTIVITY";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        auth = application.getAuth();
        if (!auth.hasAuthToken()) {
            startActivity(new Intent(this, LoginActivity.class));
            return;
        }
        bus.post(new Account.LoginWithLocalTokenRequest(auth.getAuthToken()));
    }
    @Subscribe
    public void onLoginWithLocalToken(Account.LoginWithLocalTokenResponse response) {
        if (!response.didSucceed()) {
            Toast.makeText(this,"Please login again",Toast.LENGTH_LONG).show();
            auth.setAuthToken(null);
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        Intent intent;
        String returnTo = getIntent().getStringExtra(EXTRA_RETURN_TO_ACTIVITY);
        if (returnTo != null) {
            try {
                intent = new Intent(this, Class.forName(returnTo));
            } catch (ClassNotFoundException e) {
                intent = new Intent(this,MainActivity.class);
            }
        } else {

            intent = new Intent(this,MainActivity.class);
        }
        startActivity(intent);
        finish();

    }
}
