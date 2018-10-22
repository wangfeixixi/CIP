package wangfeixixi.com.lib.widget.http.api;


import org.jetbrains.annotations.Nullable;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import wangfeixixi.com.lib.widget.http.BaseBean;
import wangfeixixi.com.lib.widget.http.bean.ContactBean;

public interface ApiService {
    String prefixPath = "";

    public static final String userArgent = ApiConstant.BASE_URL + prefixPath + "/html/userArgent.html";

    //1、用户订单详情查询（待完善）
    @FormUrlEncoded
    @POST(prefixPath + "/api/order/csc/v1/pullData.do")
    Observable<BaseBean<ContactBean>> pullData();

//    Observable<BaseBean<ContactBean>> pullData(RequestBody requestBody);
}
