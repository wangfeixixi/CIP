package wangfeixixi.com.soaplib;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * 作者：guoyzh
 * 时间：2018/8/31
 * 功能：
 */

public class XmlParser {
    public static String xml2json(String response) {
        JSONObject jsonObj = null;
        try {
            jsonObj = XML.toJSONObject(response);
        } catch (JSONException e) {
            Log.e("JSON exception", e.getMessage());
            e.printStackTrace();
        }
        return jsonObj.toString();
    }
}
