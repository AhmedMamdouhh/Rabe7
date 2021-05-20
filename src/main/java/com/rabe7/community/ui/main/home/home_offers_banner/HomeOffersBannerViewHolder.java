package com.rabe7.community.ui.main.home.home_offers_banner;


import android.view.View;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.databinding.ItemHomeOffersBannerBinding;
import com.rabe7.community.model.entity.home.Offers;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class HomeOffersBannerViewHolder extends RecyclerView.ViewHolder {

    private ItemHomeOffersBannerBinding binding;
    private RequestManager requestManager;

    public HomeOffersBannerViewHolder(@NonNull View itemView, RequestManager requestManager) {
        super(itemView);
        this.requestManager = requestManager;
        bind();
    }

    public void setOfferObject(Offers currentOffer) {
        binding.setOfferObject(currentOffer);

        requestManager
                .load(currentOffer.getOfferImage())
                .into(binding.ivHomeOffersImage);
    }

    void bind() {
        if (binding == null) {
            binding = DataBindingUtil.bind(itemView);
        }
    }

    void unbind() {
        if (binding != null) {
            binding.unbind();
        }
    }


}
