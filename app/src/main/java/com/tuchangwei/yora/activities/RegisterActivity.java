package com.tuchangwei.yora.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.services.Account;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText userName, email, password;
    private Button registerBtn;
    private View progressBar;
    private String defaultRegisterButtonString;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.activity_register_userName);
        email = (EditText) findViewById(R.id.activity_register_email);
        password = (EditText) findViewById(R.id.activity_register_password);
        registerBtn = (Button) findViewById(R.id.activity_register_register);
        progressBar = findViewById(R.id.activity_register_progressBar);

        registerBtn.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);

        defaultRegisterButtonString = registerBtn.getText().toString();
    }

    @Override
    public void onClick(View v) {

        userName.setEnabled(false);
        password.setEnabled(false);
        email.setEnabled(false);
        registerBtn.setEnabled(false);
        registerBtn.setText("");
        progressBar.setVisibility(View.VISIBLE);
        bus.post(new Account.RegisterRequest(
                userName.getText().toString(),
                email.getText().toString(),
                password.getText().toString()));
    }

    @Subscribe
    public void registerResponse(Account.RegisterResponse response) {
        onUserResponse(response);
    }
    @Subscribe
    public void externalRegisterResponse(Account.RegisterWithExternalTokenResponse response) {
        onUserResponse(response);
    }

    private void onUserResponse(Account.UserResponse response) {
        if (response.didSucceed()) {
            setResult(RESULT_OK);
            finish();
            return;
        }
        response.showErrorToast(this);
        userName.setError(response.getPropertyError("userName"));
        password.setError(response.getPropertyError("password"));
        email.setError(response.getPropertyError("email"));

        userName.setEnabled(true);
        password.setEnabled(true);
        email.setEnabled(true);
        registerBtn.setEnabled(true);
        registerBtn.setText(defaultRegisterButtonString);
        progressBar.setVisibility(View.GONE);
    }
}
