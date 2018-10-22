package wangfeixixi.com.lib.widget.http

import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import wangfeixixi.com.lib.widget.http.api.ApiConstant
import wangfeixixi.com.lib.widget.http.api.RetrofitManager
import wangfeixixi.com.lib.widget.http.api.ShareTransform
import wangfeixixi.com.lib.widget.http.bean.ContactBean

object OpenUrl {
    //    fun deleteCarportCar(carId: String?): Observable<BaseBean<String>> {
//        val jsonObject = JSONObject()
//        jsonObject.put("carId", carId)
//        val requestBody = RequestBody.create(MediaType.parse(ApiConstant.MediaType), jsonObject.toString())
//        return RetrofitManager.getInstance().postJson.deleteCarportCar(requestBody).compose(ShareTransform.switchSchedulers())
//    }
    fun getData(): Observable<BaseBean<ContactBean>>? {
//        val jsonObject = JSONObject()
//        jsonObject.put("carId", carId)
//        val requestBody = RequestBody.create(MediaType.parse(ApiConstant.MediaType), jsonObject.toString())
        return RetrofitManager.getInstance().postJson.pullData().compose(ShareTransform.switchSchedulers())
    }
}
