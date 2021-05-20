package com.rabe7.community.di.ui.main;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.rabe7.community.ui.main.creadit.tickets.TicketsAdapter;
import com.rabe7.community.ui.main.creadit.vouchers.VouchersAdapter;
import com.rabe7.community.ui.main.home.home_offers_banner.HomeOffersBannerAdapter;
import com.rabe7.community.ui.main.home.home_offers.HomeOffersAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    //Home:
    @Provides
    static HomeOffersAdapter provideHomeOffersAdapter(RequestManager requestManager){return new HomeOffersAdapter(requestManager);}
    @Provides
    static StaggeredGridLayoutManager provideStaggeredGridLayoutManager(){return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);}
    @Provides
    static HomeOffersBannerAdapter provideHomeOffersBannerAdapter(RequestManager requestManager){return new HomeOffersBannerAdapter(requestManager );}


    //Tickets:
    @Provides
    static TicketsAdapter provideTicketsAdapter(RequestManager requestManager){return new TicketsAdapter(requestManager);}

    //Vouchers
    static VouchersAdapter provideVouchersAdapter(){return new VouchersAdapter();}


}
