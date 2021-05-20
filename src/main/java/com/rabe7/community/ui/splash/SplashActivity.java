package com.rabe7.community.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.rabe7.BaseApplication;
import com.rabe7.ResponseManager;
import com.rabe7.community.R;
import com.rabe7.community.ui.auth.AuthActivity;
import com.rabe7.community.ui.main.MainActivity;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Inject
    ResponseManager responseManager;

    private static int SPLASH_TIME_OUT = 5000;
    //Hooks
    View first, second, third, fourth, fifth, sixth;
    ImageView a;
    TextView slogan;
    //Animations
    Animation topAnimantion, bottomAnimation, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication)getApplication())
                .splashComponent()
                .inject(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //Hooks
        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        fourth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.sixth_line);
        a = findViewById(R.id.a);
        slogan = findViewById(R.id.tagLine);

        //Animation Calls
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.translate_from_top);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_from_bottom);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_from_left);

        //-----------Setting Animations to the elements of Splash
        first.setAnimation(topAnimantion);
        second.setAnimation(topAnimantion);
        third.setAnimation(topAnimantion);
        fourth.setAnimation(topAnimantion);
        fifth.setAnimation(topAnimantion);
        sixth.setAnimation(topAnimantion);
        a.setAnimation(middleAnimation);
        slogan.setAnimation(bottomAnimation);

        middleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (responseManager.isAuthenticated()) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(intent);
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}



