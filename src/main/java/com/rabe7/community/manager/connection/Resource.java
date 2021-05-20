package com.rabe7.community.manager.connection;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    private ResourceStatus resourceStatus;

    @NonNull
    @SerializedName("status")
    private String status;

    @Nullable
    @SerializedName("data")
    private T data;

    @Nullable
    @SerializedName("message")
    private String message;


    private Resource() {}

    public static class ResourceBuilder<T> {
        private Resource<T> resource;

        public ResourceBuilder() {
            resource = new Resource<>(); }

        private ResourceBuilder<T> withMessage(@NonNull String msg) {
            resource.message = msg;
            return this;
        }

        private ResourceBuilder<T> holdingData(@NonNull T data) {
            resource.data = data;
            return this;
        }

        private ResourceBuilder<T> withStatus(@NonNull ResourceStatus status) {
            resource.resourceStatus = status;
            return this;
        }

        public Resource<T> build() {
            return resource;
        }

    }

    public static <T> Resource<T> success(@NonNull String msg,@NonNull T data) {
        return new ResourceBuilder<T>()
                .withStatus(ResourceStatus.SUCCESS)
                .withMessage(msg)
                .holdingData(data)
                .build();
    }

    public static <T> Resource<T> failed(@NonNull String msg) {
        return new ResourceBuilder<T>()
                .withStatus(ResourceStatus.FAILED)
                .withMessage(msg)
                .build();
    }

    public static <T> Resource<T> noConnection(@NonNull String msg) {
        return new ResourceBuilder<T>()
                .withStatus(ResourceStatus.NO_CONNECTION)
                .withMessage(msg)
                .build();
    }

    public static <T> Resource<T> loading() {
        return new ResourceBuilder<T>()
                .withStatus(ResourceStatus.LOADING)
                .build();
    }

    public static <T> Resource<T> logout() {
        return new ResourceBuilder<T>()
                .withStatus(ResourceStatus.NOT_AUTHENTICATED)
                .build();
    }


    @NonNull
    public ResourceStatus getResourceStatus() {
        return resourceStatus;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public enum ResourceStatus {SUCCESS, FAILED , NO_CONNECTION, LOADING, NOT_AUTHENTICATED}

}
