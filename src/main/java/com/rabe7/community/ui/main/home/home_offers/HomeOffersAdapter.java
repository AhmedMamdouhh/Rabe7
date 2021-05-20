package com.rabe7.community.ui.main.home.home_offers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.R;
import com.rabe7.community.model.entity.home.Offers;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

public class HomeOffersAdapter extends RecyclerView.Adapter<HomeOffersViewHolder>  {

    private ArrayList<Offers> offersList ;
    private RequestManager requestManager;

    @Inject
    public HomeOffersAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
        offersList = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeOffersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeOffersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_offers, new FrameLayout(parent.getContext()), false),requestManager);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeOffersViewHolder holder, int position) {
        holder.setOfferObject(offersList.get(position));
    }

    public void updateOffers(ArrayList<Offers> offersList){
        this.offersList = offersList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return offersList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull HomeOffersViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull HomeOffersViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

}
