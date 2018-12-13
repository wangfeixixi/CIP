package wangfeixixi.cip.widget.carview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;

public class CarView extends View {

    private Paint mPaintCar;
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标
    private Bitmap car_bitmap;//原生车图
    private Bitmap car_alert_bitmap;//原生车图
    private Paint mPaintLine;//车道线

    public CarView(Context context) {
        this(context, null, 0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        //自身车
        mPaintCar = new Paint();
        mPaintCar.setAntiAlias(true);
        mPaintCar.setStyle(Paint.Style.FILL);
        //车道线
        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaintLine.setColor(Color.WHITE);
        mPaintLine.setStrokeWidth(10);

        switchSpeed(100);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //自身车坐标,todo是以自身中心为坐标的
        mCarX = width / 2;//偏移优化
        mCarY = height / 3 * 2;
        //重心坐标
//        rect = new Rect(mCarX - carWidth / 2, mCarY - carLength / 2, mCarX + carWidth / 2, mCarY + carLength / 2);
        //车图标,放大车辆图标
        car_bitmap = BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.car), CarUtils.carBitmapScale);
        car_alert_bitmap = BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.car_alert), CarUtils.carBitmapScale);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.GRAY);//背景
        if (mBeans != null)
            for (int i = 0; i < mBeans.length; i++)
                drawCar(canvas, mBeans[i]);
        drawLine(canvas);
    }

    private Bitmap rotateBitmap;

    private void drawCar(Canvas canvas, CarBean bean) {
        rotateBitmap = BitmapUtils.rotateBitmap(bean.fcwAlarm == 0 ? car_bitmap : car_alert_bitmap, bean.heading);

        canvas.drawBitmap(rotateBitmap, CarUtils.x2XView(mCarX, bean), CarUtils.y2YView(mCarY, bean), mPaintCar);
        canvas.save();
    }

    private CarBean[] mBeans = null;

    public void updateBodys(CarBean[] beans) {
        mBeans = beans;
        invalidate();
    }

    //****************************************车道线
    private int linesPaintID = 0;

    private float[][] liness = null;

    private void drawLine(Canvas canvas) {
        mPaintLine.setPathEffect(new DashPathEffect(liness[linesPaintID], 0));
        if (linesPaintID++ == liness.length - 1) {
            linesPaintID = 0;
        }
        canvas.drawLines(new float[]{
                mCarX - 100, 0,
                mCarX - 100, mCarY / 2 * 3,
                mCarX + 140, 0,
                mCarX + 140, mCarY / 2 * 3}, mPaintLine);
    }

    public void switchSpeed(float speed) {
        liness = CarUtils.speed2Arrays((int) speed);
        linesPaintID = 0;
//        linesPaintID = liness.length - 1;
    }
}
