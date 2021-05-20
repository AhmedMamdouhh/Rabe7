package com.rabe7.community.ui.main.creadit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.RequestManager;
import com.mlsdev.animatedrv.AnimatedRecyclerView;
import com.rabe7.community.R;
import com.rabe7.community.databinding.FragmentCreditBinding;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.model.entity.credit.Coupons;
import com.rabe7.community.model.entity.credit.Vouchers;
import com.rabe7.community.ui.main.MainActivity;
import com.rabe7.community.ui.main.creadit.coupons.CouponsAdapter;
import com.rabe7.community.ui.main.creadit.vouchers.VouchersAdapter;
import com.rabe7.community.view_model.MainViewModelFactory;

import org.aviran.cookiebar2.CookieBar;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class CreditFragment extends Fragment {


    private FragmentCreditBinding creditBinding;
    private CreditViewModel creditViewModel;

    @Inject
    MainViewModelFactory providerFactory;
    @Inject
    RequestManager requestManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)getActivity())
                .mainComponent
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        creditBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_credit, container, false);
        return creditBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        creditViewModel = new ViewModelProvider(this, providerFactory).get(CreditViewModel.class);
        creditBinding.setLifecycleOwner(this);
        creditBinding.setCreditClickListener(creditViewModel);



        observeTicketsClick();
        observeCouponClick();

        animateScreen();
    }

    private void animateScreen() {
        Animation fromRight = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_right);
        fromRight.setDuration(Constants.DELAY_MEDIUM);
        creditBinding.clTicketsContainer.setAnimation(fromRight);

        Animation fromLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.enter_from_left);
        fromLeft.setDuration(Constants.DELAY_MEDIUM);
        creditBinding.rlCouponsContainer.setAnimation(fromLeft);

        Animation fade = AnimationUtils.loadAnimation(getActivity(), R.anim.item_animation_from_scale);
        fade.setDuration(Constants.DELAY_MEDIUM);
        creditBinding.clVouchersContainer.setAnimation(fade);

    }

    private void observeCouponClick() {
        creditViewModel.getCouponClick().removeObservers(getViewLifecycleOwner());
        creditViewModel.getCouponClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                couponsList();
            }
        });
    }



    private void observeTicketsClick() {
        creditViewModel.getTicketsClick().removeObservers(getViewLifecycleOwner());
        creditViewModel.getTicketsClick().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ticketsList();
            }
        });
    }


    public void ticketsList(){
        CookieBar.build(getActivity())
                .setCustomView(R.layout.layout_tickets)
                .setCustomViewInitializer(new CookieBar.CustomViewInitializer() {
                    @Override
                    public void initView(View view) {

                        AnimatedRecyclerView recyclerView = view.findViewById(R.id.rv_tickets);

//                        TicketsAdapter adapter = new TicketsAdapter();
//                        adapter.updateOffers(createTicketsList());
//                        recyclerView.setAdapter(adapter);

                    }
                })

                .setEnableAutoDismiss(false) // Cookie will stay on display until manually dismissed
                .setCookiePosition(CookieBar.BOTTOM)
                .show();

    }
    public void couponsList(){
        CookieBar.build(getActivity())
                .setCustomView(R.layout.layout_coupons)
                .setCustomViewInitializer(new CookieBar.CustomViewInitializer() {
                    @Override
                    public void initView(View view) {

                        AnimatedRecyclerView recyclerView = view.findViewById(R.id.rv_coupon);

                        CouponsAdapter adapter = new CouponsAdapter();
                        adapter.updateOffers(createCouponsList());
                        recyclerView.setAdapter(adapter);

                    }
                })

                .setEnableAutoDismiss(false) // Cookie will stay on display until manually dismissed
                .setCookiePosition(CookieBar.BOTTOM)
                .show();

    }



    private ArrayList<Coupons> createCouponsList(){
        ArrayList<Coupons> coupons = new ArrayList<>();

        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));
        coupons.add(new Coupons("Lorem Ipsum Lorem Ipsum is simply dummy text of the printing and typesetting"
                ,50));

        return coupons;

    }


}
