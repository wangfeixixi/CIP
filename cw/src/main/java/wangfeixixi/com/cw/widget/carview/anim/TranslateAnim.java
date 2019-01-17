package wangfeixixi.com.cw.widget.carview.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import wangfeixixi.com.cw.widget.carview.CarUtils;

public class TranslateAnim {
    public static TranslateAnimation getAnimation(float time) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0f, CarUtils.getInstance().carViewHeight);
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

    public static void startTranslateAnim(final View left, final View right, final float duration) {
        repeate = 1;
        if (isStartAnim) return;

        right.clearAnimation();
        left.clearAnimation();

        TranslateAnimation animation = getAnimation(duration);

        left.startAnimation(animation);
        right.startAnimation(animation);
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
                    left.startAnimation(animation);
                    right.startAnimation(animation);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
