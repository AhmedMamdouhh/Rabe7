package com.rabe7.community.ui.main.home.home_offers;


import android.view.View;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.databinding.ItemHomeOffersBinding;
import com.rabe7.community.model.entity.home.Offers;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

public class HomeOffersViewHolder extends RecyclerView.ViewHolder {

    private ItemHomeOffersBinding binding;
    private RequestManager requestManager;

    @Inject
    public HomeOffersViewHolder(@NonNull View itemView, RequestManager requestManager) {
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
