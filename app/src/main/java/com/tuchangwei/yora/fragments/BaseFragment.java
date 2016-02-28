package com.tuchangwei.yora.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.tuchangwei.yora.infrastructure.ActionScheduler;
import com.tuchangwei.yora.infrastructure.YoraApplication;

public abstract class BaseFragment extends Fragment {

    protected YoraApplication application;
    protected Bus bus;


    protected ActionScheduler scheduler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication)getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);
        scheduler = new ActionScheduler(application);
    }

    @Override
    public void onPause() {
        super.onPause();
        scheduler.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        scheduler.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
    }
    public ActionScheduler getScheduler() {
        return scheduler;
    }
}
