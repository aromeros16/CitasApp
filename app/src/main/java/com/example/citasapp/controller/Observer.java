package com.example.citasapp.controller;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class Observer implements LifecycleObserver {

    private String LOG_TAG = "DemoObserver";
    private String LOG_TAGAux = "" ;

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
       LOG_TAGAux = "onResume";
     //   Log.i(LOG_TAG, "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        LOG_TAGAux = "onPause";
      //  Log.i(LOG_TAG, "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        LOG_TAGAux = "onCreate";
      //  Log.i(LOG_TAG, "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        LOG_TAGAux = "onStart";
      //  Log.i(LOG_TAG, "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        LOG_TAGAux = "onStop";
       // Log.i(LOG_TAG, "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        LOG_TAGAux = "onDestroy";
       // Log.i(LOG_TAG, "onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {
       // LOG_TAGAux = owner.getLifecycle().getCurrentState().name();
        Log.i(LOG_TAG, owner.getLifecycle().getCurrentState().name());
        Log.i(LOG_TAG, LOG_TAGAux);
    }

    public String observer(){
        return LOG_TAGAux;
    }
}
