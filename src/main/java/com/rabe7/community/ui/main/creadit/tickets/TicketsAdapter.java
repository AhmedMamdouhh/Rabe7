package com.rabe7.community.ui.main.creadit.tickets;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.RequestManager;
import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.model.entity.credit.Tickets;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

public class TicketsAdapter extends RecyclerView.Adapter<TicketsViewHolder> {

    private ArrayList<Tickets> ticketsList ;
    private RequestManager requestManager;
    private TicketsViewModel ticketsViewModel;

    @Inject
    public TicketsAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
        ticketsList= new ArrayList<>();
    }

    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tickets, new FrameLayout(parent.getContext()), false)
                ,requestManager , ticketsViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketsViewHolder holder, int position) {
        holder.setTicketsBinding(ticketsList.get(position));
    }

    public void updateTickets(ArrayList<Tickets> ticketsList){
        this.ticketsList = ticketsList;
        notifyDataSetChanged();
    }

    public void setTicketsViewModel(TicketsViewModel ticketsViewModel){
        this.ticketsViewModel = ticketsViewModel;
    }


    @Override
    public int getItemCount() {
        return ticketsList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull TicketsViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull TicketsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }
}
