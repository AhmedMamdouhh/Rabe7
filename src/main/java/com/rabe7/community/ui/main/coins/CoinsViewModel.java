package com.rabe7.community.ui.main.coins;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import javax.inject.Inject;

public class CoinsViewModel extends ViewModel {

    private MutableLiveData<View> observeTicketsClick;
    private MutableLiveData<View> observeVouchersClick;
    private MutableLiveData<Boolean> observeCloseClick;

    @Inject
    public CoinsViewModel() {
        observeTicketsClick = new MutableLiveData<>();
        observeVouchersClick = new MutableLiveData<>();
        observeCloseClick = new MutableLiveData<>();
    }

    //Click:
    public void onTicketsClicked(View view){
        observeTicketsClick.setValue(view);
    }
    public void onVouchersClicked(View view){
        observeVouchersClick.setValue(view);
    }
    public void onCloseClicked(){observeCloseClick.setValue(true);}

    //getters:
    public LiveData<View> getTicketsClick() {
        if(observeTicketsClick!=null)
            observeTicketsClick = new MutableLiveData<>();
        return observeTicketsClick;
    }
    public LiveData<View> getVouchersClick() {
        if(observeVouchersClick!=null)
            observeVouchersClick = new MutableLiveData<>();
        return observeVouchersClick;
    }

    public LiveData<Boolean> getCloseClick() {
        if(observeCloseClick !=null)
            observeCloseClick = new MutableLiveData<>();
        return observeCloseClick;
    }
}
