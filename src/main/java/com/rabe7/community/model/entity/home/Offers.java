package com.rabe7.community.model.entity.home;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import javax.inject.Inject;

public class Offers extends BaseObservable {

    @SerializedName("id")
    private String offerId;
    @SerializedName("price")
    private String offerPrice;
    @SerializedName("image")
    private String offerImage;
    @SerializedName("name")
    private String offerName;
    @SerializedName("description")
    private String offerDescription;

    @Inject
    public Offers() {}


    @Bindable
    public String getOfferId() { return offerId; }
    @Bindable
    public String getOfferPrice() { return offerPrice; }
    @Bindable
    public String getOfferImage() { return offerImage; }
    @Bindable
    public String getOfferName() { return offerName; }


    public void setOfferId(String offerId) {
        this.offerId = offerId;
        notifyPropertyChanged(BR.offerId);
    }
    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
        notifyPropertyChanged(BR.offerPrice);
    }
    public void setOfferImage(String offerImage) {
        this.offerImage = offerImage;
        notifyPropertyChanged(BR.offerImage);
    }
    public void setOfferName(String offerName) {
        this.offerName = offerName;
        notifyPropertyChanged(BR.offerName);
    }
}
