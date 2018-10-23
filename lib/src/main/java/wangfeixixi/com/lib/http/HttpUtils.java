package wangfeixixi.com.lib.http;

import wangfeixixi.com.lib.widget.http.OpenUrl;
import wangfeixixi.com.lib.widget.http.ShareObserverNew;
import wangfeixixi.com.lib.widget.http.bean.ContactBean;

public class HttpUtils {
    public static void getDatas() {
        OpenUrl.INSTANCE.getData().subscribe(new ShareObserverNew<ContactBean>() {
            @Override
            public void onOk(ContactBean result) {

            }

            @Override
            public void onNo(int code, String msg) {

            }
        });
    }

}
