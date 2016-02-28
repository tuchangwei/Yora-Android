package com.tuchangwei.yora.infrastructure;

import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vale on 2/28/16.
 */
public class ActionScheduler {
    private final YoraApplication application;
    private final Handler handler;
    private final ArrayList<TimedCallback> timedCallbacks;
    private final HashMap<Class,Runnable> onResumeActions;
    private boolean isPause;
    public ActionScheduler(YoraApplication application) {
        this.application = application;
        handler = new Handler();
        timedCallbacks = new ArrayList<>();
        onResumeActions = new HashMap<>();
    }
    public void onPause(){
        isPause = true;
    }
    public void onResume() {
        isPause = false;

        for (TimedCallback callback : timedCallbacks) {
            callback.scheduler();
        }
        for (Runnable runnable : onResumeActions.values()) {
            runnable.run();
        }
        onResumeActions.clear();
    }
    public void invokeOnResume(Class cls, Runnable runnable) {
        if (!isPause) {
            runnable.run();
            return;
        }
        onResumeActions.put(cls, runnable);
    }
    public void postDelayed(Runnable runnable, long milliseconds){
        handler.postDelayed(runnable, milliseconds);

    }
    public void invokeEveryMilliseconds(Runnable runnable, long milliseconds) {
        invokeEveryMilliseconds(runnable, milliseconds,true);
    }
    public void invokeEveryMilliseconds(Runnable runnable, long milliseconds, boolean runImmediately) {
        TimedCallback callback = new TimedCallback(runnable,milliseconds);
        timedCallbacks.add(callback);
        if (runImmediately) {
            callback.run();
        } else {
            postDelayed(callback,milliseconds);
        }
    }
    public void postEveryMilliseconds(Object request, long milliseconds) {
        postEveryMilliseconds(request,milliseconds,true);

    }
    public void postEveryMilliseconds(final Object request, long milliseconds, boolean postImmediately) {
         invokeEveryMilliseconds(new Runnable() {
             @Override
             public void run() {
                application.getBus().post(request);
             }
         },milliseconds, postImmediately);
    }
    private class TimedCallback implements Runnable {
        private final Runnable runnable;
        private final long delay;
        public TimedCallback(Runnable runnable, long delay) {
            this.runnable = runnable;
            this.delay = delay;
        }

        @Override
        public void run() {
            if (isPause) {
                return;
            }
            runnable.run();
            scheduler();
        }
        public void scheduler(){
            handler.postDelayed(this, delay);
        }
    }
}
