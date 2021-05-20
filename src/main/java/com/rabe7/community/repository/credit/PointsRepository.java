package com.rabe7.community.repository.credit;

import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.manager.connection.Resource;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class PointsRepository {

    private final Api api;

    @Inject
    public PointsRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<Void>> redeemPoints(String points){
        return api.redeemPoints(points)
                .subscribeOn(Schedulers.io());
    }
}
