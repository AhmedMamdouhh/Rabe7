package com.rabe7.community.model.request.register.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import javax.inject.Inject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class RegisterErrors extends BaseObservable {

    private String userEmailError;
    private String userPasswordError;
    private String userConfirmPasswordError;
    private String userPhoneError;
    private String userNameError;
    private String userReferralError;


    @Inject
    RegisterErrors(){}

    //getters
    @Bindable
    public String getUserEmailError() {
        return userEmailError;
    }
    @Bindable
    public String getUserPasswordError() {
        return userPasswordError;
    }
    @Bindable
    public String getUserConfirmPasswordError() {
        return userConfirmPasswordError;
    }
    @Bindable
    public String getUserPhoneError() {
        return userPhoneError;
    }
    @Bindable
    public String getUserNameError() { return userNameError; }
    @Bindable
    public String getUserReferralError() { return userReferralError; }

    //setters
    public void setUserEmailError(String userEmailError) {
        this.userEmailError = userEmailError;
        notifyPropertyChanged(BR.userEmailError);
    }
    public void setUserPasswordError(String userPasswordError) {
        this.userPasswordError = userPasswordError;
        notifyPropertyChanged(BR.userPasswordError);
    }
    public void setUserConfirmPasswordError(String userConfirmPasswordError) {
        this.userConfirmPasswordError = userConfirmPasswordError;
        notifyPropertyChanged(BR.userConfirmPasswordError);
    }
    public void setUserPhoneError(String userPhoneError) {
        this.userPhoneError = userPhoneError;
        notifyPropertyChanged(BR.userPhoneError);
    }
    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
        notifyPropertyChanged(BR.userNameError);
    }
    public void setUserReferralError(String userReferralError) {
        this.userReferralError = userReferralError;
        notifyPropertyChanged(BR.userReferralError);
    }
}
