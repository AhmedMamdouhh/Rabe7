package com.rabe7.community.model.request.register.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import javax.inject.Inject;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class RegisterRequest extends BaseObservable {


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
    private RegisterErrors registerErrors;

    @Inject
    public RegisterRequest(RegisterErrors registerErrors) {
        this.registerErrors = registerErrors;
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
    public RegisterErrors getRegisterErrors(){return registerErrors;}
    @Bindable
    public String getUserReferralCode(){return userReferralCode;}


    //setters
    public void setUserEmail(String userEmail) {
        if(this.userEmail != null)
            registerErrors.setUserEmailError(null);
        this.userEmail = userEmail;
        notifyPropertyChanged(BR.userEmail);
    }
    public void setUserPassword(String userPassword) {
        if(this.userPassword != null)
            registerErrors.setUserPasswordError(null);
        this.userPassword = userPassword;
        notifyPropertyChanged(BR.userPassword);
    }
    public void setUserConfirmPassword(String userConfirmPassword) {
        if(this.userConfirmPassword != null)
            registerErrors.setUserConfirmPasswordError(null);
        this.userConfirmPassword = userConfirmPassword;
        notifyPropertyChanged(BR.userConfirmPassword);
    }
    public void setUserPhone(String userPhone) {
        if(this.userPhone != null)
            registerErrors.setUserPhoneError(null);
        this.userPhone = userPhone;
        notifyPropertyChanged(BR.userPhone);
    }
    public void setUserName(String userName) {
        if(this.userName !=null)
            registerErrors.setUserNameError(null);
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }
    public void setUserReferralCode(String userReferralCode) {
        if(this.userReferralCode !=null)
            registerErrors.setUserReferralError(null);
        this.userReferralCode = userReferralCode;
        notifyPropertyChanged(BR.userReferralCode);
    }



}
