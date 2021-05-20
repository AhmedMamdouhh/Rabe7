package com.rabe7.community.ui.auth.forgot_password;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.rabe7.community.databinding.DialogForgotPasswordBinding;
import com.rabe7.community.ui.auth.AuthActivity;
import com.rabe7.community.ui.main.coins.CoinsViewModel;
import com.rabe7.community.view_model.AuthViewModelFactory;

import javax.inject.Inject;


public class ForgotPasswordSheet extends BottomSheetDialogFragment {


    @Inject
    AuthViewModelFactory providerFactory;
    @Inject
    ResponseManager responseManager;


    private DialogForgotPasswordBinding forgotPasswordBinding;
    private ForgotPasswordViewModel forgotPasswordViewModel;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((AuthActivity)getActivity())
                .authComponent
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        forgotPasswordBinding = DataBindingUtil.inflate(inflater,R.layout.dialog_forgot_password,container,false);
        return forgotPasswordBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        forgotPasswordViewModel = new ViewModelProvider(this, providerFactory).get(ForgotPasswordViewModel.class);
        forgotPasswordBinding.setLifecycleOwner(this);
        forgotPasswordBinding.setForgotPasswordListener(forgotPasswordViewModel);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        observeSuccess();
        observeError();
        observeCloseClick();
    }

    private void observeError() {
        forgotPasswordViewModel.observeError().removeObservers(getViewLifecycleOwner());
        forgotPasswordViewModel.observeError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void observeSuccess() {
        forgotPasswordViewModel.observeSuccess().removeObservers(getViewLifecycleOwner());
        forgotPasswordViewModel.observeSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                try {
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }catch (NullPointerException ignored){}
            }
        });

    }

    private void observeCloseClick() {
        forgotPasswordViewModel.getCloseClick().removeObservers(getViewLifecycleOwner());
        forgotPasswordViewModel.getCloseClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                try {
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }catch (NullPointerException ignored){}
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


