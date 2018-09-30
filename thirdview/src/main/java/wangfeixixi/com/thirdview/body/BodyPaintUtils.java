package wangfeixixi.com.thirdview.body;

import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 */
public class BodyPaintUtils {
    //圆环
    public static Paint getCircleBg() {
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#333333"));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        return paint;
    }
}
