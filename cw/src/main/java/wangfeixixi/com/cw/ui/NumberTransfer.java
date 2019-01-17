package wangfeixixi.com.cw.ui;

import java.text.DecimalFormat;

public class NumberTransfer {
    public static String double2String(double a) {
        return String.valueOf(Double.parseDouble(new DecimalFormat("#.##").format(a)));
    }

    public static String float2String(float a) {
        return String.valueOf(Double.parseDouble(new DecimalFormat("#.##").format(a)));
    }
}
