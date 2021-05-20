package com.rabe7.community.ui.main.home.home_offers_banner;

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

public class HomeOffersBannerAdapter extends RecyclerView.Adapter<HomeOffersBannerViewHolder>  {

    private ArrayList<Offers> offersList ;
    private RequestManager requestManager;

    @Inject
    public HomeOffersBannerAdapter(RequestManager requestManager) {
        this.requestManager = requestManager;
        offersList = new ArrayList<>();
    }

    @NonNull
    @Override
    public HomeOffersBannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeOffersBannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_offers_banner, new FrameLayout(parent.getContext()), false),requestManager);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeOffersBannerViewHolder holder, int position) {
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
    public void onViewAttachedToWindow(@NonNull HomeOffersBannerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull HomeOffersBannerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

}
