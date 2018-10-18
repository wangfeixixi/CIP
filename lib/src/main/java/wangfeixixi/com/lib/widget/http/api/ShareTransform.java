package wangfeixixi.com.lib.widget.http.api;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShareTransform {
//    private final static String TAG = "ShareTransform";

    public static <T> ObservableTransformer<T, T> switchSchedulers() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

//    /**
//     * 轮询
//     *
//     * @param delayTime 延迟时间
//     * @param count     轮询次数
//     * @param <T>
//     * @return
//     */
//    public static <T> ObservableTransformer<T, T> switchRetreatSchedulers(final long delayTime, final long count) {
//        final Disposable[] disposable = new Disposable[1];//成员变量记录
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                upstream.interval(delayTime, count, TimeUnit.MILLISECONDS)
//                        .observeOn(Schedulers.io())//处理的线程
//                        .subscribeOn(AndroidSchedulers.mainThread())//观察的线程
//                        .subscribe(new Observer<Long>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//                                disposable[0] = d;
//                            }
//
//                            @Override
//                            public void onNext(Long aLong) {
//                                //dddd zhuxiancheng
//                                //aLong 0 ----12345
//                                // //
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
//                return upstream;
//            }
//        };
//    }

    /**
     * 轮询interval
     * 每隔一段时间发送一个数据，用于轮询的时间间隔控制
     * take
     * 限制通过的数据流的数量，用来限制轮询次数
     * flatMap
     * 进行数据的变换
     * takeUntil
     * 设置通过的条件，一旦满足条件就停止发送，这里用于设定轮询结束的条件
     * filter
     * 对通过的数据进行过滤，用于控制数据流，处理判断语句
     */
//    public static <T> ObservableTransformer<T, T> switchRetreatSchedulerNew(final long delayTime) {
//        final Disposable[] disposable = new Disposable[1];//成员变量记录
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> upstream) {
//                return upstream.observeOn(Schedulers.io())//处理的线程
//                        .subscribeOn(AndroidSchedulers.mainThread());//观察的线程
//            }
//        };
//    }

//    public static <T> ObservableTransformer<BaseBean<T>, T> httpTransformer() {
//        return new ObservableTransformer<BaseBean<T>, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<BaseBean<T>> upstream) {
//                return upstream
//                        .subscribeOn(Schedulers.io())
////                        .unsubscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<BaseBean<T>, T>() {
//                            @Override
//                            public T apply(BaseBean<T> tBaseBean) throws Exception {
////                                Log.d(TAG, "apply: " + tBaseBean.toString());
//                                if (tBaseBean.isSucessful()) {
//                                    if (tBaseBean.getResults() != null) {
//                                        return tBaseBean.getResults();
//                                    } else {
////                                        throw new Exception(String.valueOf(tBaseBean.showGetCode()));
////                                        Class <T>  entityClass  =  (Class <T> ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
////                                        return entityClass.newInstance();
//                                        return (T) new Object();
//                                    }
//                                } else {
//                                    throw new Exception(String.valueOf(tBaseBean.getCode()));
//                                }
//                            }
//                        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
//                            @Override
//                            public ObservableSource<? extends T> apply(Throwable throwable) throws Exception {
//                                return Observable.error(throwable);
//                            }
//                        });
////                        .doOnError(new Consumer<Throwable>() {
////                            @Override
////                            public void accept(Throwable throwable) throws Exception {
////                                String message = throwable.getMessage();
////                                if ("20007".equals(message)) {
////                                    reLogin();
////                                }
////                            }
////                        });
////                        .retryWhen(new RetryWithDelay(1, 2));
//            }
//        };
//    }
}
