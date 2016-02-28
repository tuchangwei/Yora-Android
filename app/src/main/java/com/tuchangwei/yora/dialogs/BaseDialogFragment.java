package com.tuchangwei.yora.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;

import com.squareup.otto.Bus;
import com.tuchangwei.yora.infrastructure.ActionScheduler;
import com.tuchangwei.yora.infrastructure.YoraApplication;

/**
 * Created by vale on 1/31/16.
 */
public abstract class BaseDialogFragment extends DialogFragment{
    protected YoraApplication application;
    protected Bus bus;
    protected ActionScheduler scheduler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (YoraApplication) getActivity().getApplication();
        bus = application.getBus();
        bus.register(this);

        scheduler = new ActionScheduler(application);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
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
}
