package com.rabe7.community.repository.trader;

import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.model.request.trader.TraderExchangeRequest;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class TraderRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public TraderRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<Void>> exchangeCash(TraderExchangeRequest exchangeRequest){
        return api.traderExchange(exchangeRequest)
                .subscribeOn(Schedulers.io());
    }

}
