package com.rabe7.community.model.request.trader;

import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import javax.inject.Inject;

public class TraderExchangeRequest extends BaseObservable {

    @SerializedName("price")
    private Integer price;
    @SerializedName("paid_amount")
    private Integer paidAmount;
    @SerializedName("member_code")
    private String memberCode;

    @Expose
    private TraderExchangeErrors traderExchangeErrors;


    @Inject
    public TraderExchangeRequest(TraderExchangeErrors traderExchangeErrors) {
        this.traderExchangeErrors = traderExchangeErrors;
    }

    @Bindable
    public Integer getPrice() { return price; }
    @Bindable
    public Integer getPaidAmount() { return paidAmount; }
    @Bindable
    public String getMemberCode() { return memberCode; }
    @Bindable
    public TraderExchangeErrors getTraderExchangeErrors() { return traderExchangeErrors; }

    public void setPrice(Integer price) {
        if(this.price != null)
            traderExchangeErrors.setPriceError(null);
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    public void setPaidAmount(Integer paidAmount) {
        if(this.paidAmount != null)
            traderExchangeErrors.setPaidAmountError(null);
        this.paidAmount = paidAmount;
        notifyPropertyChanged(BR.paidAmount);
    }

    public void setMemberCode(String memberCode) {
        if(this.memberCode != null)
            traderExchangeErrors.setMemberCodeError(null);
        this.memberCode = memberCode;
        notifyPropertyChanged(BR.memberCode);
    }



    @Bindable
    public String getStringPrice() {
        if(price == null)
            return "";
        return Integer.toString(price);
    }

    public void setStringPrice(String price) {
        try {
            int val = Integer.parseInt(price);
            this.setPrice(val);
        }catch(NumberFormatException ex){
            this.setPrice(0);//default value
        }
    }

    @Bindable
    public String getStringPaidAmount() {
        if(paidAmount == null)
            return "";
        return Integer.toString(paidAmount);
    }

    public void setStringPaidAmount(String paidAmount) {
        try {
            int val = Integer.parseInt(paidAmount);
            this.setPaidAmount(val);
        }catch(NumberFormatException ex){
            this.setPaidAmount(0);//default value
        }
    }

}
