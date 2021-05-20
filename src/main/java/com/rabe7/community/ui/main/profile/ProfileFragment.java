package com.rabe7.community.ui.main.profile;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.databinding.FragmentProfileBinding;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.view_model.MainViewModelFactory;

import javax.inject.Inject;


public class ProfileFragment extends Fragment {

    private static final String TAG = "1212123213";
    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    ResponseManager responseManager;

    public FragmentProfileBinding profileBinding;
    private ProfileViewModel profileViewModel;

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
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return profileBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this, providerFactory).get(ProfileViewModel.class);
        profileBinding.setLifecycleOwner(this);
        profileBinding.setUserObject(responseManager.getCurrentUser());
        profileBinding.setProfileClickListener(profileViewModel);


        hideScreen();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        animateProfile();
        observeCoinsClick();
        observePointsClick();
        observeReferrerClick();
    }

    private void animateProfile() {

        Animation fromLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_left);
        fromLeft.setDuration(Constants.DELAY_SMALL);
        profileBinding.vProfileDiagonalView.setAnimation(fromLeft);
        profileBinding.cvProfileReferral.setAnimation(fromLeft);

        Animation fromRight = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_right);
        fromRight.setDuration(Constants.DELAY_SMALL);
        profileBinding.cvProfileCash.setAnimation(fromRight);
        profileBinding.cvProfileImageShadow.setAnimation(fromRight);
        profileBinding.cvProfileImage.setAnimation(fromRight);

        Animation fade = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_from_scale);
        fade.setDuration(Constants.DELAY_SMALL);
        profileBinding.llUserDetailsContainer.setAnimation(fade);
        profileBinding.cvProfileCoins.setAnimation(fade);
        profileBinding.cvProfilePoints.setAnimation(fade);

    }

    private void observeReferrerClick() {
        profileViewModel.getObserveReferrerCodeClick().removeObservers(getViewLifecycleOwner());
        profileViewModel.getObserveReferrerCodeClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                Navigation.findNavController(profileBinding.getRoot()).navigate(R.id.action_profileScreen_to_referrerSheet);
            }
        });
    }
    private void observePointsClick() {
        profileViewModel.getObservePointsClick().removeObservers(getViewLifecycleOwner());
        profileViewModel.getObservePointsClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                Navigation.findNavController(profileBinding.getRoot()).navigate(R.id.action_profileScreen_to_pointsSheet);
            }
        });
    }
    private void observeCoinsClick() {
        profileViewModel.getObserveCoinsClick().removeObservers(getViewLifecycleOwner());
        profileViewModel.getObserveCoinsClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                Navigation.findNavController(profileBinding.getRoot()).navigate(R.id.action_profileScreen_to_coinsSheet);
            }
        });

    }

    private void hideScreen(){
        if (responseManager.getCurrentUser().getUserTypeId() != null)
            if (responseManager.getCurrentUser().getUserTypeId().equals(Constants.TYPE_TRADER))
                profileBinding.llProfileCashContainer.setVisibility(View.VISIBLE);
        if (responseManager.getCurrentUser().getRefererId() == 0)
            profileBinding.llProfileReferrerContainer.setVisibility(View.VISIBLE);
        else
            profileBinding.llProfileReferrerContainer.setVisibility(View.GONE);
    }

}
