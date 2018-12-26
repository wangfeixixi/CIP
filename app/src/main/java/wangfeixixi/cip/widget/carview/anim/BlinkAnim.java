package wangfeixixi.cip.widget.carview.anim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import wangfeixixi.cip.R;

public class BlinkAnim {
    public static void blink(View view, boolean isBlink) {
        Drawable background = view.getBackground();
        if (background instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) background;
            //已经初始化
            if (isBlink && animationDrawable.isRunning() || !isBlink && !animationDrawable.isRunning()) {
                return;
            } else if (isBlink && !animationDrawable.isRunning()) {
                AnimationDrawable recordingTransition;
                view.setBackgroundResource(R.drawable.state);
                recordingTransition = (AnimationDrawable) view.getBackground();
                recordingTransition.start();
            } else {
                animationDrawable.stop();
            }
        } else {
            if (!isBlink) return;
            AnimationDrawable recordingTransition;
            view.setBackgroundResource(R.drawable.state);
            recordingTransition = (AnimationDrawable) view.getBackground();
            recordingTransition.start();
        }
    }

//    private static void setBackground(int alarmOritation, View view) {
//        Animation animation = view.getAnimation();
//        if (animation != null) animation.cancel();
//        switch (alarmOritation) {
//            case 1:
//                view.setBackgroundResource(R.drawable.state);
//                break;
//            case 2:
//                view.setBackgroundResource(R.drawable.state);
//                view.animate().rotation(180);
//                break;
//            case 3:
//                view.setBackgroundResource(R.drawable.state);
//                view.animate().rotation(90);
//                break;
//            case 4:
//                view.setBackgroundResource(R.drawable.state);
//                view.animate().rotation(-90);
//                break;
//        }
//    }
}
