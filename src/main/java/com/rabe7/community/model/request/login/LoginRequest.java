package com.rabe7.community.model.request.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import javax.inject.Inject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class LoginRequest extends BaseObservable {

    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;


    //inject:
    @Expose
    private LoginErrors loginErrors;

    @Inject
    public LoginRequest(LoginErrors loginErrors) {
        this.loginErrors = loginErrors;
    }


    public void setUserEmail(String userEmail) {
        if(this.userEmail != null)
            loginErrors.setLoginErrorEmail(null);
        this.userEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
    }
    public void setUserPassword(String userPassword) {
        if(this.userPassword != null)
            loginErrors.setLoginErrorPassword(null);
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }


    @Bindable
    public String getUserPassword() { return userPassword; }
    @Bindable
    public String getUserEmail() { return userEmail; }
    @Bindable
    public LoginErrors getLoginErrors() {
        return loginErrors;
    }
}
