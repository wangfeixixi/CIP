package wangfeixixi.com.lib.base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public abstract class BaseView extends View implements IBaseView {
    private Paint mPaintBody;
    private Paint mPaintCar;
    private Context mCtx;
    private int mCarX;//车辆x坐标
    private int mCarY;//车辆y坐标
    private int mCarWidth = 3;//车宽
    private int mCarLength = 5;//车长

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPant(context);
    }

    protected void initPant(Context context) {
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

}
