package com.tuchangwei.yora.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tuchangwei.yora.R;

/**
 * Created by vale on 1/5/16.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private Button loginButton;
    private Callbacks callbacks;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (Button) view.findViewById(R.id.fragment_login_loginButton);
        loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {

            application.getAuth().getUser().setLoggedIn(true);
            callbacks.onLoggedIn();
        }

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
