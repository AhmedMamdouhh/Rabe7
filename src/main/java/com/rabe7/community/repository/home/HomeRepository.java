package com.rabe7.community.repository.home;

import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.model.entity.home.Offers;
import com.rabe7.community.manager.connection.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@MainScope
public class HomeRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public HomeRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<ArrayList<Offers>>> getHomeBannerOffers(){
        return api.homeBannerOffers()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Resource<ArrayList<Offers>>> getHomeOffers(){
        return api.homeOffers()
                .subscribeOn(Schedulers.io());
    }
}
