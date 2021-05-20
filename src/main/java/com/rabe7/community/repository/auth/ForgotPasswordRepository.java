package com.rabe7.community.repository.auth;


import com.rabe7.community.di.ui.auth.AuthScope;
import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.model.request.login.LoginRequest;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@AuthScope
public class ForgotPasswordRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public ForgotPasswordRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<Void>> getUserEmail(String email){
       return api.userForgotPassword(email)
                .subscribeOn(Schedulers.io());
    }
}
