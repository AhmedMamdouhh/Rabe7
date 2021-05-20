package com.rabe7.community.model.request.login;

import com.rabe7.community.BR;

import javax.inject.Inject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class LoginErrors extends BaseObservable {

    private String loginErrorEmail;
    private String loginErrorPassword;


    @Inject
    LoginErrors(){}


    //setters:
    public void setLoginErrorEmail(String loginErrorEmail) {
        this.loginErrorEmail = loginErrorEmail;
        notifyPropertyChanged(BR.loginErrorEmail);
    }
    public void setLoginErrorPassword(String loginErrorPassword) {
        this.loginErrorPassword = loginErrorPassword;
        notifyPropertyChanged(BR.loginErrorPassword);
    }

    //getters:
    @Bindable
    public String getLoginErrorPassword() {
        return loginErrorPassword;
    }
    @Bindable
    public String getLoginErrorEmail() {
        return loginErrorEmail;
    }
}
