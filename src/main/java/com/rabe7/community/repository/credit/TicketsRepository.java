package com.rabe7.community.repository.credit;

import com.rabe7.community.manager.connection.Api;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.manager.connection.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class TicketsRepository {

    private static final String TAG = "LoginRepository";
    private final Api api;

    @Inject
    public TicketsRepository(Api api){
        this.api = api;
    }

    public Flowable<Resource<ArrayList<Tickets>>> getTicketsData(){
        return api.getTickets()
                .subscribeOn(Schedulers.io());
    }

    public Flowable<Resource<Void>> buyTicket(Integer ticketId){
        return api.buyTicket(ticketId)
                .subscribeOn(Schedulers.io());
    }
}
