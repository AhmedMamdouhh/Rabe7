package com.rabe7;


import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.manager.connection.ResponseResource;
import com.rabe7.community.model.entity.auth.User;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

@Singleton
public class ResponseManager {

    private MutableLiveData<ResponseResource> responseManager;

    //Inject:
    private ResponseResource responseResource;
    private SharedPreferences sharedPreferences;
    private User currentUser;

    @Inject
    public ResponseManager(ResponseResource responseResource , SharedPreferences sharedPreferences , User currentUser ) {
        responseManager = new MutableLiveData<>();

        this.responseResource = responseResource;
        this.sharedPreferences = sharedPreferences;
        this.currentUser = currentUser;
    }

    public boolean isAuthenticated(){
        if(sharedPreferences.contains(Constants.USER_ID)) {
            getSavedUser();
            return true;
        }else
            return false;
    }

    public void authenticated(User currentUser){
        this.currentUser = currentUser;
        saveUser();
        setResponseObject(ResponseResource.ResourceStatus.AUTHENTICATED,null);
    }

    public void logout(){
        removeUser();
        setResponseObject(ResponseResource.ResourceStatus.LOGOUT,null);
    }

    public void loading() {
        setResponseObject(ResponseResource.ResourceStatus.LOADING, null);
    }

    public void hideLoading(){
        setResponseObject(ResponseResource.ResourceStatus.HIDE_LOADING,null);
    }

    public void success(String message) {
        setResponseObject(ResponseResource.ResourceStatus.SUCCESS, message);
    }

    public void failed(String message) {
        setResponseObject(ResponseResource.ResourceStatus.FAILED, message);
    }

    public void noConnection() {
        setResponseObject(ResponseResource.ResourceStatus.NO_CONNECTION, null);
    }

    private void setResponseObject(ResponseResource.ResourceStatus status, String message) {
        responseResource.setStatus(status);
        responseResource.setMessage(message);
        responseManager.setValue(responseResource);
        responseManager.setValue(null);

    }

    public LiveData<ResponseResource> getResponseManager() { return responseManager; }
    public User getCurrentUser() { return currentUser; }

    private void saveUser(){
        sharedPreferences.edit().putInt(Constants.USER_ID,currentUser.getId()).apply();

        Gson gson = new Gson();
        String user = gson.toJson(currentUser);
        sharedPreferences.edit().putString(Constants.USER,user).apply();
    }

    private void getSavedUser(){
        Gson gson = new Gson();
        String user = sharedPreferences.getString(Constants.USER,"");
        currentUser = gson.fromJson(user,User.class);
    }

    private void removeUser(){
        sharedPreferences.edit().remove(Constants.USER_ID).apply();
        sharedPreferences.edit().remove(Constants.USER).apply();
        currentUser = null;
    }
}
