package com.rabe7.community.repository.other;

import androidx.annotation.Nullable;

import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.manager.connection.Resource;
import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class ReferralCodeRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public ReferralCodeRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<Void>> setReferralCode(String referralCode){
        return api.setReferralCode(referralCode)
                .subscribeOn(Schedulers.io());
    }

}
