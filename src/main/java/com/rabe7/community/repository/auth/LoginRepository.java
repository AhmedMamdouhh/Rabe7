package com.rabe7.community.repository.auth;


import com.rabe7.community.di.ui.auth.AuthScope;
import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.model.request.login.LoginRequest;
import com.rabe7.community.manager.connection.Resource;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@AuthScope
public class LoginRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public LoginRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<User>> getLoginData(LoginRequest loginRequest){
       return api.userLogin(loginRequest)
                .subscribeOn(Schedulers.io());
    }
}
