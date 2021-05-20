package com.rabe7.community.manager.connection;

import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.model.entity.home.Offers;
import com.rabe7.community.model.request.login.LoginRequest;
import com.rabe7.community.model.request.register.trader.RegisterTraderRequest;
import com.rabe7.community.model.request.register.user.RegisterRequest;
import com.rabe7.community.model.request.trader.TraderExchangeRequest;

import java.util.ArrayList;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    //Auth
    @POST("auth/login")
    Flowable<Resource<User>> userLogin(
            @Body LoginRequest userData
    );

    @POST("auth/register")
    Flowable<Resource<User>> userRegister(
            @Body RegisterRequest registerData
    );

    @POST("auth/register-as-trader")
    Flowable<Resource<User>> traderRegister(
            @Body RegisterTraderRequest registerData
    );

    @POST("auth/forgot-password")
    @FormUrlEncoded
    Flowable<Resource<Void>> userForgotPassword(
            @Field("email") String userEmail
    );


    //Home offers
    @GET("entities/offer-banner")
    Flowable<Resource<ArrayList<Offers>>> homeBannerOffers();

    @GET("entities")
    Flowable<Resource<ArrayList<Offers>>> homeOffers();

    //Credit
    @GET("tickets")
    Flowable<Resource<ArrayList<Tickets>>> getTickets();

    @POST("tickets/redeem")
    @FormUrlEncoded
    Flowable<Resource<Void>> buyTicket(
            @Field("ticket_id") Integer ticketId
    );

    @GET("vouchers")
    Flowable<Resource<ArrayList<Vouchers>>> getVouchers();

    @POST("voucher/redeem")
    @FormUrlEncoded
    Flowable<Resource<Void>> buyVouchers(
            @Field("voucher_id") Integer ticketId
    );

    @FormUrlEncoded
    @POST("auth/referral-code")
    Flowable<Resource<Void>> setReferralCode(
            @Field("referral_code") String referralCode
    );


    //points:
    @POST("points/redeem")
    @FormUrlEncoded
    Flowable<Resource<Void>> redeemPoints(
            @Field("amount") String points
    );

    //trader:
    @POST("trader/exchange-cash")
    Flowable<Resource<Void>> traderExchange(
            @Body TraderExchangeRequest traderExchangeRequest
    );


}
