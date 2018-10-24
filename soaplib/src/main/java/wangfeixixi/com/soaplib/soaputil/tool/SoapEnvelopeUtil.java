package wangfeixixi.com.soaplib.soaputil.tool;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;

/**
 * 描述：  获取通过soap访问后的结果中的数据
 * 作者：  郭永振
 * 时间：  2018-08-31 10:38:51
 */
public class SoapEnvelopeUtil {
    public SoapEnvelopeUtil() {
    }

    public static String getTextFromResponse(SoapEnvelope envelope) {
        if (envelope != null && envelope.bodyIn != null) {
            if (!(envelope.bodyIn instanceof SoapObject)) {
                return null;
            } else if (((SoapObject) envelope.bodyIn).getPropertyCount() == 0) {
                return null;
            } else {
                return ((SoapObject) envelope.bodyIn).getProperty(0) == null ? null : ((SoapObject) envelope.bodyIn).getProperty(0).toString();
            }
        } else {
            return null;
        }
    }
}