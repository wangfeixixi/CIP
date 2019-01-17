package wangfeixixi.com.cw.widget.udp.client.data;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import wangfeixixi.com.cw.beans.JsonRootBean;

public class TxtUtils {
    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
//        result.append("{[\n");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        result.append("]}");
        try {
            return new String(result.toString().getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 读取txt文件的内容
     *
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static ArrayList<String> txt2Strings(File file) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> datas = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                String stemp = System.lineSeparator() + s;
                if (stemp.contains(",") && stemp.length() < 5) {
//                    result.append(stemp);
                    datas.add(result.toString());
                    result = new StringBuilder();
                } else {
                    result.append(stemp);
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = getJsonAraray();

        for (int i = 0; i < strings.size(); i++) {
            JsonRootBean jsonRootBean = JSON.parseObject(strings.get(i), JsonRootBean.class);
            System.out.println("打印的数据" + jsonRootBean.sn);
        }
    }

    public static ArrayList<String> getJsonAraray() {
        File file = new File(filePath);
        return txt2Strings(file);
    }

//    public static String filePath = "C:\\Users\\xuany\\temp\\v2xJsonLog.txt";
//    public static String filePath = "C:\\Users\\xuany\\temp\\v2xJsonLog2-16-32-24-FCW.txt";
    public static String filePath = "C:\\Users\\xuany\\temp\\v2xJsonLog0107.txt";
}
