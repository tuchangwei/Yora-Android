package com.tuchangwei.yora.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.tuchangwei.yora.infrastructure.YoraApplication;

public abstract class BaseFragment extends Fragment {

    protected YoraApplication application;
    protected Bus bus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
}
