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

import java.util.ArrayList;
import java.util.List;

import wangfeixixi.cip.R;
import wangfeixixi.cip.widget.carview.utils.BitmapUtils;

public class CarView extends View {

    private Paint mPaintCar;
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标
    private Bitmap car_bitmap;//原生车图
    private Bitmap car_alert_bitmap;//原生车图
    private Paint mPaintLine;//车道线
    public List<float[]> lines;//车道线

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

        lines = new ArrayList<>();
        lines.add(new float[]{0, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{10, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{20, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{30, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{40, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{50, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{60, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{70, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
        lines.add(new float[]{80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40});
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //自身车坐标,todo是以自身中心为坐标的
        mCarX = width / 2;
        mCarY = height / 3 * 2;
        //重心坐标
//        rect = new Rect(mCarX - carWidth / 2, mCarY - carLength / 2, mCarX + carWidth / 2, mCarY + carLength / 2);
        //车图标,放大车辆图标
        car_bitmap = BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.car), CarUtils.carBitmapScale);
        car_alert_bitmap = BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.car_alert), CarUtils.carBitmapScale);
//        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);//背景
        if (mBeans != null)
            for (int i = 0; i < mBeans.length; i++)
                drawCar(canvas, mBeans[i]);
        drawLine(canvas);
    }

    public int linesPaintID = 0;

    private void drawLine(Canvas canvas) {
        mPaintLine.setPathEffect(new DashPathEffect(lines.get(linesPaintID++), 0));
        if (linesPaintID == 7) {
            linesPaintID = 0;
        }

        canvas.drawLines(new float[]{
                mCarX - 100, 0,
                mCarX - 100, mCarY / 2 * 3,
                mCarX + 100, 0,
                mCarX + 100, mCarY / 2 * 3}, mPaintLine);
    }


    private void drawCar(Canvas canvas, CarBean bean) {
//        matrix.reset();
//        matrix.setRotate(bean.rotate);
//        carBitmap = Bitmap.createBitmap(car_bitmap, 0, 0, car_bitmap.getWidth() / 3, car_bitmap.getHeight() / 3, matrix, true);
//        carBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);
        //自身车
//        carRectSrc = new Rect(0, 0, carBitmap.getWidth(), carBitmap.getHeight());
//        carRectSrc = new Rect(0, 0, carBitmap.getWidth(), carBitmap.getHeight());

//        canvas.drawBitmap(carBitmap, carRectSrc, rect, mPaintCar);
//        canvas.drawBitmap(carBitmap, mCarX, mCarY, mPaintCar);

        Bitmap rotateBitmap = BitmapUtils.rotateBitmap(bean.fcwAlarm == 0 ? car_bitmap : car_alert_bitmap, bean.rotate);
//        int width = car_bitmap.getWidth();
//        int height = car_bitmap.getHeight();
//        canvas.drawBitmap(rotateBitmap, mCarX, mCarY, mPaintCar);
        canvas.drawBitmap(rotateBitmap, CarUtils.x2XView(mCarX, bean), CarUtils.y2YView(mCarY, bean), mPaintCar);


        //测试版垂直
//        canvas.drawBitmap(carBitmap, carRectSrc, CarUtils.shelf2Screen(mCarX, mCarY, bean), mPaintCar);

        canvas.save();
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
}
