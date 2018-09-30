package wangfeixixi.com.utils;

import android.graphics.Paint;
import android.support.annotation.ColorInt;

/**
 * Created by xuany on 2018/5/3.
 */

public class PaintUtil {
    //圆环
    public static Paint getCircleBg(@ColorInt int color) {
        Paint paint = new Paint();
        paint.setFakeBoldText(true);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        return paint;
    }    //圆环
//    public static Paint getCircleBg(@ColorInt int color) {
//
//        Paint    mMonthTitlePaint = new Paint();
////        mMonthTitlePaint.setFakeBoldText(true);
//        mMonthTitlePaint.setAntiAlias(true);
//        mMonthTitlePaint.setTextSize(MONTH_LABEL_TEXT_SIZE);
////        mMonthTitlePaint.setTypeface(Typeface.create(mMonthTitleTypeface, Typeface.BOLD));
//        mMonthTitlePaint.setColor(mMonthTextColor);
////        mMonthTitlePaint.setTextAlign(Align.CENTER);
//        mMonthTitlePaint.setStyle(Paint.Style.FILL);
//
//        Paint paint = new Paint();
//        paint.setFakeBoldText(true);
//        paint.setAntiAlias(true);
//        paint.setColor(color);
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(5);
//        return paint;
//    }

}
