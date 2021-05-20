package com.rabe7.community.model.entity.credit;

import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Vouchers extends BaseObservable {

    @SerializedName("id")
    private int vouchersId;
    @SerializedName("price")
    private String vouchersPrice;
    @SerializedName("expires_at")
    private String vouchersExpire;
    @SerializedName("name")
    private String vouchersName;
    @SerializedName("description")
    private String vouchersDescription;

    @Bindable
    public int getVouchersId() { return vouchersId; }
    @Bindable
    public String getVouchersPrice() { return vouchersPrice; }
    @Bindable
    public String getVouchersExpire() { return vouchersExpire; }
    @Bindable
    public String getVouchersName() { return vouchersName; }
    @Bindable
    public String getVouchersDescription() { return vouchersDescription; }

    public void setVouchersId(int vouchersId) {
        this.vouchersId = vouchersId;
        notifyPropertyChanged(BR.vouchersId);
    }

    public void setVouchersPrice(String vouchersPrice) {
        this.vouchersPrice = vouchersPrice;
        notifyPropertyChanged(BR.vouchersPrice);
    }

    public void setVouchersExpire(String vouchersExpire) {
        this.vouchersExpire = vouchersExpire;
        notifyPropertyChanged(BR.vouchersExpire);
    }

    public void setVouchersName(String vouchersName) {
        this.vouchersName = vouchersName;
        notifyPropertyChanged(BR.vouchersName);
    }

    public void setVouchersDescription(String vouchersDescription) {
        this.vouchersDescription = vouchersDescription;
        notifyPropertyChanged(BR.vouchersDescription);
    }
}
