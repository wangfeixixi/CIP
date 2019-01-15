package wangfeixixi.cip.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {
    public static int getScreenHeight() {
        return UIUtils.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return UIUtils.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, UIUtils.getResources().getDisplayMetrics());
    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }
}
