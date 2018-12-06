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
    public float[][] lines;//车道线
    private float[] line_local;

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

        //初始化道路线
        lines = new float[][]{
                new float[]{80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{70, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{60, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{50, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{40, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{30, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{20, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{10, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
                new float[]{0, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40}};
        //初始化道路线
//        lines = new float[][]{
//                new float[]{80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{70, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{60, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{50, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{40, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{30, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{20, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{10, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//                new float[]{0, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40}};

        line_local = new float[]{
                mCarX - 100, 0,
                mCarX - 100, mCarY / 2 * 3,
                mCarX + 100, 0,
                mCarX + 100, mCarY / 2 * 3};
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);//背景
        if (mBeans != null)
            for (int i = 0; i < mBeans.length; i++)
                drawCar(canvas, mBeans[i]);
        drawLine(canvas);
    }

    public int linesPaintID = 7;

    private void drawLine(Canvas canvas) {
        mPaintLine.setPathEffect(new DashPathEffect(lines[linesPaintID--], 0));
        if (linesPaintID == -1) {
            linesPaintID = 7;
        }
        canvas.drawLines(line_local, mPaintLine);
    }


    private Bitmap rotateBitmap;

    private void drawCar(Canvas canvas, CarBean bean) {
        rotateBitmap = BitmapUtils.rotateBitmap(bean.fcwAlarm == 0 ? car_bitmap : car_alert_bitmap, bean.rotate);
        canvas.drawBitmap(rotateBitmap, CarUtils.x2XView(mCarX, bean), CarUtils.y2YView(mCarY, bean), mPaintCar);
        canvas.save();
    }

    private CarBean[] mBeans = null;

    public void updateBodys(CarBean[] beans) {
        mBeans = beans;
        invalidate();
    }

    public void setSpeed(int speed) {

    }
}