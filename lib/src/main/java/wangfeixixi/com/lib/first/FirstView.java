package wangfeixixi.com.lib.first;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wangfeixixi.com.lib.R;
import wangfeixixi.com.lib.body.CarBean;
import wangfeixixi.com.lib.car.CarViewBean;
import wangfeixixi.com.lib.car.ConvertUtils;

public class FirstView extends View {

    private Paint mPaintBody;
    private Paint mPaintCar;
    private Context mCtx;
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标
    private int mCarWidth = 3;//车宽
    private int mCarLength = 5;//车长

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
        mCtx = context;
        //阻挡物
        mPaintBody = new Paint();
        mPaintBody.setAntiAlias(true);
        mPaintBody.setStyle(Paint.Style.FILL);
//        mPaintBody.setStrokeWidth(5);
        mPaintBody.setColor(Color.RED);

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

        //自身车坐标
        mCarX = width / 2;
        mCarY = height / 3 * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap carBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);

        canvas.drawColor(Color.BLACK);


        //自身车
        Rect rectSrc = new Rect(0, 0, carBitmap.getWidth(), carBitmap.getHeight());
        Rect rect = new Rect(mCarX - mCarWidth / 2 * ConvertUtils.scale, (mCarY - mCarLength / 2 * ConvertUtils.scale),
                (mCarX + mCarWidth / 2 * ConvertUtils.scale), (mCarY + mCarLength / 2 * ConvertUtils.scale));

        canvas.drawBitmap(carBitmap, rectSrc, rect, mPaintBody);
        canvas.save();

//        Bitmap alertBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.alert, null);
//        Rect rectSrcAlert = new Rect(0, 0, alertBitmap.getWidth(), alertBitmap.getHeight());

//        canvas.drawBitmap(alertBitmap, rectSrcAlert, rect, mPaintBody);
//        canvas.save();


        //周围车
        if (mBeans != null) {
            for (int i = 0; i < mBeans.length; i++) {
                CarViewBean carViewBean = ConvertUtils.getCarView(mCtx, mCarX, mCarY, mBeans[i]);

                rect = new Rect(carViewBean.carX - carViewBean.carWidth, carViewBean.carY - carViewBean.carLength
                        , carViewBean.carX, carViewBean.carY);

                canvas.drawBitmap(carBitmap, rectSrc, rect, mPaintBody);
                canvas.save();
            }
        }


    }

    private CarBean[] mBeans = null;

    public void updateBodys(CarBean[] beans) {
        mBeans = beans;
        invalidate();
    }

    public void switchPoint() {

    }

    public void stop() {

    }


    public void drawCar(CarBean carBean) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);

    }
}
