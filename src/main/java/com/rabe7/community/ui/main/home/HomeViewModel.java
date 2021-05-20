package com.rabe7.community.ui.main.home;


import android.util.Log;

import com.rabe7.ResponseManager;
import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.model.entity.home.Offers;
import com.rabe7.community.repository.home.HomeRepository;
import com.rabe7.community.manager.connection.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

@MainScope
public class HomeViewModel extends ViewModel {

    //inject
    private final HomeRepository homeRepository;
    private ResponseManager responseManager;


    private MutableLiveData<ArrayList<Offers>> observeOffersSuccess;
    private MutableLiveData<ArrayList<Offers>> observeOffersBannerSuccess;

    private boolean isFirstRequest =false;

    @Inject
    public HomeViewModel(HomeRepository homeRepository, ResponseManager responseManager) {
        this.homeRepository = homeRepository;
        this.responseManager = responseManager;

        observeOffersSuccess = new MutableLiveData<>();
        observeOffersBannerSuccess = new MutableLiveData<>();
    }

    public void getRepositoryData() {
        responseManager.loading();

        homeRepository.getHomeBannerOffers()
                .mergeWith(homeRepository.getHomeOffers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<ArrayList<Offers>>>() {
                    @Override
                    public void onNext(Resource<ArrayList<Offers>> offersResponse) {
                        if (offersResponse != null) {
                            ArrayList<Offers> offers = offersResponse.getData();
                            if(!isFirstRequest) {
                                observeOffersSuccess.setValue(offers);
                                isFirstRequest = true;
                            }else {
                                observeOffersBannerSuccess.setValue(offers);
                            }

                            responseManager.hideLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        responseManager.failed(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public MutableLiveData<ArrayList<Offers>> observeOffersSuccess() {
        return observeOffersSuccess;
    }

    public MutableLiveData<ArrayList<Offers>> observeOffersBannerSuccess() {
        return observeOffersBannerSuccess;
    }

}
