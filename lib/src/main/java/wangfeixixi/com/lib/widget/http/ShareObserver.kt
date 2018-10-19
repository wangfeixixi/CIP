package wangfeixixi.com.lib.widget.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class ShareObserver<K> : Observer<BaseBean<K>> {


    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(baseBean: BaseBean<K>) {
        if (baseBean.code == 20000)
            onOk(baseBean.results ?: ("请求成功" as K))
        else
            onNo(baseBean.code, baseBean.msg)
    }

    override fun onError(e: Throwable) {
        onNo(99999, e.message.toString())
    }

    override fun onComplete() {

    }

    /**
     * baseBean : K 返回的数据
     */
    abstract fun onOk(results: K)

    /**
     * @param code : Int 错误码 99999为网络等非服务器返回的异常
     * @param msg : String 失败信息
     */
    abstract fun onNo(code: Int, msg: String)
}
