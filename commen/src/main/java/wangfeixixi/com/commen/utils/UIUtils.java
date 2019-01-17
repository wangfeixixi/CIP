package wangfeixixi.com.commen.utils;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

import wangfeixixi.com.commen.fram.BaseApp;

public class UIUtils {

    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static Application getContext() {
        return BaseApp.getInstance();
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, (ViewGroup) null);
    }

    public static int getDimens(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    public static String getString(int id) {
        return getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }


}
