package wangfeixixi.com.cw.ui;

import java.text.DecimalFormat;

public class NumberTransfer {
    public static String double2String(double a) {
        return String.valueOf(Double.parseDouble(new DecimalFormat("#.##").format(a)));
    }
}
