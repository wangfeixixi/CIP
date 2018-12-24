package wangfeixixi.cip.widget.carview;


import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wangfeixixi.com.base.ScreenUtils;

/**
 * 转换坐标类，将接收到的信息转换成屏幕坐标点
 * <p>
 * 人和车的坐标都应该是左上角
 */
public class CarUtils {

    /**
     * 转化比例：1米scale个像素
     */
    public static float scale = 30;

    /**
     * 车辆图片的缩放
     */
    public static float carBitmapScale = 2f;

    //默认车宽
    public static float carWidth = 2.5f;
    //默认车长
    public static float carLength = 4f;

    public static int getX0() {
        return getCarViewWidth() / 2;
    }

    public static int getY0() {
        return getCarViewHeight() / 3 * 2;
    }

    public static int getCarViewHeight() {
        return ScreenUtils.getScreenHeight() / 3 * 2;
    }

    public static int getCarViewWidth() {
        return ScreenUtils.getScreenWidth();
    }


    public static float getRoadWidth() {
        return carWidth * scale * 2;
    }

    public static float getHouseWidth() {
        return carWidth * scale * 2 - 40;
    }

    ////////////////////////////////////动态代码/////////////////////////////////////////////////////

//    public static CarBean carBean2Screen(CarBean bean) {
//        int y = ScreenUtils.getScreenHeight() / 3 * 2;
//        int x = ScreenUtils.getScreenWidth() / 2;
////        float width = bean.width;
////        float height = bean.height;
////        float x0 = x - width / 2;
////        float y0 = y - height / 2;
//        bean.leftMargin = (int) (x + bean.x * scale - bean.width * scale / 2);
//        bean.topMargin = (int) (y - bean.y * scale - bean.height * scale / 2);
//        return bean;
//    }


    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 将自身坐标系转换为屏幕坐标系
     *
     * @param carX     车辆的x像素值,坐标原点，车辆中心
     * @param carY     车辆的y像素值，坐标原点，车辆中心
     * @param bodyBean 扫描的物体信息
     * @return 屏幕坐标系物体区域
     */
    public static Rect shelf2Screen(float carX, float carY, CarBean bodyBean) {
        //1.车辆自身中心坐标（x1,y1）

        //2.


        float x = (carX + bodyBean.x * scale);
        float y = (carY - bodyBean.y * scale);
        float width = bodyBean.width * scale;
        float length = bodyBean.height * scale;
        return new Rect((int) (x - width), (int) (y - length), (int) x, (int) y);
    }

    public static float x2XView(float carX, CarBean bodyBean) {
        return carX + bodyBean.x * scale - bodyBean.width * scale;
    }

    public static float y2YView(float carY, CarBean bodyBean) {
        return carY - bodyBean.y * scale - bodyBean.height * scale;
    }

    //    public static void main(String[] args) {
//        speed2Arrays(10);
//        for (int j = 0; j < 100; j++) {
//
//
//            float[][] floats = speed2Arrays(j);
//            String temp = "";
//            for (int i = 0; i < floats.height; i++) {
//                temp += "  " + String.valueOf(floats[i]);
//                if (floats[i] != null) temp += "长度" + floats.height;
//            }
//            System.out.println(temp);
//
//        }
//
//
//    }
    private static float[] floatsSrc = {80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40};

    public static float[][] speed2Arrays(int speed) {
        List<float[]> strList = null;
        if (speed == 0) {
            return new float[][]{floatsSrc};
        } else if (speed <= 20) {
            strList = Arrays.asList(getLiensArrays(80, -40, 2));
        } else if (speed <= 40) {
            strList = Arrays.asList(getLiensArrays(80, -40, 4));
        } else if (speed <= 60) {
            strList = Arrays.asList(getLiensArrays(80, -40, 6));
        } else if (speed <= 80) {
            strList = Arrays.asList(getLiensArrays(80, -40, 8));
        } else if (speed <= 100) {
            strList = Arrays.asList(getLiensArrays(80, -40, 10));
        } else if (speed <= 120) {
            strList = Arrays.asList(getLiensArrays(80, -40, 12));
        } else {
            strList = Arrays.asList(getLiensArrays(80, -40, 20));
        }
        List<float[]> strListNew = new ArrayList<>();
        for (int i = 0; i < strList.size(); i++) {
            if (strList.get(i) != null) {
                strListNew.add(strList.get(i));
            }
        }
        return strListNew.toArray(new float[strListNew.size()][]);
    }

    /**
     * @param start 开始的数字 如80
     * @param end   结束的数字 如-40
     * @param cha   中间的插值 如10
     * @return 返回float[][]双重数组
     */
    public static float[][] getLiensArrays(int start, int end, int cha) {
        int size = (start - end) / cha;
        float[][] floatsReturn = new float[size][];
//        float[] floatsSrc = {80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40};
        float[] floatsTemp;
        while (true) {
            if (start != end && (start -= cha) > end) {
                floatsTemp = new float[floatsSrc.length];
                System.arraycopy(floatsSrc, 1, floatsTemp, 1, floatsSrc.length - 1);
                floatsTemp[0] = start;
                floatsReturn[--size] = floatsTemp;
            } else {
                break;
            }
        }
        return floatsReturn;
    }

//    public static float[][] linesOne = new float[][]{
//            new float[]{80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{70, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{60, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{50, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{40, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{30, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{20, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{10, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{0, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//
//
//            new float[]{-10, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{-20, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{-30, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//            new float[]{-40, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40, 80, 40},
//    };
}
