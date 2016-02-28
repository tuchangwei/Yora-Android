package com.tuchangwei.yora.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Subscribe;
import com.tuchangwei.yora.R;
import com.tuchangwei.yora.services.Account;

/**
 * Created by vale on 1/5/16.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
    private Callbacks callbacks;
    private View progressBar;
    private EditText usernameText;
    private EditText passwordText;
    private String defaultLoginButtonString;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);
        defaultLoginButtonString = loginButton.getText().toString();
        progressBar = view.findViewById(R.id.fragment_login_progressBar);
        usernameText = (EditText)view.findViewById(R.id.fragment_login_userName);
        passwordText = (EditText)view.findViewById(R.id.fragment_login_password);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            progressBar.setVisibility(View.VISIBLE);
            loginButton.setText("");
            loginButton.setEnabled(false);
            usernameText.setEnabled(false);
            passwordText.setEnabled(false);
            bus.post(new Account.LoginWithUserNameRequest(
                    usernameText.getText().toString(),
                    passwordText.getText().toString()));
        }

    }

    @Subscribe
    public void onLoginWithUserName(Account.LoginWithUserNameResponse response) {


        response.showErrorToast(getActivity());
        if (response.didSucceed()) {
            callbacks.onLoggedIn();
            return;
        }

        usernameText.setError(response.getPropertyError("userName"));
        passwordText.setError(response.getPropertyError("password"));

        progressBar.setVisibility(View.GONE);
        loginButton.setText(defaultLoginButtonString);
        loginButton.setEnabled(true);
        usernameText.setEnabled(true);
        passwordText.setEnabled(true);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    public interface Callbacks {
       void onLoggedIn();
    }
}
