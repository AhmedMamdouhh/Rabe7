package com.rabe7.community.di.ui.auth;

import com.rabe7.community.di.ui.auth.keys.AuthViewModelKey;
import com.rabe7.community.di.ui.main.keys.MainViewModelKey;
import com.rabe7.community.ui.auth.forgot_password.ForgotPasswordViewModel;
import com.rabe7.community.ui.auth.login.LoginViewModel;
import com.rabe7.community.ui.auth.register.RegisterViewModel;
import com.rabe7.community.view_model.AuthViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {

    @AuthScope
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(AuthViewModelFactory factory);

    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(LoginViewModel.class)
    public abstract ViewModel bindsLoginViewModel(LoginViewModel loginViewModel);

    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(RegisterViewModel.class)
    public abstract ViewModel bindsRegisterViewModel(RegisterViewModel registerViewModel);

    @AuthScope
    @Binds
    @IntoMap
    @AuthViewModelKey(ForgotPasswordViewModel.class)
    public abstract ViewModel bindsForgotPasswordViewModel(ForgotPasswordViewModel forgotPasswordViewModel);

}
