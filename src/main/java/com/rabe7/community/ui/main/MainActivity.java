package com.rabe7.community.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.rabe7.BaseActivity;
import com.rabe7.BaseApplication;
import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.databinding.ActivityMainBinding;
import com.rabe7.community.databinding.NavigationHeaderBinding;
import com.rabe7.community.di.ui.main.MainComponent;
import com.rabe7.community.manager.utilities.Constants;
import com.rabe7.community.ui.auth.AuthActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    ResponseManager responseManager;
    public MainComponent mainComponent;

    public ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);


        initialize();
    }

    @Override
    protected void inject() {
        mainComponent = ((BaseApplication) getApplication())
                .mainComponent();
        mainComponent.inject(this);
    }

    private void initialize() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, activityMainBinding.drawerLayout);
        NavigationUI.setupWithNavController(activityMainBinding.navView, navController);
        activityMainBinding.navView.setNavigationItemSelectedListener(this);
        activityMainBinding.navView.setItemIconTintList(null);

        NavigationHeaderBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.navigation_header, activityMainBinding.navView, true);
        navHeaderMainBinding.setUserObject(responseManager.getCurrentUser());

        if (responseManager.getCurrentUser() != null) {
            if (responseManager.getCurrentUser().getUserTypeId() != null)
                if (responseManager.getCurrentUser().getUserTypeId().equals(Constants.TYPE_USER)) {
                    Menu nav_Menu = activityMainBinding.navView.getMenu();
                    nav_Menu.findItem(R.id.nav_trader_manager).setVisible(false);
                }else {
                    Menu nav_Menu = activityMainBinding.navView.getMenu();
                    nav_Menu.findItem(R.id.nav_our_business).setVisible(false);
                }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_home: {

                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.main, true)
                        .build();

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.homeScreen,
                        null,
                        navOptions
                );

                break;
            }

            case R.id.nav_trader_manager: {

                if (isValidDestination(R.id.traderManagerFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.traderManagerFragment);
                }

                break;
            }

            case R.id.nav_our_business: {

                if (isValidDestination(R.id.nav_our_business)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.registerTraderFragment);
                }

                break;
            }

            case R.id.nav_profile: {

                if (isValidDestination(R.id.profileScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileScreen);
                }

                break;
            }

            case R.id.nav_logout: {
                responseManager.logout();

                startActivity(new Intent(MainActivity.this, AuthActivity.class));
                finishAffinity();
                break;
            }
        }

        menuItem.setChecked(true);
        activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isValidDestination(int destination) {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), activityMainBinding.drawerLayout);
    }
}
