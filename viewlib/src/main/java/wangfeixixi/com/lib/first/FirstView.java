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
import wangfeixixi.com.lib.body.CarShelfBean;
import wangfeixixi.com.lib.car.ConvertUtils;

public class FirstView extends View {

    private Paint mPaintCar;
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标
    private int mCarWidth = 3;//车宽
    private int mCarLength = 5;//车长
    private Bitmap carBitmap;
    private Rect carRectSrc;
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

        //车图标
        carBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car, null);
        //自身车
        carRectSrc = new Rect(0, 0, carBitmap.getWidth(), carBitmap.getHeight());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        //自身车坐标,todo是以自身中心为坐标的
        mCarX = width / 2;
        mCarY = height / 3 * 2;
        rect = new Rect(mCarX - mCarWidth / 2 * ConvertUtils.scale, (mCarY - mCarLength / 2 * ConvertUtils.scale),
                (mCarX + mCarWidth / 2 * ConvertUtils.scale), (mCarY + mCarLength / 2 * ConvertUtils.scale));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);

        canvas.drawBitmap(carBitmap, carRectSrc, rect, mPaintCar);
        canvas.save();

        //周围车
        if (mBeans != null) {
            for (int i = 0; i < mBeans.length; i++) {
                canvas.drawBitmap(carBitmap, carRectSrc, ConvertUtils.shelf2Screen(mCarX, mCarY, mBeans[i]), mPaintCar);
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
