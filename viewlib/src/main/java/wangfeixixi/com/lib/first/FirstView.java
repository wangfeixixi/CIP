package wangfeixixi.com.lib.first;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wangfeixixi.com.lib.R;
import wangfeixixi.com.lib.body.CarShelfBean;
import wangfeixixi.com.lib.car.CarUtils;

public class FirstView extends View {

    private Paint mPaintCar;
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标

    private Rect rect;

    public FirstView(Context context) {
        this(context, null, 0);
    }

    public FirstView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //自身车
        mPaintCar = new Paint();
        mPaintCar.setAntiAlias(true);
        mPaintCar.setStyle(Paint.Style.FILL);
//        mPaintCar.setStrokeWidth(5);
        mPaintCar.setColor(Color.BLUE);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //自身车坐标,todo是以自身中心为坐标的
        mCarX = width / 2;
        mCarY = height / 3 * 2;

        int carWidth = CarUtils.carWidth * CarUtils.scale;
        int carLength = CarUtils.carLength * CarUtils.scale;

        //重心坐标
        rect = new Rect(mCarX - carWidth / 2, mCarY - carLength / 2, mCarX + carWidth / 2, mCarY + carLength / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //车图标
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(10.2f);
        Bitmap carBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        carBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);
        //自身车
        Rect carRectSrc = new Rect(0, 0, carBitmap.getWidth(), carBitmap.getHeight());


        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(carBitmap, carRectSrc, rect, mPaintCar);
        canvas.save();

        //周围车
        if (mBeans != null) {
            for (int i = 0; i < mBeans.length; i++) {

                matrix.reset();
                matrix.setRotate(mBeans[i].rotate);
                carBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//        carBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);
                //自身车
                carRectSrc = new Rect(0, 0, carBitmap.getWidth(), carBitmap.getHeight());

                canvas.drawBitmap(carBitmap, carRectSrc, CarUtils.shelf2Screen(mCarX, mCarY, mBeans[i]), mPaintCar);
                canvas.save();
            }
        }
    }

    private CarShelfBean[] mBeans = null;

    public void updateBodys(CarShelfBean[] beans) {
        mBeans = beans;
        invalidate();
    }

    public void switchPoint() {

    }

    public void stop() {

    }
}
