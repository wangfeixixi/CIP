package wangfeixixi.cip;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        float[] pts = {50, 100, 100, 200, 200, 300, 300, 400};
        paint.setColor(Color.RED);
        canvas.drawLines(pts, paint);

        canvas.drawColor(Color.WHITE);

        paint.setColor(Color.BLUE);
        canvas.drawLines(pts, 1, 4, paint);//去掉第一个数50，取之后的4个数即100,100,200,200


        float[] rects = {};
        canvas.drawRect(50, 50, 100, 100, paint);
    }
}
