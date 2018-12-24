package wangfeixixi.cip.widget.carview.anim;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import wangfeixixi.cip.R;

public class BlinkAnim {
    public static void blink(View view) {
        Drawable background = view.getBackground();
        if (background instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) background;
            if (animationDrawable.isRunning()) {
                animationDrawable.stop();
            } else {
                AnimationDrawable recordingTransition;
                view.setBackgroundResource(R.drawable.state);
                recordingTransition = (AnimationDrawable) view.getBackground();
                recordingTransition.start();
            }
        } else {
            AnimationDrawable recordingTransition;
            view.setBackgroundResource(R.drawable.state);
            recordingTransition = (AnimationDrawable) view.getBackground();
            recordingTransition.start();
        }
    }

}
