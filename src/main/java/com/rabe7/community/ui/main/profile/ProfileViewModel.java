package com.rabe7.community.ui.main.profile;

import android.util.Log;
import android.view.View;

import com.rabe7.ResponseManager;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.model.entity.auth.User;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    //inject
    private ResponseManager responseManager;
    private MutableLiveData<Boolean> observeCoinsClick;
    private MutableLiveData<Boolean> observePointsClick;
    private MutableLiveData<Boolean> observeReferrerCodeClick;


    @Inject
    public ProfileViewModel(ResponseManager responseManager) {
        this.responseManager = responseManager;
        observeCoinsClick = new MutableLiveData<>();
        observePointsClick = new MutableLiveData<>();
        observeReferrerCodeClick = new MutableLiveData<>();
    }

    //Click listeners:
    public void onCoinsClick() {
        observeCoinsClick.setValue(true);
    }
    public void onPointsClick() {
        observePointsClick.setValue(true);
    }
    public void onReferrerCodeClick() { observeReferrerCodeClick.setValue(true); }


    public LiveData<Boolean> getObserveCoinsClick() {
        if (observeCoinsClick != null)
            observeCoinsClick = new MutableLiveData<>();
        return observeCoinsClick;
    }

    public LiveData<Boolean> getObservePointsClick() {
        if(observePointsClick != null)
            observePointsClick = new MutableLiveData<>();
        return observePointsClick;
    }

    public LiveData<Boolean> getObserveReferrerCodeClick() {
        if(observeReferrerCodeClick != null)
            observeReferrerCodeClick = new MutableLiveData<>();
        return observeReferrerCodeClick;
    }
}
