package com.rabe7.community.di.base;

import android.app.Application;
import android.content.Context;

import com.rabe7.BaseActivity;
import com.rabe7.ResponseManager;
import com.rabe7.community.di.ui.auth.AuthComponent;
import com.rabe7.community.di.ui.main.MainComponent;
import com.rabe7.community.di.ui.splash.SplashComponent;
import com.rabe7.community.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                SubComponentsModule.class
        }
)
public interface AppComponent {

    ResponseManager responseManager();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(BaseActivity baseActivity);

    AuthComponent.Factory authComponent();
    MainComponent.Factory mainComponent();
    SplashComponent.Factory splashComponent();
}
