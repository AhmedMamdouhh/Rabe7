package com.rabe7.community.di.base;


import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.model.entity.auth.User;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {


    @Singleton
    @Provides
    static OkHttpClient.Builder provideOkHttpClientInstance(ResponseManager responseManager){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        String token = "";
                        if(responseManager.getCurrentUser() !=null){
                            token = responseManager.getCurrentUser().getToken();
                        }
                        Request request = chain.request();
                        Request.Builder newRequest = request.newBuilder()
                                .addHeader("Authorization","Bearer "+token);

                        return  chain.proceed(newRequest.build());
                    }
                })
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES);


    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient.Builder okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.img_placehoder)
                .error(R.drawable.img_placehoder);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static SharedPreferences provideSharedPreferences(Application application){
        return application.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    static Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }

    @Singleton
    @Provides
    static User currentUser(){return new User();}


}

