package com.tuchangwei.yora.infrastructure;

import android.app.Application;

import com.squareup.otto.Bus;
import com.tuchangwei.yora.services.Module;

public class YoraApplication extends Application {
    private Auth auth;
    private Bus bus;
    @Override
    public void onCreate() {
        super.onCreate();
        this.auth = new Auth(this);
        Module.register(this);

    }
    public YoraApplication() {
        bus = new Bus();
    }
    public Auth getAuth() {

        return auth;
    }
    public Bus getBus() {
        return bus;
    }
}
