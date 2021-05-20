package com.rabe7.community.ui.main.points;

import android.app.Application;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.manager.connection.Resource;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.repository.credit.PointsRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class PointsViewModel extends ViewModel {

    //inject
    private final PointsRepository pointsRepository;
    private Application application;
    private ResponseManager responseManager;

    private MutableLiveData<String> observeRedeemClick;
    private MutableLiveData<String> observeError;
    private MutableLiveData<Boolean> observeCloseClick;
    private String points;


    @Inject
    public PointsViewModel(PointsRepository pointsRepository, Application application , ResponseManager responseManager) {
        this.pointsRepository = pointsRepository;
        this.application = application;
        this.responseManager = responseManager;

        observeRedeemClick = new MutableLiveData<>();
        observeError = new MutableLiveData<>();
        observeCloseClick = new MutableLiveData<>();
    }

    //Click:
    public void onRedeemClick(EditText points , String pointsValue) {
        if (validatePoints(points.getText().toString(),pointsValue))
            observeRedeemClick.setValue(points.getText().toString());
    }
    public void onCloseClicked(){observeCloseClick.setValue(true);}



    //getters:
    public LiveData<String> getRedeemClick() {
        if(observeRedeemClick!=null)
            observeRedeemClick = new MutableLiveData<>();
        return observeRedeemClick;
    }
    public LiveData<String> getError() {
        if(observeError!=null)
            observeError = new MutableLiveData<>();
        return observeError;
    }
    public LiveData<Boolean> getCloseClick() {
        if(observeCloseClick!=null)
            observeCloseClick = new MutableLiveData<>();
        return observeCloseClick;
    }

    public void getRepositoryData(){
        responseManager.loading();


        pointsRepository.redeemPoints(points)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<Void>>() {
                    @Override
                    public void onNext(Resource<Void> pointsResponse) {
                        if (pointsResponse != null) {

                            if(pointsResponse.getStatus().equals(Constants.SUCCESS)){
                                responseManager.success(pointsResponse.getMessage());
                            }else if(pointsResponse.getStatus().equals(Constants.FAILED)){
                                responseManager.failed(pointsResponse.getMessage());
                            }
                            responseManager.hideLoading();
                        }else {
                            responseManager.noConnection();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        responseManager.failed(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    //validation:
    private boolean validatePoints(String points, String pointsValue) {
        if(points.isEmpty() || Integer.parseInt(points) ==0){
            observeError.setValue(application.getString(R.string.error_points_empty));
            return false;
        }

        if (Integer.parseInt(points)>Integer.parseInt(pointsValue)){
            observeError.setValue(application.getString(R.string.error_points_valid));
            return false;
        }

        this.points = points;
        return true;
    }

}
