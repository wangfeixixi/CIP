package wangfeixixi.com.cw.ui;//package wangfeixixi.cip.ui;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//import wangfeixixi.cip.R;
//import wangfeixixi.cip.beans.JsonRootBean;
//import wangfeixixi.cip.fram.BaseActivity;
//import wangfeixixi.cip.widget.carview.CarBean;
//import wangfeixixi.cip.widget.carview.CarView;
//import wangfeixixi.cip.widget.udp.server.UDPUtils;
//import wangfeixixi.cip.widget.udp.server.IUDPResultListener;
//import wangfeixixi.com.base.UIUtils;
//import wangfeixixi.com.base.data.DateUtils;
//import wangfeixixi.com.bdvoice.VoiceUtil;
//import wangfeixixi.share.circle.DialProgress;
//
//public class CarViewActivity extends BaseActivity {
//    private CarView carview;
//    //    private LikeButton btn_start;
//    private TextView tv_warning;
//    private ImageView iv_hand_rv;
//    private ImageView iv_hand_hv;
//    private DialProgress dial_progress_hv;
//    private DialProgress dial_progress_rv;
//    private TextView tv_tips;
//    private IUDPResultListener listener;
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        setContentView(R.layout.carview_activity);
//        carview = findViewById(R.id.carview);
////        btn_start = findViewById(R.id.btn_start);
//        tv_tips = findViewById(R.id.tv_tips);
//        tv_warning = findViewById(R.id.tv_warning);
//        iv_hand_hv = findViewById(R.id.iv_hand_hv);
//        iv_hand_rv = findViewById(R.id.iv_hand_rv);
//        dial_progress_hv = findViewById(R.id.dial_progress_hv);
//        dial_progress_rv = findViewById(R.id.dial_progress_rv);
//    }
//
//    @Override
//    protected void initData() {
//        VoiceUtil.getInstance().initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");
//        listener = new IUDPResultListener() {
//            @Override
//            public void onResultListener(final JsonRootBean bean) {
//                final ArrayList<CarBean> list = new ArrayList<>();
//                if (bean.hvDatas != null)
//                    list.add(bean.hvDatas);
//                if (bean.rvDatas != null)
//                    list.addAll(bean.rvDatas);
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (list.size() < 2) {
//                            tv_warning.setText(bean.toString());
//                            return;
//                        }
//                        receiveCars(list);
//                    }
//                });
//            }
//        };
//
//        dial_progress_rv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tv_warning.setVisibility(tv_warning.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
//            }
//        });
//
////        carview.updateBodys(new CarBean[0]);
////        UDPUtils.udpServer(listener);
////        dial_progress_hv.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                carview.updateBodys(new CarBean[0]);
////                UDPUtils.udpServer(listener);
////            }
////        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        carview.updateBodys(new CarBean[0]);
//        UDPUtils.startServer(listener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        UDPUtils.stopUDP();
//    }
//
//    public long lastTime = 0;
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveCars(ArrayList<CarBean> beans) {
//        carview.updateBodys(beans.toArray(new CarBean[beans.size()]));
//        long nowTime = System.currentTimeMillis();
//        long timeTemp = nowTime - lastTime;
//        StringBuffer sb = new StringBuffer();
//        sb.append("\n" + String.valueOf(DateUtils.getCurrentDate(DateUtils.dateFormatYMDHMS)));
//
//        double sqrt = Math.sqrt(Math.abs(beans.get(1).x) * Math.abs(beans.get(1).x) + Math.abs(beans.get(1).y) * Math.abs(beans.get(1).y));
//        double jvli = Double.parseDouble(new DecimalFormat("#.##").format(sqrt));
//
//        if (beans.get(0).fcwAlarm != 0) {
//            tv_tips.setText("附近车辆:" + jvli + "米");
//        } else {
//            tv_tips.setText("");
//        }
//        sb.append("\n距离：" + jvli);
//        sb.append("\n" + beans.get(0).toString());
//        sb.append("\n" + beans.get(1).toString());
//        tv_warning.setText(sb.toString());
//        iv_hand_hv.setRotation(beans.get(0).heading);
//        iv_hand_rv.setRotation(beans.get(1).heading);
//        dial_progress_hv.setValue(beans.get(0).speed * 3.6f);
//        dial_progress_rv.setValue(beans.get(1).speed * 3.6f);
//
//        if ((timeTemp) > 2000) {
//            carview.switchSpeed(beans.get(0).speed * 3.6f);
//            if (beans.get(0).fcwAlarm != 0)
//                VoiceUtil.getInstance().speek("注意安全距离");
//            lastTime = nowTime;
//        }
//    }
//}
