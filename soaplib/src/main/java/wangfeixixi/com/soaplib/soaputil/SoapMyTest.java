//package wangfeixixi.com.soaplib.soaputil;
//
//import android.util.Log;
//
//import org.ksoap2.SoapEnvelope;
//import org.ksoap2.serialization.MarshalBase64;
//import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.SoapSerializationEnvelope;
//import org.ksoap2.transport.HttpTransportSE;
//
///**
// * Created by xuany on 2018/10/24.
// */
//
//public class SoapMyTest {
//    public static void test() {
//
//        try
//        {
////            Log.d("aaaaa", "url:"+SDKConfig.BaseUrl+",nameSpace:"+nameSpace+",methodName:"+methodName+",params:"+params.toString());
//            // 第一：实例化SoapObject
//            // 对象，指定webService的命名空间（从相关WSDL文档中可以查看命名空间），以及调用方法名称
//            SoapObject rpc = new SoapObject(nameSpace, methodName);
//            // 第二步：假设方法有参数的话,设置调用方法参数
//
//            for(String key:params.keySet()){
//                rpc.addProperty(key, params.get(key));
//            }
//            // 第三步：设置SOAP请求信息(参数部分为SOAP协议版本号，与你要调用的webService中版本号一致)
//            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
//            // 第四步：注册Envelope
//            envelope.enc="http://schemas.xmlsoap.org/soap/encoding/";
//            envelope.env="http://schemas.xmlsoap.org/soap/envelope/";
//            envelope.xsi="http://www.w3.org/2001/XMLSchema-instance";
//            envelope.xsd="http://www.w3.org/2001/XMLSchema";
//            envelope.bodyOut = rpc;
//            envelope.dotNet = false;//注意跟服务器对应，如果服务器用.net开发，就为true
////                  envelope.setOutputSoapObject(rpc);
//            new MarshalBase64().register(envelope);
//            // 第五步：构建传输对象，并指明WSDL文档URL
//            HttpTransportSE ht = new HttpTransportSE(SDKConfig.BaseUrl);
//            ht.debug = true;
//            // 第六步:调用WebService(其中参数为1：nameSpace+方法名称，2：Envelope对象)
//            ht.call(nameSpace+methodName, envelope);
////                   SoapPrimitive detal = (SoapPrimitive) envelope.getResponse();
//            // SoapObject detal = (SoapObject) envelope.bodyIn;
//            SoapObject detal = (SoapObject) envelope.bodyIn;
//            res.onSuccess(detal);
//            // 第七步：解析返回数据
//        } catch (Exception e)
//        {
//            res.onError(e);
//            e.printStackTrace();
//        }
//        String result = object.getPropertySafelyAsString("Result");
//
//        Document document = DocumentHelper.parseText(paramString);
//        DefaultXPath xpath = new DefaultXPath("//ns2:"+RESPONSE_NAME);
//        xpath.setNamespaceURIs(Collections.singletonMap("ns2",
//                NAME_SPACE));
//        List list = xpath.selectNodes(document);
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Element rootElement = (Element) iterator.next();// 获取根节点对象
//
//            sumAdBackResult.setVersion(getString(rootElement.element("Version")));
//
//    }
//}
