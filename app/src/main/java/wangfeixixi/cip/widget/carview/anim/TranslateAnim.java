package wangfeixixi.cip.widget.carview.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import wangfeixixi.cip.widget.carview.CarUtils;

public class TranslateAnim {
    public static TranslateAnimation getAnimation(float time) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0f, CarUtils.getCarViewHeight());
        if (time < 100)
            translateAnimation.setDuration(100);
        else
            translateAnimation.setDuration((long) time);
//        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setInterpolator(new LinearInterpolator());
        return translateAnimation;
    }

    public static int repeate = 1;

    public static boolean isStartAnim = false;

    public static void switchSpeedAnim(final View left, final View right, final float duration) {
        repeate = 1;
        if (isStartAnim) return;

        right.clearAnimation();
        left.clearAnimation();
        left.startAnimation(getAnimation(duration));
        right.startAnimation(getAnimation(duration));

        TranslateAnimation animation = getAnimation(duration);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                repeate--;
                isStartAnim = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isStartAnim = false;
                if (repeate != 0) {
                    left.startAnimation(getAnimation(duration));
                    right.startAnimation(getAnimation(duration));
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
