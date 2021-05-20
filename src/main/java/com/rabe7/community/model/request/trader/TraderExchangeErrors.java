package com.rabe7.community.model.request.trader;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import javax.inject.Inject;

public class TraderExchangeErrors extends BaseObservable {

    private String priceError;
    private String paidAmountError;
    private String memberCodeError;

    @Inject

    public TraderExchangeErrors() { }

    @Bindable
    public String getPriceError() { return priceError; }
    @Bindable
    public String getPaidAmountError() { return paidAmountError; }
    @Bindable
    public String getMemberCodeError() { return memberCodeError; }


    public void setPriceError(String priceError) {
        this.priceError = priceError;
        notifyPropertyChanged(BR.priceError);
    }

    public void setPaidAmountError(String paidAmountError) {
        this.paidAmountError = paidAmountError;
        notifyPropertyChanged(BR.paidAmountError);
    }

    public void setMemberCodeError(String memberCodeError) {
        this.memberCodeError = memberCodeError;
        notifyPropertyChanged(BR.memberCodeError);
    }
}
