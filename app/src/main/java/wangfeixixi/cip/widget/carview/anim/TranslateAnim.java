package wangfeixixi.cip.widget.carview.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import wangfeixixi.com.base.ScreenUtils;

public class TranslateAnim {
    public static TranslateAnimation getAnimation(float time) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0f, ScreenUtils.getScreenHeight() / 3 * 2);
        if (time < 100)
            translateAnimation.setDuration(100);
        else
            translateAnimation.setDuration((long) time);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setInterpolator(new LinearInterpolator());
        return translateAnimation;
    }

    public static void switchSpeedAnim(View view, float speed) {
        float duration = 0;
        if (speed > 110) {
            duration = 30;
        } else if (speed == 0) {
            view.clearAnimation();
            return;
        } else {
            duration = 140 - speed;
        }
        duration *= 30;
        view.clearAnimation();
        view.startAnimation(getAnimation(duration));
    }

}
