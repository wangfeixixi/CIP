package wangfeixixi.com.soaplib.soaputil;

/**
 * 作者：guoyzh
 * 时间：2018/8/31
 * 功能：基于soap的网络请求成功的回调
 */

public interface OnSoapResponse {
    /**
     * 请求成功的回调
     *
     * @param response
     */
    void onSuccess(BaseSoapResBean response);

    /**
     * 请求失败后返回失败信息
     *
     * @param failMsg
     */
    void onFail(Exception failMsg);
}
