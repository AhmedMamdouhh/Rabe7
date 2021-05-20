package com.rabe7.community.manager.connection;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ResponseResource {

    @NonNull
    private ResourceStatus status;

    @Nullable
    private String message;

    @Inject
    public ResponseResource(){}

    public void setStatus(@NonNull ResourceStatus status) {
        this.status = status;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @NonNull
    public ResourceStatus getStatus() {
        return status;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public enum ResourceStatus {SUCCESS, FAILED, NO_CONNECTION, LOADING, LOGOUT , AUTHENTICATED , HIDE_LOADING}

}
