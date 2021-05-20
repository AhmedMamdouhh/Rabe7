package com.rabe7.community.ui.main.trader_manager;




import android.content.Context;
import android.os.Bundle;
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
import com.rabe7.community.databinding.FragmentTraderManagerBinding;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.model.request.trader.TraderExchangeRequest;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.profile.ProfileViewModel;
import com.rabe7.community.view_model.MainViewModelFactory;

import javax.inject.Inject;


public class TraderManagerFragment extends Fragment {

    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    ResponseManager responseManager;
    @Inject
    TraderExchangeRequest traderExchangeRequest;

    private FragmentTraderManagerBinding traderManagerBinding;
    private TraderManagerViewModel traderManagerViewModel;

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
        traderManagerBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_trader_manager, container, false);
        return traderManagerBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        traderManagerViewModel = new ViewModelProvider(this, providerFactory).get(TraderManagerViewModel.class);
        traderManagerBinding.setLifecycleOwner(this);
        traderManagerBinding.setTraderObject(traderExchangeRequest);
        traderManagerBinding.setTraderListener(traderManagerViewModel);



        animateTraderExchange();
    }

    private void animateTraderExchange() {

        Animation fromLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_left);
        fromLeft.setDuration(Constants.DELAY_SMALL);
        traderManagerBinding.cvTraderPrice.setAnimation(fromLeft);

        Animation fromRight = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_right);
        fromRight.setDuration(Constants.DELAY_SMALL);
        traderManagerBinding.cvTraderMemberCode.setAnimation(fromRight);

        Animation fade = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_from_scale);
        fade.setDuration(Constants.DELAY_SMALL);
        traderManagerBinding.cvTraderPaidAmount.setAnimation(fade);
        traderManagerBinding.cvTraderRedeem.setAnimation(fade);

    }


}
