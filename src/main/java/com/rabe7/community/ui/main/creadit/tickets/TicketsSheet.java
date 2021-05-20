package com.rabe7.community.ui.main.creadit.tickets;

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
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rabe7.community.R;
import com.rabe7.community.databinding.DialogTicketsListBinding;
import com.rabe7.community.model.entity.credit.Tickets;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.view_model.MainViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;


public class TicketsSheet extends BottomSheetDialogFragment {


    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    TicketsAdapter ticketsAdapter;


    private DialogTicketsListBinding ticketsBinding;
    private TicketsViewModel ticketsViewModel;

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
        ticketsBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_tickets_list, container, false);
        return ticketsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ticketsViewModel = new ViewModelProvider(this, providerFactory).get(TicketsViewModel.class);
        ticketsBinding.setLifecycleOwner(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ticketsViewModel.callTicketsRepository();
        observeTickets();
        observeBuyTickets();
        observeTicketsError();
        observeBuyTicketsSuccess();
    }

    private void observeBuyTicketsSuccess() {
        ticketsViewModel.getBuyTicketsSuccess().removeObservers(getViewLifecycleOwner());
        ticketsViewModel.getBuyTicketsSuccess().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                TicketsSheet.this.dismiss();
            }
        });
    }

    private void observeTicketsError() {
        ticketsViewModel.getTicketsError().removeObservers(getViewLifecycleOwner());
        ticketsViewModel.getTicketsError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                TicketsSheet.this.dismiss();
            }
        });
    }

    private void observeTickets() {
        ticketsViewModel.getTickets().removeObservers(getViewLifecycleOwner());
        ticketsViewModel.getTickets().observe(getViewLifecycleOwner(), new Observer<ArrayList<Tickets>>() {
            @Override
            public void onChanged(ArrayList<Tickets> tickets) {
                ticketsAdapter.updateTickets(tickets);
                ticketsAdapter.setTicketsViewModel(ticketsViewModel);
                ticketsBinding.rvTickets.setAdapter(ticketsAdapter);

            }
        });
    }

    private void observeBuyTickets() {
        if (ticketsBinding.getLifecycleOwner() != null) {
            ticketsViewModel.getBuyTickets().removeObservers(this);
            ticketsViewModel.getBuyTickets().observe(ticketsBinding.getLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    TicketsSheet.this.dismiss();
                }
            });

        }
    }


}


