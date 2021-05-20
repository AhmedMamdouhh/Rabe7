package com.rabe7.community.ui.splash;

import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rabe7.community.R;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModel;

public class SplashViewModel extends ViewModel {

    private static final String TAG = "324234324";

    @BindingAdapter("blink")
    public static void blinkAnimation(View view , boolean isOpen) {
        if (isOpen) {

        }

    }
}
