package com.rabe7.community.di.ui.auth;

import com.rabe7.community.ui.auth.AuthActivity;
import com.rabe7.community.ui.auth.forgot_password.ForgotPasswordSheet;

import dagger.Subcomponent;

@AuthScope
@Subcomponent(
        modules = {
                AuthModule.class,
                AuthViewModelModule.class
        }
)
public interface AuthComponent {

    @Subcomponent.Factory
    interface Factory{
        AuthComponent create();
    }

    void inject(AuthActivity authActivity);
    void inject(ForgotPasswordSheet forgotPasswordSheet);
}
