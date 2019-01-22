package wangfeixixi.com.avpv.handle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import wangfeixixi.com.avpv.R;

public class PointView extends View {


    private Bitmap mWhitePiece;
    private int mPanelWidth;
    private float mLineHeight;
    private float PieceSize = 3 * 1.0f / 4;
    private int MAX_LINE = 12;
    private int width;
    private float piontX, piontY;


    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mWhitePiece = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取屏幕的宽带和高度
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        width = Math.min(widthSize, heightSize);
        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = heightSize;
        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
            width = widthSize;
        }
        setMeasuredDimension(width, width);//设置我们View的高度和宽度
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;

        int PieceWidth = (int) (mLineHeight * PieceSize);
        mWhitePiece = Bitmap.createScaledBitmap(mWhitePiece, PieceWidth, PieceWidth, false);
    }

    private float a, b;

    public void setX(float a) {
        this.a = a;
        invalidate();//有偏移量传进来后就进行重绘
    }

    public void setY(float b) {
        this.b = b;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        piontX = (float) (width / 2 + a * 2.5);//将偏移量乘以2.5倍后加上原点的X坐标值得到当前点的X坐标指
        piontY = (float) (width / 2 + b * 2.5);//将偏移量乘以2.5倍后加上原点的Y坐标值得到当前点的Y坐标指
        Log.e("PoinetView", piontX + "--" + piontY);
        canvas.drawBitmap(mWhitePiece, piontX, piontY, null);//根据计算后的坐标进行绘制图形
        check();
    }

    //检查坐标是否在范围内，再范围内就提示
    private void check() {
        if ((800 < piontY & piontY <= 900) && (700 < piontX & piontX < 800)) {
            Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
        }
    }
}