package com.rabe7.community.ui.auth.forgot_password;

import android.app.Application;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.manager.utilities.Validation;
import com.rabe7.community.repository.auth.ForgotPasswordRepository;
import com.rabe7.community.repository.auth.RegisterRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class ForgotPasswordViewModel extends ViewModel {

    //inject
    private final ForgotPasswordRepository forgotPasswordRepository;
    private Application application;
    private ResponseManager responseManager;

    private MutableLiveData<Boolean> observeSuccess;
    private MutableLiveData<String> observeError;
    private MutableLiveData<Boolean> observeCloseClick;

    @Inject
    public ForgotPasswordViewModel(ForgotPasswordRepository forgotPasswordRepository , Application application , ResponseManager responseManager) {

        this.forgotPasswordRepository = forgotPasswordRepository;
        this.application = application;
        this.responseManager = responseManager;

        observeSuccess = new MutableLiveData<>();
        observeCloseClick = new MutableLiveData<>();
        observeError = new MutableLiveData<>();
    }

    //Click:
    public void onForgotPasswordClicked(EditText userEmail){
        if(validateForgotPassword(userEmail.getText().toString()))
            getRepositoryData(userEmail.getText().toString());
    }
    public void onCloseClicked(){observeCloseClick.setValue(true);}



    public void getRepositoryData(String userEmail){
        responseManager.loading();


        forgotPasswordRepository.getUserEmail(userEmail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<Void>>() {
                    @Override
                    public void onNext(Resource<Void> forgotPasswordResponse) {
                        if (forgotPasswordResponse != null) {

                            if(forgotPasswordResponse.getStatus().equals(Constants.SUCCESS )){
                                responseManager.success(forgotPasswordResponse.getMessage());
                                observeSuccess.setValue(true);
                            }else if(forgotPasswordResponse.getStatus().equals(Constants.FAILED)){
                                observeError.setValue(forgotPasswordResponse.getMessage());
                            }
                            responseManager.hideLoading();
                        }else {
                            responseManager.noConnection();
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


    //getters:
    public LiveData<Boolean> observeSuccess() {
        if(observeSuccess!=null)
            observeSuccess = new MutableLiveData<>();
        return observeSuccess;
    }
    public LiveData<Boolean> getCloseClick() {
        if(observeCloseClick!=null)
            observeCloseClick = new MutableLiveData<>();
        return observeCloseClick;
    }
    public LiveData<String> observeError() {
        if(observeError!=null)
            observeError = new MutableLiveData<>();
        return observeError;
    }

    //validation:
    private boolean validateForgotPassword(String email){

        boolean isValid = true;

        if(Validation.isNullOrEmpty(email)){
            observeError.setValue((application.getString(R.string.error_forgot_password_empty)));
            isValid = false;
        }else if (!Validation.isEmail(email)){
            observeError.setValue((application.getString(R.string.error_forgot_password_valid)));
            isValid = false;
        }

        return isValid;

    }
}
