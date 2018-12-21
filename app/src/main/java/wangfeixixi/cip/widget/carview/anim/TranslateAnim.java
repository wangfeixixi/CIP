package wangfeixixi.cip.widget.carview.anim;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import wangfeixixi.com.base.ScreenUtils;

public class TranslateAnim {
    public static TranslateAnimation getAnimation(long time) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0f, 0f, 0f, ScreenUtils.getScreenHeight());
        if (time < 100)
            translateAnimation.setDuration(100);
        else
            translateAnimation.setDuration(time);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        return translateAnimation;
    }

    public static void switchSpeedAnim(View view, int speed) {
        long duration = 0;
        if (speed > 110) {
            duration = 30;
        } else if (speed == 0) {
            view.clearAnimation();
            return;
        } else {
            duration = 140 - speed;
        }
        duration *= 10;
        view.clearAnimation();
        view.startAnimation(getAnimation(duration));
    }

}
