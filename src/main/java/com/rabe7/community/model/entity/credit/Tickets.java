package com.rabe7.community.model.entity.credit;

import com.google.gson.annotations.SerializedName;
import com.rabe7.community.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Tickets extends BaseObservable{


    @SerializedName("id")
    private Integer ticketId;
    @SerializedName("price")
    private String ticketPrice;
    @SerializedName("logo")
    private String ticketLogo;
    @SerializedName("owner_name")
    private String ticketOwnerName;
    @SerializedName("name")
    private String ticketName;
    @SerializedName("description")
    private String ticketDescription;
    @SerializedName("currency")
    private String ticketCurrency;

    //getters:
    @Bindable
    public String getTicketPrice() { return ticketPrice; }
    @Bindable
    public String getTicketOwnerName() { return ticketOwnerName; }
    @Bindable
    public String getTicketName() { return ticketName; }
    @Bindable
    public String getTicketDescription() { return ticketDescription; }
    @Bindable
    public String getTicketCurrency() { return ticketCurrency; }
    @Bindable
    public Integer getTicketId() { return ticketId; }
    public String getTicketLogo() { return ticketLogo; }

    //setters:
    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
        notifyPropertyChanged(BR.ticketPrice);
    }

    public void setTicketOwnerName(String ticketOwnerName) {
        this.ticketOwnerName = ticketOwnerName;
        notifyPropertyChanged(BR.ticketOwnerName);
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
        notifyPropertyChanged(BR.ticketName);
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
        notifyPropertyChanged(BR.ticketDescription);
    }

    public void setTicketCurrency(String ticketCurrency) {
        this.ticketCurrency = ticketCurrency;
        notifyPropertyChanged(BR.ticketCurrency);
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
        notifyPropertyChanged(BR.ticketId);
    }
}
