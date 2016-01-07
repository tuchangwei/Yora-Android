package com.tuchangwei.yora.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.tuchangwei.yora.R;

/**
 * Created by vale on 1/7/16.
 */
public class ExternalLoginActivity extends BaseActivity implements View.OnClickListener {

    public final static String EXTRA_EXTERANAL_SERVER = "EXTRA_EXTERANAL_SERVER";
    private WebView webView;
    private Button testBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_login);
        webView = (WebView) findViewById(R.id.activity_external_login_webView);
        testBtn = (Button) findViewById(R.id.activity_external_login_testBtn);
        testBtn.setOnClickListener(this);
        testBtn.setText("Log in with:" + getIntent().getStringExtra(EXTRA_EXTERANAL_SERVER));
    }

    @Override
    public void onClick(View v) {
        if (v == testBtn) {
            application.getAuth().getUser().setLoggedIn(true);
            setResult(RESULT_OK);
            finish();
        }
    }
}
