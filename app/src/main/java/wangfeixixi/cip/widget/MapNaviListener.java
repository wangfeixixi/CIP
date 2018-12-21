//package wangfeixixi.cip.utils;
//
//import com.amap.api.navi.AMapNaviListener;
//import com.amap.api.navi.model.AMapLaneInfo;
//import com.amap.api.navi.model.AMapNaviCameraInfo;
//import com.amap.api.navi.model.AMapNaviCross;
//import com.amap.api.navi.model.AMapNaviInfo;
//import com.amap.api.navi.model.AMapNaviLocation;
//import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
//import com.amap.api.navi.model.AMapServiceAreaInfo;
//import com.amap.api.navi.model.AimLessModeCongestionInfo;
//import com.amap.api.navi.model.AimLessModeStat;
//import com.amap.api.navi.model.NaviInfo;
//import com.autonavi.tbt.TrafficFacilityInfo;
//
//import org.greenrobot.eventbus.EventBus;
//
//import wangfeixixi.com.bdvoice.VoiceUtil;
//
//public class MapNaviListener implements AMapNaviListener {
//
//    public String TAG = "navilistener";
//
//    @Override
//    public void onInitNaviFailure() {
//        VoiceUtil.speek("巡航初始化失败");
//    }
//
//    @Override
//    public void onInitNaviSuccess() {
//
//        VoiceUtil.speek("巡航初始化成功");
//    }
//
//    @Override
//    public void onStartNavi(int i) {
//
//        VoiceUtil.speek("开始巡航");
//    }
//
//    @Override
//    public void onTrafficStatusUpdate() {
//
//        VoiceUtil.speek("交通数据更新");
//    }
//
//    @Override
//    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
//
////        VoiceUtil.speek("位置改变");
//    }
//
//    @Override
//    public void onGetNavigationText(int i, String s) {
//
//        VoiceUtil.speek("获取巡航文本信息");
//    }
//
//    @Override
//    public void onEndEmulatorNavi() {
//
//        VoiceUtil.speek("结束模拟器巡航");
//    }
//
//    @Override
//    public void onArriveDestination() {
//
//        VoiceUtil.speek("到达目的地");
//    }
//
//    @Override
//    public void onCalculateRouteSuccess() {
//
//        VoiceUtil.speek("计算路线成功");
//    }
//
//    @Override
//    public void onCalculateRouteFailure(int i) {
//
//        VoiceUtil.speek("计算路线失败");
//    }
//
//    @Override
//    public void onReCalculateRouteForYaw() {
//
//        VoiceUtil.speek("重新计算路线");
//    }
//
//    @Override
//    public void onReCalculateRouteForTrafficJam() {
//
//        VoiceUtil.speek("重新计算交通路线");
//    }
//
//    @Override
//    public void onArrivedWayPoint(int i) {
//
//        VoiceUtil.speek("到达指定地点");
//    }
//
//    @Override
//    public void onGpsOpenStatus(boolean b) {
//
//        VoiceUtil.speek("gps信号状态" + b);
//    }
//
//    @Override
//    public void onNaviInfoUpdate(NaviInfo naviInfo) {
//
//        VoiceUtil.speek("巡航信息更新");
//    }
//
//    @Override
//    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {
//
//        VoiceUtil.speek("巡航信息已经更新");
//    }
//
//    @Override
//    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {
//
//        VoiceUtil.speek("更新地图信息");
//    }
//
//    @Override
//    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {
//
//        VoiceUtil.speek("服务区域改变");
//    }
//
//    @Override
//    public void showCross(AMapNaviCross aMapNaviCross) {
//
//        VoiceUtil.speek("显示街道");
//    }
//
//    @Override
//    public void hideCross() {
//
//        VoiceUtil.speek("隐藏街道");
//    }
//
//    @Override
//    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {
//
//        VoiceUtil.speek("显示社区信息");
//    }
//
//    @Override
//    public void hideLaneInfo() {
//
//        VoiceUtil.speek("隐藏社区信息");
//    }
//
//    @Override
//    public void onCalculateMultipleRoutesSuccess(int[] ints) {
//
//        VoiceUtil.speek("计算多条路线成功");
//    }
//
//    @Override
//    public void notifyParallelRoad(int i) {
//
//        VoiceUtil.speek("提醒"+i);
//    }
//
//    @Override
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {
//
//        VoiceUtil.speek("更新交通");
//    }
//
//    /**
//     * 获取特殊道路设施数据
//     * <p>
//     * 在巡航过程中，出现特殊道路设施（如：测速摄像头、测速雷达；违章摄像头；铁路道口；应急车道等等）时，
//     * 回进到 OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] infos)，通过 AMapNaviTrafficFacilityInfo  对象可获取道路交通设施信息。
//     *
//     * @param aMapNaviTrafficFacilityInfos
//     */
//    @Override
//    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {
////        for (AMapNaviTrafficFacilityInfo info : aMapNaviTrafficFacilityInfos) {
//////            Toast.makeText(this, , Toast.LENGTH_LONG).show();
////            Log.d(TAG, "(trafficFacilityInfo.coor_X+trafficFacilityInfo.coor_Y+trafficFacilityInfo.distance+trafficFacilityInfo.limitSpeed):"
////                    + (info.getCoorX() + info.getCoorY() + info.getDistance() + info.getLimitSpeed()));
////        }
//        EventBus.getDefault().post(aMapNaviTrafficFacilityInfos);
//
//        VoiceUtil.speek("监听数据：获取特殊道路设施数据");
//    }
//
//    @Override
//    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {
//
//    }
//
//    /**
//     * 获取巡航统计数据
//     * <p>
//     * 连续5个点速度大于15km/h后触发 updateAimlessModeStatistics 回调，通过 AimLessModeStat 对象可获取巡航的连续行驶距离和连
//     *
//     * @param aimLessModeStat
//     */
//    @Override
//    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {
////        Toast.makeText(this, "看log", Toast.LENGTH_SHORT).show();
////        Log.d(TAG, "distance=" + aimLessModeStat.getAimlessModeDistance());
////        Log.d(TAG, "time=" + aimLessModeStat.getAimlessModeTime());
//        EventBus.getDefault().post(aimLessModeStat);
//        VoiceUtil.speek("监听数据：获取巡航统计数据");
//    }
//
//    /**
//     * 获取巡航拥堵数据
//     * <p>
//     * 在巡航过程中，出现拥堵长度大于500米且拥堵时间大于5分钟时，
//     * 会进到 updateAimlessModeCongestionInfo 回调中，
//     * 通过 AimLessModeCongestionInfo 对象，可获取到道路拥堵信息（如：导致拥堵的事件类型、拥堵的状态等）。
//     *
//     * @param aimLessModeCongestionInfo
//     */
//    @Override
//    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {
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
//        EventBus.getDefault().post(aimLessModeCongestionInfo);
//        VoiceUtil.speek("监听数据：获取巡航拥堵数据");
//    }
//
//    @Override
//    public void onPlayRing(int i) {
//
//    }
//}
