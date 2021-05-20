package com.rabe7.community.repository.credit;

import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.model.entity.credit.Vouchers;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class VouchersRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public VouchersRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<ArrayList<Vouchers>>> getVouchersData(){
        return api.getVouchers()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Resource<Void>> buyVouchers(Integer voucherId){
        return api.buyVouchers(voucherId)
                .subscribeOn(Schedulers.io());
    }
}
