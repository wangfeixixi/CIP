package wangfeixixi.com.lib.widget.http.api;


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

    @POST(prefixPath + "/add/")
    Observable<BaseBean<ContactBean>> add(@Field("double") Double a, @Field("double") Double b);

    @POST(prefixPath + "/add/")
    @FormUrlEncoded
    Observable<BaseBean<ContactBean>> add2(RequestBody requestBody);
}
