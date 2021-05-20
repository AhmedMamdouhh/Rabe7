package com.rabe7.community.di.ui.main;

import com.rabe7.community.ui.auth.trader_register.RegisterTraderFragment;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.coins.CoinsSheet;
import com.rabe7.community.ui.main.creadit.CreditFragment;
import com.rabe7.community.ui.main.creadit.tickets.TicketsSheet;
import com.rabe7.community.ui.main.creadit.vouchers.VouchersSheet;
import com.rabe7.community.ui.main.home.HomeFragment;
import com.rabe7.community.ui.main.points.PointsSheet;
import com.rabe7.community.ui.main.profile.ProfileFragment;
import com.rabe7.community.ui.main.referrer_id.ReferrerSheet;
import com.rabe7.community.ui.main.trader_manager.TraderManagerFragment;

import dagger.Subcomponent;

@MainScope
@Subcomponent(
        modules = {
                MainModule.class,
                MainViewModelsModule.class
        }
)
public interface MainComponent {

    @Subcomponent.Factory
    interface Factory{
        MainComponent create();
    }


    void inject(MainActivity mainActivity);
    void inject(HomeFragment homeFragment);
    void inject(ProfileFragment profileFragment);
    void inject(CreditFragment creditFragment);
    void inject(RegisterTraderFragment registerTraderFragment);
    void inject(TraderManagerFragment traderManagerFragment);
    void inject(CoinsSheet coinsSheet);
    void inject(PointsSheet pointsSheet);
    void inject(TicketsSheet ticketsSheet);
    void inject(VouchersSheet vouchersSheet);
    void inject(ReferrerSheet referrerSheet);
}
