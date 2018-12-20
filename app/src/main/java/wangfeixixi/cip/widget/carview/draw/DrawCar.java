package wangfeixixi.cip.widget.carview.draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarUtils;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;
import wangfeixixi.com.base.UIUtils;

public class DrawCar {
    private DrawCar() {

        //车图标,放大车辆图标
        car_bitmap = BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car), CarUtils.carBitmapScale);
        car_alert_bitmap = BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car1), CarUtils.carBitmapScale);

        //自身车
        mPaintCar = new Paint();
        mPaintCar.setAntiAlias(true);
        mPaintCar.setStyle(Paint.Style.FILL);
    }

    private static class Inner {
        private static DrawCar instance = new DrawCar();
    }

    public static DrawCar getInstance() {
        return Inner.instance;
    }


    private Bitmap car_bitmap;//原生车图
    private Bitmap car_alert_bitmap;//原生车图
    private Paint mPaintCar;

    private Bitmap rotateBitmap;

    public void drawHvCar(Canvas canvas, CarBean bean, float mCarX, float mCarY) {
        rotateBitmap = BitmapUtils.rotateBitmap(car_bitmap, 0f);
        canvas.drawBitmap(rotateBitmap, CarUtils.x2XView(mCarX, bean), CarUtils.y2YView(mCarY, bean), mPaintCar);
        canvas.save();
    }

    public void drawRvCar(Canvas canvas, CarBean bean, float mCarX, float mCarY) {
        rotateBitmap = BitmapUtils.rotateBitmap(bean.fcwAlarm == 0 ? car_bitmap : car_alert_bitmap, 0f);
        canvas.drawBitmap(rotateBitmap, CarUtils.x2XView(mCarX, bean), CarUtils.y2YView(mCarY, bean), mPaintCar);
        canvas.save();
    }

}
