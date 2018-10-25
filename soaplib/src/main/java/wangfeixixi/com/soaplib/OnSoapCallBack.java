package wangfeixixi.com.soaplib;

import wangfeixixi.com.soaplib.beans.BaseSoapBean;

/**
 * 作者：guoyzh
 * 时间：2018/8/31
 * 功能：基于soap的网络请求成功的回调
 */

public interface OnSoapCallBack {
    /**
     * 请求成功的回调
     *
     * @param response
     */
    void onOk(BaseSoapBean response);

    /**
     * 请求失败后返回失败信息
     *
     * @param e
     */
    void onNo(Exception e);
}
