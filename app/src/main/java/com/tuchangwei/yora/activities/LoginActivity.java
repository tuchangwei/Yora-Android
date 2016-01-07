package com.tuchangwei.yora.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tuchangwei.yora.R;
import com.tuchangwei.yora.fragments.LoginFragment;

public class LoginActivity extends BaseActivity implements View.OnClickListener,LoginFragment.Callbacks {
    private View loginButton;
    private View registerButton;
    private View facebookLoginButton;
    private View googleLoginButton;
    private final static int REQUEST_NARROW_LOGIN = 1;
    private static final int REQUEST_REGISTER = 2;
    private static final int REQUEST_EXTERNAL_LOGIN = 3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.activity_login_login);
        registerButton = findViewById(R.id.activity_login_register);
        facebookLoginButton = findViewById(R.id.activity_login_facebook);
        googleLoginButton = findViewById(R.id.activity_login_google);
        if (loginButton != null) {
            loginButton.setOnClickListener(this);
        }
        registerButton.setOnClickListener(this);
        facebookLoginButton.setOnClickListener(this);
        googleLoginButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            startActivityForResult(new Intent(this,LoginNarrowActivity.class), REQUEST_NARROW_LOGIN);

        } else if (v == registerButton) {
            startActivityForResult(new Intent(this,RegisterActivity.class), REQUEST_REGISTER);
        } else if (v == facebookLoginButton) {
            doExternalLogin("Facebook");
        } else if (v == googleLoginButton) {
            doExternalLogin("Google");
        }
    }

    private void doExternalLogin(String externalServer) {
        Intent intent = new Intent(this, ExternalLoginActivity.class);
        intent.putExtra(ExternalLoginActivity.EXTRA_EXTERANAL_SERVER, externalServer);
        startActivityForResult(intent,REQUEST_EXTERNAL_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_NARROW_LOGIN ||
                requestCode == REQUEST_REGISTER ||
                requestCode == REQUEST_EXTERNAL_LOGIN)
            finishLogin();
    }

    private void finishLogin() {

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoggedIn() {
        finishLogin();
    }
}
