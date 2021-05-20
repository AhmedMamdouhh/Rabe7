package com.rabe7.community.ui.main.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.R;
import com.rabe7.community.databinding.FragmentHomeBinding;

import com.rabe7.community.model.entity.home.Offers;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.home.home_offers_banner.HomeOffersBannerAdapter;
import com.rabe7.community.ui.main.home.home_offers.HomeOffersAdapter;
import com.rabe7.community.view_model.MainViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HomeFragment extends Fragment {

    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    HomeOffersAdapter homeOffersAdapter;
    @Inject
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Inject
    HomeOffersBannerAdapter homeOffersBannerAdapter;
    @Inject
    RequestManager requestManager;


    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);

        viewModel.getRepositoryData();
        observeOffersSuccess();
        observeOffersBannerSuccess();
    }

    private void observeOffersBannerSuccess() {
        viewModel.observeOffersBannerSuccess().removeObservers(getViewLifecycleOwner());
        viewModel.observeOffersBannerSuccess().observe(getViewLifecycleOwner(), new Observer<ArrayList<Offers>>() {
            @Override
            public void onChanged(ArrayList<Offers> offers) {
                homeOffersBannerAdapter.updateOffers(offers);
                binding.rvHomeOffersBanner.setAdapter(homeOffersBannerAdapter);

            }
        });
    }

    private void observeOffersSuccess() {
        viewModel.observeOffersSuccess().removeObservers(getViewLifecycleOwner());
        viewModel.observeOffersSuccess().observe(getViewLifecycleOwner(), new Observer<ArrayList<Offers>>() {
            @Override
            public void onChanged(ArrayList<Offers> offers) {
                binding.rvHomeOffers.setLayoutManager(staggeredGridLayoutManager);
                binding.rvHomeOffers.setAdapter(homeOffersAdapter);
                homeOffersAdapter.updateOffers(offers);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.rvHomeOffers.setLayoutManager(null);
        binding.rvHomeOffers.setAdapter(null);
    }
}
