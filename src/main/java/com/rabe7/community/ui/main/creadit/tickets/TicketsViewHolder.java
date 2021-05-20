package com.rabe7.community.ui.main.creadit.tickets;

import android.view.View;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.databinding.ItemTicketsBinding;
import com.rabe7.community.model.entity.credit.Tickets;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

public class TicketsViewHolder extends RecyclerView.ViewHolder {

    private ItemTicketsBinding ticketsBinding;
    private RequestManager requestManager;
    private TicketsViewModel ticketsViewModel;

    public TicketsViewHolder(@NonNull View itemView, RequestManager requestManager, TicketsViewModel ticketsViewModel) {
        super(itemView);
        this.requestManager = requestManager;
        this.ticketsViewModel = ticketsViewModel;
        bind();
    }



    void bind() {
        if (ticketsBinding == null) {
            ticketsBinding = DataBindingUtil.bind(itemView);
            ticketsBinding.setTicketsListener(ticketsViewModel);
        }
    }

    void unbind() {
        if (ticketsBinding != null) {
            ticketsBinding.unbind();
        }
    }

    public void setTicketsBinding(Tickets currentTicket) {
        ticketsBinding.setTicketsObject(currentTicket);

        requestManager
                .load(currentTicket.getTicketLogo())
                .into(ticketsBinding.ivTicketsLogo);
    }


}
