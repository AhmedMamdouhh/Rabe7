package com.rabe7.community.ui.main.creadit.vouchers;

import android.view.View;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.databinding.ItemTicketsBinding;
import com.rabe7.community.databinding.ItemVoucherBinding;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.ui.main.creadit.tickets.TicketsViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class VouchersViewHolder extends RecyclerView.ViewHolder {

    private ItemVoucherBinding voucherBinding;
    private VouchersViewModel vouchersViewModel;

    public VouchersViewHolder(@NonNull View itemView, VouchersViewModel vouchersViewModel) {
        super(itemView);
        this.vouchersViewModel = vouchersViewModel;
        bind();
    }

    void bind() {
        if (voucherBinding == null) {
            voucherBinding = DataBindingUtil.bind(itemView);
            voucherBinding.setVoucherListener(vouchersViewModel);
        }
    }

    void unbind() {
        if (voucherBinding != null) {
            voucherBinding.unbind();
        }
    }

    public void setVoucherBinding(Vouchers currentVoucher){
        voucherBinding.setVoucherObject(currentVoucher);
    }
}
