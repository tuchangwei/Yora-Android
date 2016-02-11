package com.tuchangwei.yora.services;

import android.util.Log;

import com.tuchangwei.yora.infrastructure.YoraApplication;

public class Module {
    public static void register(YoraApplication application) {

        new InMemoryAccountService(application);
    }
}