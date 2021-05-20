package com.rabe7.community.ui.auth;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rabe7.BaseActivity;
import com.rabe7.BaseApplication;
import com.rabe7.community.R;
import com.rabe7.community.databinding.ActivityAuthBinding;
import com.rabe7.community.di.ui.auth.AuthComponent;
import com.rabe7.community.model.request.login.LoginRequest;
import com.rabe7.community.model.request.register.user.RegisterRequest;
import com.rabe7.community.ui.auth.forgot_password.ForgotPasswordSheet;
import com.rabe7.community.ui.auth.login.LoginViewModel;
import com.rabe7.community.ui.auth.register.RegisterViewModel;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.view_model.AuthViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class AuthActivity extends BaseActivity {

    @Inject
    AuthViewModelFactory providerFactory;
    @Inject
    LoginRequest loginRequest;
    @Inject
    RegisterRequest registerRequest;

    public AuthComponent authComponent;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();

        ActivityAuthBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_auth);

        loginViewModel = new ViewModelProvider(this, providerFactory).get(LoginViewModel.class);
        binding.setLoginObject(loginRequest);
        binding.setLoginClickListener(loginViewModel);

        registerViewModel = new ViewModelProvider(this, providerFactory).get(RegisterViewModel.class);
        binding.setSignUpObject(registerRequest);
        binding.setSignUpClickListener(registerViewModel);


        binding.setLifecycleOwner(this);

        observeLoginSuccess();
        observeForgotPasswordClicked();
        observeRegisterSuccess();
    }

    private void observeRegisterSuccess() {
        registerViewModel.observeSuccess().removeObservers(this);
        registerViewModel.observeSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void observeForgotPasswordClicked() {
        loginViewModel.observeForgotPasswordClick().removeObservers(this);
        loginViewModel.observeForgotPasswordClick().observe(this, new Observer<View>() {
            @Override
            public void onChanged(View view) {
                new ForgotPasswordSheet().show(getSupportFragmentManager(), "forgotPassword");
            }
        });

    }

    @Override
    protected void inject() {
        authComponent = ((BaseApplication) getApplication())
                .authComponent();

        authComponent.inject(this);
    }

    private void observeLoginSuccess() {
        loginViewModel.observeSuccess().removeObservers(this);
        loginViewModel.observeSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
