package com.rabe7.community.ui.main.creadit.coupons;

import android.view.View;

import com.rabe7.community.databinding.ItemCouponBinding;
import com.rabe7.community.databinding.ItemVoucherBinding;
import com.rabe7.community.model.entity.credit.Coupons;
import com.rabe7.community.model.entity.credit.Vouchers;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CouponsViewHolder extends RecyclerView.ViewHolder {

    private ItemCouponBinding couponBinding;

    public CouponsViewHolder(@NonNull View itemView) {
        super(itemView);
        bind();
    }

    void bind() {
        if (couponBinding == null) {
            couponBinding = DataBindingUtil.bind(itemView);
        }
    }

    void unbind() {
        if (couponBinding != null) {
            couponBinding.unbind();
        }
    }

    public void setCouponBinding(Coupons currentCoupon){
        couponBinding.setCouponObject(currentCoupon);
    }
}
