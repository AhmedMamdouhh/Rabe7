package com.rabe7.community.di.ui.splash;

import com.rabe7.community.di.ui.auth.AuthModule;
import com.rabe7.community.di.ui.auth.AuthScope;
import com.rabe7.community.di.ui.auth.AuthViewModelModule;
import com.rabe7.community.ui.auth.AuthActivity;
import com.rabe7.community.ui.splash.SplashActivity;

import dagger.Subcomponent;

@AuthScope
@Subcomponent()
public interface SplashComponent {

    @Subcomponent.Factory
    interface Factory{
        SplashComponent create();
    }

    void inject(SplashActivity splashActivity);
}
