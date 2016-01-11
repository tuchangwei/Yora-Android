package com.tuchangwei.yora.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tuchangwei.yora.R;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText userName, email, password;
    private Button registerBtn;
    private View progressBar;
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


    }

    @Override
    public void onClick(View v) {

        application.getAuth().getUser().setLoggedIn(true);
        setResult(RESULT_OK);
        finish();
    }
}
