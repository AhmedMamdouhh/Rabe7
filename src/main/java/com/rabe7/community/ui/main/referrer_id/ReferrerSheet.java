package com.rabe7.community.ui.main.referrer_id;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.rabe7.community.databinding.DialogReferrerBinding;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.points.PointsViewModel;
import com.rabe7.community.ui.main.profile.ProfileFragment;
import com.rabe7.community.view_model.MainViewModelFactory;

import org.aviran.cookiebar2.CookieBar;

import javax.inject.Inject;


public class ReferrerSheet extends BottomSheetDialogFragment {


    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    ResponseManager responseManager;


    private DialogReferrerBinding referrerBinding;
    private ReferrerViewModel referrerViewModel;
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
        referrerBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_referrer, container, false);
        return referrerBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        referrerViewModel = new ViewModelProvider(this, providerFactory).get(ReferrerViewModel.class);
        referrerBinding.setLifecycleOwner(this);
        referrerBinding.setUserObject(responseManager.getCurrentUser());
        referrerBinding.setReferrerListener(referrerViewModel);

        observeError();
        observeSuccess();
        observeCloseClick();
    }

    private void observeSuccess() {
        referrerViewModel.getSuccess().removeObservers(getViewLifecycleOwner());
        referrerViewModel.getSuccess().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                try {
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).popBackStack(R.id.profileScreen, true);
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.profileScreen);
                } catch (NullPointerException ignored) {
                }
            }
        });
    }

    private void observeCloseClick() {
        referrerViewModel.getCloseClick().removeObservers(getViewLifecycleOwner());
        referrerViewModel.getCloseClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
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


    private void observeError() {
        referrerViewModel.getError().removeObservers(getViewLifecycleOwner());
        referrerViewModel.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
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


