package com.rabe7.community.model.request.register.trader;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;
import com.rabe7.community.model.request.register.user.RegisterErrors;

import javax.inject.Inject;

public class RegisterTraderRequest extends BaseObservable {


    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;
    @Expose
    private String userConfirmPassword;
    @SerializedName("phone")
    private String userPhone;
    @SerializedName("name")
    private String userName;
    @SerializedName("referral_code")
    private String userReferralCode;

    @Expose
    private RegisterTraderErrors registerTraderErrors;

    @Inject
    public RegisterTraderRequest(RegisterTraderErrors registerTraderErrors) {
        this.registerTraderErrors = registerTraderErrors;
    }

    //getters
    @Bindable
    public String getUserEmail() { return userEmail; }
    @Bindable
    public String getUserPassword() { return userPassword; }
    @Bindable
    public String getUserConfirmPassword(){return userConfirmPassword;}
    @Bindable
    public String getUserPhone() { return userPhone; }
    @Bindable
    public String getUserName() { return userName; }
    @Bindable
    public RegisterTraderErrors getRegisterErrors(){return registerTraderErrors;}
    @Bindable
    public String getUserReferralCode(){return userReferralCode;}


    //setters
    public void setUserEmail(String userEmail) {
        if(this.userEmail != null)
            registerTraderErrors.setUserEmailError(null);
        this.userEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
    }
    public void setUserPassword(String userPassword) {
        if(this.userPassword != null)
            registerTraderErrors.setUserPasswordError(null);
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }
    public void setUserConfirmPassword(String userConfirmPassword) {
        if(this.userConfirmPassword != null)
            registerTraderErrors.setUserConfirmPasswordError(null);
        this.userConfirmPassword = userConfirmPassword;
        notifyPropertyChanged(BR.userConfirmPassword);
    }
    public void setUserPhone(String userPhone) {
        if(this.userPhone != null)
            registerTraderErrors.setUserPhoneError(null);
        this.userPhone = userPhone;
        notifyPropertyChanged(BR.userPhone);
    }
    public void setUserName(String userName) {
        if(this.userName !=null)
            registerTraderErrors.setUserNameError(null);
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }
    public void setUserReferralCode(String userReferralCode) {
        if(this.userReferralCode !=null)
            registerTraderErrors.setUserReferralError(null);
        this.userReferralCode = userReferralCode;
        notifyPropertyChanged(BR.userReferralCode);
    }



}
