//package wangfeixixi.cip.ui.map;
//
//import android.graphics.BitmapFactory;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.like.LikeButton;
//import com.like.OnLikeListener;
//
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//
//import wangfeixixi.cip.R;
//import wangfeixixi.cip.beans.JsonRootBean;
//import wangfeixixi.cip.fram.BaseActivity;
//import wangfeixixi.cip.widget.carview.CarBean;
//import wangfeixixi.cip.widget.carview.utils.BitmapUtils;
//import wangfeixixi.cip.widget.udp.UDPUtils;
//import wangfeixixi.cip.widget.udp.server.IUDPResultListener;
//import wangfeixixi.com.base.UIUtils;
//import wangfeixixi.com.base.location.Gps;
//import wangfeixixi.com.base.location.PositionUtil;
//import wangfeixixi.lbs.LocationInfo;
//import wangfeixixi.lbs.gaode.GaodeMapService;
//
//public class MapActivity extends BaseActivity {
//    public String TAG = "MapActivity";
//    private GaodeMapService mLbs;
//    private FrameLayout mapContainer;
//    //    private CarView carview;
//    private LikeButton btn_start;
//    //    private View rl_container_car;
//    private TextView tv_warning;
//
//    //    private Compass compass;
//    //    private ImageView arrowView;
//    private ImageView iv_hand_other;
//    private ImageView arrowViewHeading;
////    private float currentAzimuth;
////    private SOTWFormatter sotwFormatter;
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        setContentView(R.layout.map_activity);
//        mapContainer = findViewById(R.id.fl_map_container);
//        mLbs = new GaodeMapService(this);
//
//        mapContainer.addView(mLbs.getMap());
//        mLbs.onCreate(savedInstanceState);
//
////        carview = findViewById(R.id.carview);
//        btn_start = findViewById(R.id.btn_start);
////        rl_container_car = findViewById(R.id.rl_container_car);
//        tv_warning = findViewById(R.id.tv_warning);
//        arrowViewHeading = findViewById(R.id.arrowViewHeading);
//        iv_hand_other = findViewById(R.id.iv_hand_other);
//
//    }
//
//    @Override
//    protected void initData() {
//        tv_warning.setText("预警信息提示");
//        mLbs.setLocationRes(R.mipmap.car);
////        VoiceUtil.getInstance().initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");
//
//        mLbs.setLocationRes(R.mipmap.car);
//
//        btn_start.setOnLikeListener(new OnLikeListener() {
//            @Override
//            public void liked(LikeButton likeButton) {
//                mLbs.clearAllMarker();
////                carview.updateBodys(new CarBean[0]);
////                VoiceUtil.getInstance().speek("开始驾驶");
//
//                UDPUtils.startServer(new IUDPResultListener() {
//                    @Override
//                    public void onResultListener(final JsonRootBean jsonRootBean) {
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    receiveCars(jsonRootBean);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });
//                    }
//                });
//
////                mLbs.moveCamera(new LocationInfo("自身", 30.330416, 121.317497), 20);
////                mLbs.addOrUpdateMarker(new LocationInfo("自身", 30.330416, 121.317497), BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
//
//            }
//
//            @Override
//            public void unLiked(LikeButton likeButton) {
//                UDPUtils.stopServer();
//            }
//        });
//        tv_warning.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                btn_start.setText(isStart ? "启动" : "结束");
//                if (!isStart) {//开始
//                    mLbs.clearAllMarker();
////                    carview.updateBodys(new CarBean[0]);
////                VoiceUtil.getInstance().speek("开始驾驶");
//
//                    UDPUtils.startServer(new IUDPResultListener() {
//                        @Override
//                        public void onResultListener(final JsonRootBean jsonRootBean) {
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    try {
//                                        receiveCars(jsonRootBean);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//                        }
//                    });
//                } else {
//                    UDPUtils.stopServer();
//                }
//
//                isStart = !isStart;
//            }
//        });
////        btn_start.setOnLongClickListener(new View.OnLongClickListener() {
////            @Override
////            public boolean onLongClick(View v) {
////                rl_container_car.setVisibility(rl_container_car.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
////                btn_start.setText(rl_container_car.getVisibility() == View.VISIBLE ? "长按隐藏" : (isStart ? "启动" : "结束"));
////                return false;
////            }
////        });
//    }
//
//    public void moveSelfCar(LocationInfo locationInfo) {
//        locationInfo.key = "自身坐标车";
//        mLbs.addOrUpdateMarker(locationInfo, BitmapFactory.decodeResource(UIUtils.getResources(), R.mipmap.car));
//        mLbs.moveCamera(locationInfo, 20);
//        mLbs.clearAllMarker();
//    }
//
//    private boolean isStart = false;
//
//    public long lastTime = 0;
//
//    public String mRemoteId;
//    public StringBuffer sb = new StringBuffer();
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveCars(JsonRootBean bean) {
//        ArrayList<CarBean> list = new ArrayList<>();
//        if (bean.hvDatas != null)
//            list.add(bean.hvDatas);
//        if (bean.rvDatas != null)
//            list.addAll(bean.rvDatas);
////        carview.updateBodys(list.toArray(new CarBean[list.size()]));
//
////        if (bean.hvDatas == null || bean.rvDatas == null) return;
//
//        long nowTime = System.currentTimeMillis();
//        long timeTemp = nowTime - lastTime;
//        StringBuffer sb = new StringBuffer();
//        sb.append("\n车辆数量：" + list.size());
//        sb.append("\n" + "时间：" + timeTemp);
////        sb.append("\n距离：" + Math.sqrt(Math.abs(list.get(1).x) * Math.abs(list.get(1).x) + Math.abs(list.get(1).y) * Math.abs(list.get(1).y)));
//        sb.append(bean.toString());
//        tv_warning.setText(sb.toString());
//        if ((timeTemp) > 200) {
////            carview.switchSpeed((int) bean.hvDatas.speed);
//            updateLbs(list);//相当耗时
//            iv_hand_other.setRotation(bean.rvDatas.get(0).heading);
//            arrowViewHeading.setRotation(bean.hvDatas.heading);
//            lastTime = nowTime;
//
//        }
//
//    }
//
//    private void updateLbs(ArrayList<CarBean> list) {
////        mLbs.clearAllMarker();
//        CarBean bean = null;
//        Gps gps = null;
//        LocationInfo local = null;
//        for (int i = 0; i < list.size(); i++) {
//            bean = list.get(i);
//            gps = PositionUtil.gps84_To_Gcj02(bean.latitude / 10000000, bean.longitude / 10000000);
//            local = new LocationInfo(String.valueOf(i), "car", gps.getWgLat(), gps.getWgLon(), bean.heading);
//            if (i == 0) {
//                mLbs.moveCamera(local, 20);
//            }
//
//            mLbs.addOrUpdateMarker(local, BitmapUtils.scaleBitmap(BitmapFactory.decodeResource(UIUtils.getResources(), R.drawable.car), 0.1f));
//        }
//    }
//
//    //    /**
////     * 获取巡航拥堵数据
////     * <p>
////     * 在巡航过程中，出现拥堵长度大于500米且拥堵时间大于5分钟时，
////     * 会进到 updateAimlessModeCongestionInfo 回调中，
////     * 通过 AimLessModeCongestionInfo 对象，可获取到道路拥堵信息（如：导致拥堵的事件类型、拥堵的状态等）。
////     *
////     * @param aimLessModeCongestionInfo
////     */
////    @Subscribe(threadMode = ThreadMode.MAIN)
////    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
////        ToastUtils.showToast("获取巡航拥堵数据");
////        VoiceUtil.speek("获取巡航拥堵数据");
////        Log.d(TAG, "roadName=" + aimLessModeCongestionInfo.getRoadName());
////        Log.d(TAG, "CongestionStatus=" + aimLessModeCongestionInfo.getCongestionStatus());
////        Log.d(TAG, "eventLonLat=" + aimLessModeCongestionInfo.getEventLon() + "," + aimLessModeCongestionInfo.getEventLat());
////        Log.d(TAG, "height=" + aimLessModeCongestionInfo.getLength());
////        Log.d(TAG, "time=" + aimLessModeCongestionInfo.getTime());
////        for (AMapCongestionLink link : aimLessModeCongestionInfo.getAmapCongestionLinks()) {
////            Log.d(TAG, "status=" + link.getCongestionStatus());
////            for (NaviLatLng latlng : link.getCoords()) {
////                Log.d(TAG, latlng.toString());
////            }
////        }
////    }
////
////    /**
////     * 获取巡航统计数据
////     * <p>
////     * 连续5个点速度大于15km/h后触发 updateAimlessModeStatistics 回调，通过 AimLessModeStat 对象可获取巡航的连续行驶距离和连
////     *
////     * @param aimLessModeStat
////     */
////    @Subscribe(threadMode = ThreadMode.MAIN)
////    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
////        ToastUtils.showToast("获取巡航统计数据");
////        VoiceUtil.speek("获取巡航统计数据");
////        Log.d(TAG, "distance=" + aimLessModeStat.getAimlessModeDistance());
////        Log.d(TAG, "time=" + aimLessModeStat.getAimlessModeTime());
////    }
////
////    /**
////     * 获取特殊道路设施数据
////     * <p>
////     * 在巡航过程中，出现特殊道路设施（如：测速摄像头、测速雷达；违章摄像头；铁路道口；应急车道等等）时，
////     * 回进到 OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] infos)，通过 AMapNaviTrafficFacilityInfo  对象可获取道路交通设施信息。
////     *
////     * @param aMapNaviTrafficFacilityInfos
////     */
////    @Subscribe(threadMode = ThreadMode.MAIN)
////    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
////        ToastUtils.showToast("获取特殊道路设施数据");
////        VoiceUtil.speek("获取特殊道路设施数据");
////        for (AMapNaviTrafficFacilityInfo info : aMapNaviTrafficFacilityInfos) {
//////            Toast.makeText(this, , Toast.LENGTH_LONG).show();
////            Log.d(TAG, "(trafficFacilityInfo.coor_X+trafficFacilityInfo.coor_Y+trafficFacilityInfo.distance+trafficFacilityInfo.limitSpeed):"
////                    + (info.getCoorX() + info.getCoorY() + info.getDistance() + info.getLimitSpeed()));
////        }
////    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mLbs.onResume();
////        compass.start();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
////        compass.start();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
////        compass.stop();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mLbs.onPause();
////        compass.stop();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mLbs.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mLbs.onDestroy();
//    }
//}
