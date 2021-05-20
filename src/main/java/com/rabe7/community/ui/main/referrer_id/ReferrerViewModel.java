package com.rabe7.community.ui.main.referrer_id;

import android.app.Application;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.repository.other.ReferralCodeRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class ReferrerViewModel extends ViewModel {

    //inject
    private final ReferralCodeRepository referralCodeRepository;
    private Application application;
    private ResponseManager responseManager;

    private MutableLiveData<Boolean> observeSuccess;
    private MutableLiveData<String> observeError;
    private MutableLiveData<Boolean> observeCloseClick;


    @Inject
    public ReferrerViewModel(ReferralCodeRepository referralCodeRepository, Application application , ResponseManager responseManager) {
        this.referralCodeRepository = referralCodeRepository;
        this.application = application;
        this.responseManager = responseManager;

        observeSuccess = new MutableLiveData<>();
        observeError = new MutableLiveData<>();
        observeCloseClick = new MutableLiveData<>();
    }

    //Click:
    public void onSubmitClicked(EditText referrerCode) {
        if (validateReferrerCode(referrerCode.getText().toString())) {
            getRepositoryData(referrerCode.getText().toString());
        }
    }
    public void onCloseClicked(){observeCloseClick.setValue(true);}



    public void getRepositoryData(String referrerCode){
        responseManager.loading();

        referralCodeRepository.setReferralCode(referrerCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<Void>>() {
                    @Override
                    public void onNext(Resource<Void> ReferrerResponse) {
                        if (ReferrerResponse != null) {

                            if(ReferrerResponse.getStatus().equals(Constants.SUCCESS)){
                                responseManager.success(ReferrerResponse.getMessage());
                            }else if(ReferrerResponse.getStatus().equals(Constants.FAILED)){
                                observeCloseClick.setValue(true);
                                responseManager.failed(ReferrerResponse.getMessage());
                            }
                            responseManager.hideLoading();
                        }else {
                            responseManager.noConnection();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        observeCloseClick.setValue(true);
                        responseManager.failed(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    //getters:
    public LiveData<Boolean> getSuccess() {
        if(observeSuccess !=null)
            observeSuccess = new MutableLiveData<>();
        return observeSuccess;
    }
    public LiveData<String> getError() {
        if(observeError!=null)
            observeError = new MutableLiveData<>();
        return observeError;
    }
    public LiveData<Boolean> getCloseClick() {
        if(observeCloseClick!=null)
            observeCloseClick = new MutableLiveData<>();
        return observeCloseClick;
    }

    //validation:
    private boolean validateReferrerCode(String referrerCode) {
        if(referrerCode.isEmpty()){
            observeError.setValue(application.getString(R.string.error_referrer_empty));
            return false;
        }else if(referrerCode.length() < Constants.REFERRAL_CODE_SIZE){
            observeError.setValue(application.getString(R.string.error_referrer_valid));
            return false;
        }

        return true;
    }

}
