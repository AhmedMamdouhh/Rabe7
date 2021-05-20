package com.rabe7.community.ui.main.points;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rabe7.BaseActivity;
import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.databinding.DialogPointsBinding;
import com.rabe7.community.model.entity.auth.User;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.coins.CoinsViewModel;
import com.rabe7.community.ui.main.profile.ProfileFragment;
import com.rabe7.community.view_model.MainViewModelFactory;

import org.aviran.cookiebar2.CookieBar;

import javax.inject.Inject;


public class PointsSheet extends BottomSheetDialogFragment {


    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    ResponseManager responseManager;


    private DialogPointsBinding pointsBinding;
    private PointsViewModel pointsViewModel;
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
        pointsBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_points, container, false);
        return pointsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        pointsViewModel = new ViewModelProvider(this, providerFactory).get(PointsViewModel.class);
        pointsBinding.setLifecycleOwner(this);
        pointsBinding.setUserObject(responseManager.getCurrentUser());
        pointsBinding.setPointsListener(pointsViewModel);

        observeError();
        observeRedeemClick();
        observeCloseClick();
    }

    private void observeCloseClick() {
        pointsViewModel.getCloseClick().removeObservers(getViewLifecycleOwner());
        pointsViewModel.getCloseClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                try {
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }catch (NullPointerException ignored){}
            }
        });
    }

    private void observeRedeemClick() {
        pointsViewModel.getRedeemClick().removeObservers(getViewLifecycleOwner());
        pointsViewModel.getRedeemClick().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String points) {
                try {
                    bottomSheetBehavior.setHideable(true);
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    showConfirmRedeemingSheet(points,getActivity());
                }catch (NullPointerException ignored){}
            }
        });
    }



        private void observeError () {
            pointsViewModel.getError().removeObservers(getViewLifecycleOwner());
            pointsViewModel.getError().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String msg) {
                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }


        private void showConfirmRedeemingSheet (String points , Activity activity){
            CookieBar.build(getActivity())
                    .setCustomView(R.layout.layout_confirm_points)
                    .setCustomViewInitializer(new CookieBar.CustomViewInitializer() {
                        @Override
                        public void initView(View view) {

                            TextView messageView = view.findViewById(R.id.tv_message);
                            String pointsMessage = String.format(getActivity().getString(R.string.points_confirm), points);
                            messageView.setText(pointsMessage);

                            TextView confirm = view.findViewById(R.id.btn_action);
                            TextView cancel = view.findViewById(R.id.btn_no);

                            cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CookieBar.dismiss(activity);
                                }
                            });

                            confirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    pointsViewModel.getRepositoryData();
                                    responseManager.getCurrentUser().setPointBalance(String.valueOf(Double.parseDouble(responseManager.getCurrentUser().getPointBalance())- Double.parseDouble(points)));
                                    CookieBar.dismiss(activity);
                                }
                            });

                        }
                    })

                    .setEnableAutoDismiss(false)
                    .setCookiePosition(CookieBar.BOTTOM)
                    .show();
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


