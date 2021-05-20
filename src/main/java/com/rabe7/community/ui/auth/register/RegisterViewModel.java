package com.rabe7.community.ui.auth.register;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.di.ui.auth.AuthScope;
import com.rabe7.community.manager.utilities.AnimationHelper;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.manager.utilities.Validation;
import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.model.request.register.user.RegisterRequest;
import com.rabe7.community.repository.auth.RegisterRepository;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.ui.auth.login.LoginViewModel;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

@AuthScope
public class RegisterViewModel extends ViewModel {

    private static final String TAG = "RegisterViewModel";
    public static boolean isOpen = true;

    //inject
    private final RegisterRepository registerRepository;
    private Application application;
    private ResponseManager responseManager;

    private MutableLiveData<Boolean> observeSuccess;


    @Inject
    public RegisterViewModel(RegisterRepository registerRepository, Application application, ResponseManager responseManager) {
        this.registerRepository = registerRepository;
        this.application = application;
        this.responseManager = responseManager;

        observeSuccess = new MutableLiveData<>();
    }

    public void onRegisterSubmitClicked(RegisterRequest registerRequest) {
        if (validate(registerRequest))
            getRepositoryData(registerRequest);
    }

    public void onRegisterContainerClicked(View signUpContainer, View loginContainerDetails) {

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int height = metrics.heightPixels;

        if (!isOpen) {
            if (LoginViewModel.isOpen) {
                AnimationHelper.expandAndCollapse(loginContainerDetails, 500, 0, "H");
                LoginViewModel.isOpen = false;
            }

            AnimationHelper.expandAndCollapse(signUpContainer, 500, height / 2, "H");
            isOpen = true;
        } else {
            AnimationHelper.expandAndCollapse(signUpContainer, 500, 0, "H");
            isOpen = false;
        }

    }

    //Get data from repository:
    private void getRepositoryData(RegisterRequest registerRequest) {


        responseManager.loading();


        registerRepository.getRegisterData(registerRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<User>>() {
                    @Override
                    public void onNext(Resource<User> resource) {

                        if (resource != null) {
                            if (resource.getStatus().equals(Constants.SUCCESS)) {
                                observeSuccess.setValue(true);
                                responseManager.authenticated(resource.getData());
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

    //validate register data:
    private boolean validate(RegisterRequest registerRequest) {
        boolean valid = true;
        //name:
        if (Validation.isNullOrEmpty(registerRequest.getUserName())) {
            registerRequest.getRegisterErrors().setUserNameError(application.getString(R.string.error_register_name_empty));
            valid = false;
        }

        //phone:
        if (Validation.isNullOrEmpty(registerRequest.getUserPhone())) {
            registerRequest.getRegisterErrors().setUserPhoneError(application.getString(R.string.error_register_phone_empty));
            valid = false;
        } else if (!Validation.isPhone(registerRequest.getUserPhone())) {
            registerRequest.getRegisterErrors().setUserPhoneError(application.getString(R.string.error_register_phone_wrong));
            valid = false;
        }

        //email:
        if (Validation.isNullOrEmpty(registerRequest.getUserEmail())) {
            registerRequest.getRegisterErrors().setUserEmailError(application.getString(R.string.error_register_email_empty));
            valid = false;
        } else if (!Validation.isEmail(registerRequest.getUserEmail())) {
            registerRequest.getRegisterErrors().setUserEmailError(application.getString(R.string.error_register_email_wrong));
            valid = false;
        }

        //password:
        if (Validation.isNullOrEmpty(registerRequest.getUserPassword())) {
            registerRequest.getRegisterErrors().setUserPasswordError(application.getString(R.string.error_register_password_empty));
            valid = false;
        } else if (!Validation.isPassword(registerRequest.getUserPassword())) {
            registerRequest.getRegisterErrors().setUserPasswordError(application.getString(R.string.error_register_password_wrong));
            valid = false;
        }

        //confirm password:
        if (Validation.isNullOrEmpty(registerRequest.getUserConfirmPassword())) {
            registerRequest.getRegisterErrors().setUserConfirmPasswordError(application.getString(R.string.error_register_confirm_password_empty));
            valid = false;
        } else if (!Validation.isPasswordMatch(registerRequest.getUserPassword(), registerRequest.getUserConfirmPassword())) {
            registerRequest.getRegisterErrors().setUserConfirmPasswordError(application.getString(R.string.error_register_confirm_password_wrong));
            valid = false;
        }

        if (!Validation.isNullOrEmpty(registerRequest.getUserReferralCode())) {
            if (registerRequest.getUserReferralCode().length() < Constants.REFERRAL_CODE_SIZE) {
                registerRequest.getRegisterErrors().setUserReferralError(application.getString(R.string.error_register_referrer_size));
                valid = false;
            }
        }

        return valid;

    }


}
