package wangfeixixi.com.thirdview.background;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import wangfeixixi.com.thirdview.body.BodyBean;
import wangfeixixi.com.thirdview.car.CarView;
import wangfeixixi.com.thirdview.car.CarViewBean;
import wangfeixixi.com.thirdview.car.CarViewUtils;
import wangfeixixi.com.utils.LogUtils;
import wangfeixixi.com.utils.ScreenUtils;


public class BackgourndAllView extends RelativeLayout {

    private Context mCtx;
    private int carX;
    private int carY;

    public BackgourndAllView(Context context) {
        this(context, null);
    }

    public BackgourndAllView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackgourndAllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mCtx = context;
        init(context);
    }

    private void init(Context context) {
        addCar(context);

    }

    private void addCar(Context context) {
        View car = new CarView(context);
        int carWidth = 80;
        int carHeight = 160;
        int width = ScreenUtils.getScreenWidth(context);
        int height = ScreenUtils.getScreenHeight(context);

        int x = width - carWidth;
        int y = height - carHeight;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(carWidth, carHeight);
        params.setMargins(x / 2, y * 2 / 3, 0, 0);
        car.setLayoutParams(params);
        addView(car);
        carX = (int) car.getX();
        carY = (int) car.getY();
    }

    public void addBody(CarViewBean carViewBean) {
        View view = new CarView(mCtx);
        int carWidth = carViewBean.carWidth;
        int carHeight = carViewBean.carLength;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(carWidth, carHeight);
        params.setMargins(carViewBean.carX, carViewBean.carY, 0, 0);
        view.setLayoutParams(params);
        addView(view);

        String log = "carWidth:" + carWidth + "  carHeight:" + carHeight + "  carViewBean.carX:" + carViewBean.carX + "  carViewBean.carY" + carViewBean.carY;
        LogUtils.d(log);
    }

    public void updateBodys(BodyBean[] beans) {
//        removeAllViews();
//        addCar(getContext());
        removeViews(1, getChildCount()-1);

        for (int i = 0; i < beans.length; i++) {
            CarViewBean carViewBean = CarViewUtils.getCarView(mCtx, carX, carY, beans[i]);
            addBody(carViewBean);

        }
    }

    public void start() {

    }

    public void stop() {
//        removeAllViews();
        removeViews(1, 1);

    }
}
