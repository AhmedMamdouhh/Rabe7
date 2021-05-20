package com.rabe7.community.ui.main.creadit.vouchers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rabe7.community.R;
import com.rabe7.community.databinding.DialogVouchersListBinding;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.view_model.MainViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;


public class VouchersSheet extends BottomSheetDialogFragment {


    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    VouchersAdapter vouchersAdapter;


    private DialogVouchersListBinding vouchersBinding;
    private VouchersViewModel vouchersViewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity) getActivity())
                .mainComponent
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vouchersBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_vouchers_list, container, false);
        return vouchersBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        vouchersViewModel = new ViewModelProvider(this, providerFactory).get(VouchersViewModel.class);
        vouchersBinding.setLifecycleOwner(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        vouchersViewModel.callVouchersRepository();
        observeVouchers();
        observeBuyVouchers();
        observeVouchersError();
        observeBuyVouchersSuccess();
    }

    private void observeBuyVouchersSuccess() {
        vouchersViewModel.getBuyVouchersSuccess().removeObservers(getViewLifecycleOwner());
        vouchersViewModel.getBuyVouchersSuccess().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                VouchersSheet.this.dismiss();
            }
        });
    }

    private void observeVouchersError() {
        vouchersViewModel.getVouchersError().removeObservers(getViewLifecycleOwner());
        vouchersViewModel.getVouchersError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                VouchersSheet.this.dismiss();
            }
        });
    }

    private void observeVouchers() {
        vouchersViewModel.getVouchers().removeObservers(getViewLifecycleOwner());
        vouchersViewModel.getVouchers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Vouchers>>() {
            @Override
            public void onChanged(ArrayList<Vouchers> vouchers) {
                vouchersAdapter.updateVouchers(vouchers);
                vouchersAdapter.setVouchersViewModel(vouchersViewModel);
                vouchersBinding.rvVouchers.setAdapter(vouchersAdapter);

            }
        });
    }

    private void observeBuyVouchers() {
        if (vouchersBinding.getLifecycleOwner() != null) {
            vouchersViewModel.getBuyVouchers().removeObservers(this);
            vouchersViewModel.getBuyVouchers().observe(vouchersBinding.getLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    VouchersSheet.this.dismiss();
                }
            });

        }
    }


}


