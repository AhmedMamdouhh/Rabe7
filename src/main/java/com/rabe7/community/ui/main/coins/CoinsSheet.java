package com.rabe7.community.ui.main.coins;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.databinding.DialogCoinsBinding;
import com.rabe7.community.ui.auth.forgot_password.ForgotPasswordSheet;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.creadit.tickets.TicketsSheet;
import com.rabe7.community.ui.main.creadit.vouchers.VouchersSheet;
import com.rabe7.community.view_model.MainViewModelFactory;

import javax.inject.Inject;


public class CoinsSheet extends BottomSheetDialogFragment {


    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    ResponseManager responseManager;


    private DialogCoinsBinding coinsBinding;
    private CoinsViewModel coinsViewModel;
    private BottomSheetBehavior bottomSheetBehavior;

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
        coinsBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_coins, container, false);
        return coinsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        coinsViewModel = new ViewModelProvider(this, providerFactory).get(CoinsViewModel.class);
        coinsBinding.setLifecycleOwner(this);
        coinsBinding.setUserObject(responseManager.getCurrentUser());
        coinsBinding.setCoinsClickListeners(coinsViewModel);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observeTicketsClick();
        observeVouchersClick();
        observeCloseClick();
    }


    private void observeCloseClick() {
        coinsViewModel.getCloseClick().removeObservers(getViewLifecycleOwner());
        coinsViewModel.getCloseClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                try {
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } catch (NullPointerException ignored) {
                }
            }
        });
    }


    private void observeVouchersClick() {
        coinsViewModel.getVouchersClick().removeObservers(getViewLifecycleOwner());
        coinsViewModel.getVouchersClick().observe(getViewLifecycleOwner(), new Observer<View>() {
            @Override
            public void onChanged(View view) {
                new VouchersSheet().show(getActivity().getSupportFragmentManager(), "vouchers");
            }
        });
    }

    private void observeTicketsClick() {
        coinsViewModel.getTicketsClick().removeObservers(getViewLifecycleOwner());
        coinsViewModel.getTicketsClick().observe(getViewLifecycleOwner(), new Observer<View>() {
            @Override
            public void onChanged(View view) {
                new TicketsSheet().show(getActivity().getSupportFragmentManager(), "tickets");
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        View bottomSheet = null;

        if (dialog != null) {
            bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        View view = getView();
        View finalBottomSheet = bottomSheet;
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
            ((View) finalBottomSheet.getParent()).setBackgroundColor(Color.TRANSPARENT);
        });
    }


}


