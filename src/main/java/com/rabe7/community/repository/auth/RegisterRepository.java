package com.rabe7.community.repository.auth;

import com.rabe7.community.di.ui.auth.AuthScope;
import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.model.request.register.trader.RegisterTraderRequest;
import com.rabe7.community.model.request.register.user.RegisterRequest;
import com.rabe7.community.manager.connection.Resource;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;


public class RegisterRepository {

    private static final String TAG = "RegisterRepository";
    private final Api api;

    @Inject
    public RegisterRepository(Api api){
        this.api = api;
    }

    @AuthScope
    public Flowable<Resource<User>> getRegisterData(RegisterRequest registerRequest){
        return api.userRegister(registerRequest)
                .subscribeOn(Schedulers.io());
    }

    @MainScope
    public Flowable<Resource<User>> traderRegister(RegisterTraderRequest registerRequest){
        return api.traderRegister(registerRequest)
                .subscribeOn(Schedulers.io());
    }
}
