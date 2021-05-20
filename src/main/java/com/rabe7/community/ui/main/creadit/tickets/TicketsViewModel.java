package com.rabe7.community.ui.main.creadit.tickets;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rabe7.ResponseManager;
import com.rabe7.community.di.ui.main.MainScope;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.repository.credit.TicketsRepository;
import com.rabe7.community.manager.connection.Resource;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

@MainScope
public class TicketsViewModel extends ViewModel {

    //inject
    private final TicketsRepository ticketsRepository;
    private ResponseManager responseManager;


    private MutableLiveData<ArrayList<Tickets>> observeTickets;
    private MutableLiveData<Boolean> observeBuyTickets;
    private MutableLiveData<String> observeBuyTicketsSuccess;
    private MutableLiveData<String> observeErrors;

    @Inject
    public TicketsViewModel(TicketsRepository ticketsRepository, ResponseManager responseManager) {
        this.ticketsRepository = ticketsRepository;
        this.responseManager = responseManager;

        observeTickets = new MutableLiveData<>();
        observeBuyTickets = new MutableLiveData<>();
        observeBuyTicketsSuccess = new MutableLiveData<>();
        observeErrors = new MutableLiveData<>();
    }

    //click:
    public void onTicketsClicked(Integer ticketId) { getBuyTicketRepository(ticketId); }

    private void getBuyTicketRepository(Integer ticketId) {
        responseManager.loading();


        ticketsRepository.buyTicket(ticketId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<Void>>() {
                    @Override
                    public void onNext(Resource<Void> ticketsResponse) {
                        if (ticketsResponse != null) {
                            responseManager.hideLoading();

                            if (ticketsResponse.getStatus().equals(Constants.SUCCESS))
                                observeBuyTickets.setValue(true);


                            else
                                observeErrors.setValue(ticketsResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        responseManager.noConnection();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    private void getTicketsRepository() {

        responseManager.loading();


        ticketsRepository.getTicketsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<Resource<ArrayList<Tickets>>>() {
                    @Override
                    public void onNext(Resource<ArrayList<Tickets>> ticketsResponse) {
                        if (ticketsResponse != null) {
                            responseManager.hideLoading();

                            if (ticketsResponse.getStatus().equals(Constants.SUCCESS))
                                observeTickets.setValue(ticketsResponse.getData());
                            else
                                observeErrors.setValue(ticketsResponse.getMessage());

                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        responseManager.noConnection();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void callTicketsRepository(){getTicketsRepository();}
    public LiveData<ArrayList<Tickets>> getTickets() {
        if(observeTickets !=null)
            observeTickets = new MutableLiveData<>();
        return observeTickets;
    }
    public LiveData<Boolean> getBuyTickets() {
        if(observeBuyTickets !=null)
            observeBuyTickets = new MutableLiveData<>();
        return observeBuyTickets;
    }
    public LiveData<String> getTicketsError() {
        if(observeErrors != null)
            observeErrors = new MutableLiveData<>();
        return observeErrors;
    }
    public LiveData<String> getBuyTicketsSuccess() {
        if(observeBuyTicketsSuccess != null)
            observeBuyTicketsSuccess = new MutableLiveData<>();
        return observeBuyTicketsSuccess;
    }

}
