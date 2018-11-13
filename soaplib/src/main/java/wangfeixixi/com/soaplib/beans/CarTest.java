package wangfeixixi.com.soaplib.beans;

import android.content.ClipData;

import java.util.List;

public class CarTest extends BaseSoapBean {
    public String ms;
    public int num;
    public long time;

    public List<ClipData.Item> infoArray;

    public List<Car> cars;

    public class Car {
        public String name;
        public double x;
        public double y;
        public int speed;

        public String key;
        public String address;
        public double latitude;
        public double longitude;
        public float rotation = 0.0F;
    }
}
