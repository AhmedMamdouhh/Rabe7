package com.rabe7.community.manager.utilities;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;

import com.rabe7.community.R;


public class AnimationHelper {

    public static Animation shakeAnimation;
    public static ValueAnimator expandValueAnimator;
    public static AnimatorSet stretchAnimatorSet;

    //**********************************************************//
    public static void shake(View TheView , Context context) {

        shakeAnimation = AnimationUtils.loadAnimation(context, R.anim.bounce);
        double animationDuration = 0.80 * 1000;

        shakeAnimation.setDuration((long) animationDuration);
        shakeAnimation.setInterpolator(interpolator);

        TheView.startAnimation(shakeAnimation);
    }
    static Interpolator interpolator = new Interpolator() {
        @Override
        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time / 0.17) * Math.cos(40 * time) + 1);
        }
    };

    public static void expandAndCollapse(final View view, int duration, int targetSize, final String widthOrHeight) {

        int Previous = 200;

        if (widthOrHeight.equals("W"))
            Previous = view.getWidth();

        else if (widthOrHeight.equals("H"))
            Previous = view.getHeight();


        expandValueAnimator = ValueAnimator.ofInt(Previous, targetSize);
        expandValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                if (widthOrHeight.equals("W"))
                    view.getLayoutParams().width = (int) animation.getAnimatedValue();

                else if (widthOrHeight.equals("H"))
                    view.getLayoutParams().height = (int) animation.getAnimatedValue();

                view.requestLayout();
            }


        });

        expandValueAnimator.setDuration(duration);
        expandValueAnimator.start();
    }
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static void scaleView(View view, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(500);
        view.startAnimation(anim);
    }
    public static void stretch(View view , int duration , String widthOrHeight){
        stretchAnimatorSet = new AnimatorSet();
        stretchAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(view, "scale"+widthOrHeight, 1.0f, 0f).setDuration(duration)
        );
        stretchAnimatorSet.start();
    }


    public static AlphaAnimation stretch2(View view , int duration , String widthOrHeight){
        stretchAnimatorSet = new AnimatorSet();
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(500);
        view.setAlpha(1f);
        view.startAnimation(animation1);



        return animation1;

    }

}
