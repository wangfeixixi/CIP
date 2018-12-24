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
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setInterpolator(new LinearInterpolator());
        return translateAnimation;
    }

    public static void switchSpeedAnim(View view, float duration) {
        if (duration == 3000) {
            view.clearAnimation();
            return;
        }
        view.clearAnimation();
        view.startAnimation(getAnimation(duration));
    }

}
