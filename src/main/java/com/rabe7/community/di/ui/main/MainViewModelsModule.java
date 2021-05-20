package com.rabe7.community.di.ui.main;


import com.rabe7.community.di.ui.main.keys.MainViewModelKey;
import com.rabe7.community.ui.auth.trader_register.RegisterTraderViewModel;
import com.rabe7.community.ui.main.coins.CoinsViewModel;
import com.rabe7.community.ui.main.creadit.CreditViewModel;
import com.rabe7.community.ui.main.creadit.tickets.TicketsViewModel;
import com.rabe7.community.ui.main.creadit.vouchers.VouchersViewModel;
import com.rabe7.community.ui.main.home.HomeViewModel;
import com.rabe7.community.ui.main.points.PointsViewModel;
import com.rabe7.community.ui.main.profile.ProfileViewModel;
import com.rabe7.community.ui.main.referrer_id.ReferrerViewModel;
import com.rabe7.community.ui.main.trader_manager.TraderManagerViewModel;
import com.rabe7.community.view_model.MainViewModelFactory;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @MainScope
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(MainViewModelFactory factory);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(HomeViewModel.class)
    public abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(CreditViewModel.class)
    public abstract ViewModel bindCreditViewModel(CreditViewModel creditViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(TraderManagerViewModel.class)
    public abstract ViewModel bindTraderManagerViewModel(TraderManagerViewModel traderManagerViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(RegisterTraderViewModel.class)
    public abstract ViewModel bindRegisterTraderViewModel(RegisterTraderViewModel registerTraderViewModel);

    //credit :

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(CoinsViewModel.class)
    public abstract ViewModel bindCoinsViewModel(CoinsViewModel coinsViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(PointsViewModel.class)
    public abstract ViewModel bindPointsViewModel(PointsViewModel pointsViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(TicketsViewModel.class)
    public abstract ViewModel bindTicketsViewModel(TicketsViewModel ticketsViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(VouchersViewModel.class)
    public abstract ViewModel bindVouchersViewModel(VouchersViewModel vouchersViewModel);

    @MainScope
    @Binds
    @IntoMap
    @MainViewModelKey(ReferrerViewModel.class)
    public abstract ViewModel bindReferrerViewModel(ReferrerViewModel referrerViewModel);

}




