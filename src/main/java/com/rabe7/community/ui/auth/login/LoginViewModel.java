package com.rabe7.community.ui.auth.login;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.di.ui.auth.AuthScope;
import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.manager.utilities.AnimationHelper;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.manager.utilities.Validation;
import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.model.request.login.LoginRequest;
import com.rabe7.community.repository.auth.LoginRepository;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.ui.auth.register.RegisterViewModel;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

@AuthScope
public class LoginViewModel extends ViewModel {

    public static boolean isOpen = true;

    private static final String TAG = "LoginViewModel";

    //inject
    private final LoginRepository loginRepository;
    private Application application;
    private ResponseManager responseManager;

    private MutableLiveData<Boolean> observeSuccess;
    private MutableLiveData<View> observeForgotPasswordClick;

    @Inject
    public LoginViewModel(LoginRepository loginRepository, Application application, ResponseManager responseManager) {
        this.loginRepository = loginRepository;
        this.application = application;
        this.responseManager = responseManager;

        observeSuccess = new MutableLiveData<>();
        observeForgotPasswordClick = new MutableLiveData<>();
    }


    //Clicked:
    public void onLoginSubmitClicked(LoginRequest loginRequest) {
        if (loginValidation(loginRequest)) {
            getRepositoryData(loginRequest);
        }
    }
    public void onLoginContainerClicked(View loginContainerDetails, View signUpContainer) {

        Log.e(TAG, "onLoginContainerClicked: ");
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int height = metrics.heightPixels;

        if (!isOpen) {

            if (RegisterViewModel.isOpen) {
                AnimationHelper.expandAndCollapse(signUpContainer, 500, 0, "H");
                RegisterViewModel.isOpen = false;
            }

            AnimationHelper.expandAndCollapse(loginContainerDetails, 500, height / 2, "H");
            isOpen = true;
        } else {
            AnimationHelper.expandAndCollapse(loginContainerDetails, 500, 0, "H");
            isOpen = false;
        }
    }
    public void onForgotPasswordClicked(View view) {
        observeForgotPasswordClick.setValue(view);
    }

    //Get data from repository:
    private void getRepositoryData(LoginRequest loginRequest) {


        responseManager.loading();


        loginRepository.getLoginData(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<User>>() {
                    @Override
                    public void onNext(Resource<User> resource) {

                        if (resource != null) {
                            if (resource.getStatus().equals(Constants.SUCCESS)) {

                                if (resource.getData() != null) {
                                    observeSuccess.setValue(true);
                                    responseManager.authenticated(resource.getData());
                                } else
                                    responseManager.failed(resource.getMessage());

                            } else if (resource.getStatus().equals(Constants.FAILED)) {
                                responseManager.failed(resource.getMessage());
                            }

                        } else
                            responseManager.noConnection();

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

    public LiveData<Boolean> observeSuccess() {
        if(observeSuccess != null)
            observeSuccess = new MutableLiveData<>();
        return observeSuccess;
    }
    public LiveData<View> observeForgotPasswordClick() {
        if(observeForgotPasswordClick != null)
            observeForgotPasswordClick = new MutableLiveData<>();
        return observeForgotPasswordClick;
    }

    //Validation:
    private boolean loginValidation(LoginRequest loginRequest) {

        boolean valid = true;

        if (Validation.isNullOrEmpty(loginRequest.getUserEmail())) {
            loginRequest.getLoginErrors().setLoginErrorEmail(application.getResources().getString(R.string.error_login_name_empty));
            valid = false;
        }
        if (Validation.isNullOrEmpty(loginRequest.getUserPassword())) {
            loginRequest.getLoginErrors().setLoginErrorPassword(application.getResources().getString(R.string.error_login_password));
            valid = false;
        }

        return valid;
    }

}
