package com.rabe7.community.ui.main.creadit;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreditViewModel extends ViewModel {

    private MutableLiveData<Boolean> ticketsClick ;
    private MutableLiveData<Boolean> voucherClick ;
    private MutableLiveData<Boolean> couponClick ;

    @Inject
    CreditViewModel(){
        ticketsClick = new MutableLiveData<>();
        voucherClick = new MutableLiveData<>();
        couponClick = new MutableLiveData<>();
    }

    //listeners:
    public void onTicketsClicked(){
        ticketsClick.setValue(true);
    }
    public void onVouchersClicked(){
        voucherClick.setValue(true);
    }
    public void onCouponClicked(){
        couponClick.setValue(true);
    }

    public MutableLiveData<Boolean> getTicketsClick() {
        return ticketsClick;
    }

    public MutableLiveData<Boolean> getVoucherClick() {
        return voucherClick;
    }

    public MutableLiveData<Boolean> getCouponClick() {
        return couponClick;
    }
}
