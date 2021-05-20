package com.rabe7.community.model.entity.credit;

import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Coupons extends BaseObservable {

    @SerializedName("Description")
    private String couponDescription;

    @SerializedName("AmountType")
    private String couponAmountType;

    @SerializedName("BuyingAmount")
    private int couponBuyingAmount;

    @SerializedName("Quantity")
    private int couponQuantity;

    @SerializedName("Id")
    private int couponId;

    @SerializedName("Image")
    private String couponImage;

    @SerializedName("Code")
    private String couponCode;

    public Coupons(String couponDescription, int couponBuyingAmount) {
        this.couponDescription = couponDescription;
        this.couponBuyingAmount = couponBuyingAmount;
    }

    @Bindable
    public String getCouponDescription() { return couponDescription; }
    @Bindable
    public int getCouponBuyingAmount() { return couponBuyingAmount; }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
        notifyPropertyChanged(BR.couponDescription);
    }

    public void setCouponBuyingAmount(int couponBuyingAmount) {
        this.couponBuyingAmount = couponBuyingAmount;
        notifyPropertyChanged(BR.couponBuyingAmount);
    }
}
