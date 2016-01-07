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
    private final static int REQUEST_NARROW_LOGIN = 1;
    private static final int REQUEST_REGISTER = 2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.activity_login_login);
        registerButton = findViewById(R.id.activity_login_register);
        if (loginButton != null) {
            loginButton.setOnClickListener(this);
        }
        registerButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == loginButton) {
            startActivityForResult(new Intent(this,LoginNarrowActivity.class), REQUEST_NARROW_LOGIN);

        } else if (v == registerButton) {
            startActivityForResult(new Intent(this,RegisterActivity.class), REQUEST_REGISTER);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_NARROW_LOGIN ||
                requestCode == REQUEST_REGISTER)
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
