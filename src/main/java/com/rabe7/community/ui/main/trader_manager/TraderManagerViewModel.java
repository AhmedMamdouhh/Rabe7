package com.rabe7.community.ui.main.trader_manager;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.manager.utilities.Validation;
import com.rabe7.community.model.request.trader.TraderExchangeRequest;
import com.rabe7.community.repository.trader.TraderRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class TraderManagerViewModel extends ViewModel {

    //inject
    private final TraderRepository traderRepository;
    private Application application;
    private ResponseManager responseManager;

    private MutableLiveData<Boolean> observeSuccess;
    private MutableLiveData<String> observeError;


    @Inject
    public TraderManagerViewModel(TraderRepository traderRepository, Application application, ResponseManager responseManager) {

        this.traderRepository = traderRepository;
        this.application = application;
        this.responseManager = responseManager;

        observeSuccess = new MutableLiveData<>();
        observeError = new MutableLiveData<>();
    }

    //Click:
    public void onTraderRedeemClicked(TraderExchangeRequest traderRequest) {

        if (validateTraderExchange(traderRequest))
            getRepositoryData(traderRequest);

    }

    //getters:
    public MutableLiveData<Boolean> observeSuccess() {
        return observeSuccess;
    }

    public MutableLiveData<String> observeError() {
        return observeError;
    }

    public void getRepositoryData(TraderExchangeRequest traderRequest) {
        responseManager.loading();

        traderRepository.exchangeCash(traderRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<Void>>() {
                    @Override
                    public void onNext(Resource<Void> pointsResponse) {
                        if (pointsResponse != null) {

                            if (pointsResponse.getStatus().equals(Constants.SUCCESS)) {
                                responseManager.success(pointsResponse.getMessage());
                                observeSuccess.setValue(true);
                            } else if (pointsResponse.getStatus().equals(Constants.FAILED)) {
                                responseManager.failed(pointsResponse.getMessage());
                            }
                            responseManager.hideLoading();
                        } else {
                            responseManager.noConnection();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                        observeError.setValue(t.getMessage());
                        responseManager.failed(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    //validation:
    private boolean validateTraderExchange(TraderExchangeRequest traderRequest) {

        boolean isValid = true;

        if (Validation.isNullOrEmpty(traderRequest.getStringPrice())) {
            traderRequest.getTraderExchangeErrors().setPriceError(application.getString(R.string.error_trader_price));
            isValid = false;
        }  if (Validation.isNullOrEmpty(traderRequest.getStringPaidAmount())) {
            traderRequest.getTraderExchangeErrors().setPaidAmountError(application.getString(R.string.error_trader_paid_amount));
            isValid = false;
        }  if (Validation.isNullOrEmpty(traderRequest.getMemberCode())) {
            traderRequest.getTraderExchangeErrors().setMemberCodeError(application.getString(R.string.error_trader_member_code));
            isValid = false;
        }

        return isValid;

    }


}
