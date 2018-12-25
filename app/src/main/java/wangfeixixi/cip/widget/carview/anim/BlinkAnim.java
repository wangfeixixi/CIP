package wangfeixixi.cip.widget.carview.anim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;

import wangfeixixi.cip.R;

public class BlinkAnim {
    public static void blink(View view, int alarmOritation) {
        Drawable background = view.getBackground();
        if (background instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) background;
            if (animationDrawable.isRunning()) {
                animationDrawable.stop();
            } else {
                AnimationDrawable recordingTransition;
                setBackground(alarmOritation, view);
                recordingTransition = (AnimationDrawable) view.getBackground();
                recordingTransition.start();
            }
        } else {
            AnimationDrawable recordingTransition;
            setBackground(alarmOritation, view);
            recordingTransition = (AnimationDrawable) view.getBackground();
            recordingTransition.start();
        }
    }

    private static void setBackground(int alarmOritation, View view) {
        Animation animation = view.getAnimation();
        if (animation != null) animation.cancel();
        switch (alarmOritation) {
            case 1:
                view.setBackgroundResource(R.drawable.state);
                break;
            case 2:
                view.setBackgroundResource(R.drawable.state);
                view.animate().rotation(180);
                break;
            case 3:
                view.setBackgroundResource(R.drawable.state);
                view.animate().rotation(90);
                break;
            case 4:
                view.setBackgroundResource(R.drawable.state);
                view.animate().rotation(-90);
                break;
        }
    }
}
