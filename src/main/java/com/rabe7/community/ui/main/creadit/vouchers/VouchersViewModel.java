package com.rabe7.community.ui.main.creadit.vouchers;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabe7.ResponseManager;
import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.repository.credit.VouchersRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

@MainScope
public class VouchersViewModel extends ViewModel {

    //inject
    private final VouchersRepository vouchersRepository;
    private ResponseManager responseManager;


    private MutableLiveData<ArrayList<Vouchers>> observeVouchers;
    private MutableLiveData<Boolean> observeBuyVouchers;
    private MutableLiveData<String> observeBuyVouchersSuccess;
    private MutableLiveData<String> observeErrors;

    @Inject
    public VouchersViewModel(VouchersRepository vouchersRepository, ResponseManager responseManager) {
        this.vouchersRepository = vouchersRepository;
        this.responseManager = responseManager;

        observeVouchers = new MutableLiveData<>();
        observeBuyVouchers = new MutableLiveData<>();
        observeBuyVouchersSuccess = new MutableLiveData<>();
        observeErrors = new MutableLiveData<>();
    }

    //click:
    public void onVouchersClicked(Integer voucherId) { getBuyVoucherRepository(voucherId); }

    private void getBuyVoucherRepository(Integer ticketId) {
        responseManager.loading();


        vouchersRepository.buyVouchers(ticketId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<Void>>() {
                    @Override
                    public void onNext(Resource<Void> vouchersResponse) {
                        if (vouchersResponse != null) {
                            responseManager.hideLoading();

                            if (vouchersResponse.getStatus().equals(Constants.SUCCESS))
                                observeBuyVouchers.setValue(true);


                            else
                                observeErrors.setValue(vouchersResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        responseManager.noConnection();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    private void getVouchersRepository() {

        responseManager.loading();


        vouchersRepository.getVouchersData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<ArrayList<Vouchers>>>() {
                    @Override
                    public void onNext(Resource<ArrayList<Vouchers>> vouchersResponse) {
                        if (vouchersResponse != null) {
                            responseManager.hideLoading();

                            if (vouchersResponse.getStatus().equals(Constants.SUCCESS))
                                observeVouchers.setValue(vouchersResponse.getData());
                            else
                                observeErrors.setValue(vouchersResponse.getMessage());

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        responseManager.noConnection();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void callVouchersRepository(){
        getVouchersRepository();}

    public LiveData<ArrayList<Vouchers>> getVouchers() {
        if(observeVouchers !=null)
            observeVouchers = new MutableLiveData<>();
        return observeVouchers;
    }
    public LiveData<Boolean> getBuyVouchers() {
        if(observeBuyVouchers !=null)
            observeBuyVouchers = new MutableLiveData<>();
        return observeBuyVouchers;
    }
    public LiveData<String> getVouchersError() {
        if(observeErrors != null)
            observeErrors = new MutableLiveData<>();
        return observeErrors;
    }
    public LiveData<String> getBuyVouchersSuccess() {
        if(observeBuyVouchersSuccess != null)
            observeBuyVouchersSuccess = new MutableLiveData<>();
        return observeBuyVouchersSuccess;
    }

}
