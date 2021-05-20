package com.rabe7;


import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.rabe7.community.di.base.AppComponent;
import com.rabe7.community.di.base.DaggerAppComponent;
import com.rabe7.community.di.ui.auth.AuthComponent;
import com.rabe7.community.di.ui.main.MainComponent;
import com.rabe7.community.di.ui.splash.SplashComponent;


public class BaseApplication extends Application {

    private AppComponent appComponent;
    private AuthComponent authComponent = null;
    private MainComponent mainComponent = null;
    private SplashComponent splashComponent =null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        initAppComponent();
    }

    public AuthComponent authComponent(){
        if(authComponent == null)
            authComponent = appComponent.authComponent().create();
        return authComponent;
    }

    public void releaseAuthComponent(){
        authComponent = null;
    }

    public MainComponent mainComponent(){
        if(mainComponent == null)
            mainComponent = appComponent.mainComponent().create();
        return mainComponent;
    }

    public void releaseMainComponent(){
        mainComponent = null;
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();
    }

    public AppComponent appComponent(){
        return appComponent;
    }

    public SplashComponent splashComponent(){
        if(splashComponent == null)
            splashComponent = appComponent.splashComponent().create();
        return splashComponent;
    }

    public void releaseSplashComponent(){splashComponent = null;}
}
