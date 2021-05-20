package com.rabe7.community.ui.main.creadit.coupons;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rabe7.community.R;
import com.rabe7.community.model.entity.credit.Coupons;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.ui.main.creadit.vouchers.VouchersViewHolder;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsViewHolder> {

    private ArrayList<Coupons> couponsList ;

    public CouponsAdapter() {
        couponsList= new ArrayList<>();
    }

    @NonNull
    @Override
    public CouponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CouponsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon, new FrameLayout(parent.getContext()), false));
    }

    @Override
    public void onBindViewHolder(@NonNull CouponsViewHolder holder, int position) {
        holder.setCouponBinding(couponsList.get(position));
    }

    public void updateOffers(ArrayList<Coupons> couponsList){
        this.couponsList = couponsList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return couponsList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull CouponsViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CouponsViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }
}
