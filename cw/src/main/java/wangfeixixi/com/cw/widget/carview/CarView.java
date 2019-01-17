package wangfeixixi.com.cw.widget.carview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wangfeixixi.com.cw.widget.carview.draw.DrawCar;
import wangfeixixi.com.cw.widget.carview.draw.DrawLine;

public class CarView extends View {
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标

    public CarView(Context context) {
        this(context, null, 0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
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
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.GRAY);//背景
        DrawLine.getInstance().drawLine(canvas, mCarX, mCarY);
        if (mBeans != null)
            for (int i = 0; i < mBeans.length; i++) {
                if (i == 0) {
                    DrawCar.getInstance().drawHvCar(canvas, mBeans[i], mCarX, mCarY);
                } else {
                    DrawCar.getInstance().drawRvCar(canvas, mBeans[i], mCarX, mCarY);
                }
            }

    }

    private CarBean[] mBeans = null;

    public void updateBodys(CarBean[] beans) {
        mBeans = beans;
        invalidate();
    }

    public void switchSpeed(float speed) {
        DrawLine.getInstance().switchSpeed(speed);
    }
}
