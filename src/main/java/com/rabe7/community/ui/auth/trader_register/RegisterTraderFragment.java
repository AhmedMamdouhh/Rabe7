package com.rabe7.community.ui.auth.trader_register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.databinding.FragmentHomeBinding;
import com.rabe7.community.databinding.FragmentRegisterTraderBinding;
import com.rabe7.community.model.request.register.trader.RegisterTraderRequest;
import com.rabe7.community.ui.auth.AuthActivity;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.home.HomeViewModel;
import com.rabe7.community.view_model.MainViewModelFactory;

import javax.inject.Inject;

public class RegisterTraderFragment extends Fragment {

    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    RegisterTraderRequest traderRequest;
    @Inject
    ResponseManager responseManager;


    private FragmentRegisterTraderBinding binding;
    private RegisterTraderViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() != null)
            ((MainActivity) getActivity())
                    .mainComponent
                    .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_trader, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, providerFactory).get(RegisterTraderViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSignUpObject(traderRequest);
        binding.setSignUpClickListener(viewModel);

        observeSuccess();
    }

    private void observeSuccess() {
        viewModel.observeSuccess().removeObservers(getViewLifecycleOwner());
        viewModel.observeSuccess().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                responseManager.logout();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), AuthActivity.class));
                getActivity().finish();
            }
        });
    }

}
