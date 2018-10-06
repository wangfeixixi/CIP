package wangfeixixi.com.thirdview.background;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import wangfeixixi.com.thirdview.body.BodyBean;
import wangfeixixi.com.thirdview.body.BodyView;
import wangfeixixi.com.thirdview.car.CarView;
import wangfeixixi.com.thirdview.car.CarViewBean;
import wangfeixixi.com.thirdview.car.ConvertUtils;
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
        addCar(context, 3, 4);

    }

    /**
     * @param context
     * @param carWidth  车宽，单位米
     * @param carLength 车长，单位米
     */
    public void addCar(Context context, int carWidth, int carLength) {
        View car = new CarView(context);

        int carScreenWidth = carWidth * ConvertUtils.scale;
        int carScreenLength = carLength * ConvertUtils.scale;

        int x = ScreenUtils.getScreenWidth(context) - carScreenWidth;
        int y = ScreenUtils.getScreenHeight(context) - carScreenLength;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(carScreenWidth, carScreenLength);
        carX = x / 2;
        carY = y * 2 / 3;
        params.setMargins(carX, carY, 0, 0);
        car.setLayoutParams(params);
        addView(car);
    }

    public void addBody(CarViewBean carViewBean) {
        View view = new BodyView(mCtx);
        int carWidth = carViewBean.carWidth;
        int carHeight = carViewBean.carLength;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(carWidth, carHeight);
        params.setMargins(carViewBean.carX, carViewBean.carY, 0, 0);
        view.setLayoutParams(params);
        addView(view);

//        String log = "carWidth:" + carWidth + "  carHeight:" + carHeight + "  carViewBean.carX:" + carViewBean.carX + "  carViewBean.carY" + carViewBean.carY + "  X" + carX + " Y" + carY;
//        LogUtils.d(log);
    }

    /**
     * 更新物体
     *
     * @param beans
     */
    public void updateBodys(BodyBean[] beans) {
        removeViews(1, getChildCount() - 1);

        for (int i = 0; i < beans.length; i++) {
            CarViewBean carViewBean = ConvertUtils.getCarView(mCtx, carX, carY, beans[i]);
            addBody(carViewBean);

        }
    }

    /**
     * 停止更新
     */
    public void stop() {
        removeViews(1, getChildCount() - 1);

    }

    /**
     * 切换角度
     */
    public void switchPoint() {

    }
}
