package com.rabe7.community.ui.main.creadit.vouchers;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.R;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.ui.main.creadit.tickets.TicketsViewHolder;
import com.rabe7.community.ui.main.creadit.tickets.TicketsViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

public class VouchersAdapter extends RecyclerView.Adapter<VouchersViewHolder> {

    private ArrayList<Vouchers> vouchersList ;
    private VouchersViewModel vouchersViewModel;

    @Inject
    public VouchersAdapter() {
        vouchersList= new ArrayList<>();
    }

    @NonNull
    @Override
    public VouchersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VouchersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher, new FrameLayout(parent.getContext()), false)
        ,vouchersViewModel);
    }

    @Override
    public void onBindViewHolder(@NonNull VouchersViewHolder holder, int position) {
        holder.setVoucherBinding(vouchersList.get(position));
    }

    public void updateVouchers(ArrayList<Vouchers> vouchersList){
        this.vouchersList = vouchersList;
        notifyDataSetChanged();
    }

    public void setVouchersViewModel(VouchersViewModel vouchersViewModel){
        this.vouchersViewModel = vouchersViewModel;
    }


    @Override
    public int getItemCount() {
        return vouchersList.size();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VouchersViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VouchersViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }
}
