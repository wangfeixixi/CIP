//package wangfeixixi.cip.ui;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import wangfeixixi.cip.MapActivity;
//import wangfeixixi.cip.R;
//import wangfeixixi.cip.TestDataActivity;
//import wangfeixixi.cip.fram.BaseActivity;
//import wangfeixixi.cip.push.HttpService;
//import wangfeixixi.cip.utils.RandomBodyUtils;
//import wangfeixixi.cip.utils.ServiceUtils;
//import wangfeixixi.com.bdvoice.VoiceUtil;
//import wangfeixixi.com.car.CarBean;
//import wangfeixixi.com.car.CarView;
//import wangfeixixi.cip.utils.ThreadUtils;
//import wangfeixixi.com.soaplib.beans.CarTest;
//
//public class MainActivity extends BaseActivity implements View.OnClickListener {
//    private CarView testView;
//    private TextView tv_info;
//    private View btn_switch;
//    private View btn_stop;
//    private View btn_update;
//    private View btn_map;
//    private View btn_voice;
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        setContentView(R.layout.main_activity);
//        tv_info = findViewById(R.id.tv_info);
//        btn_switch = findViewById(R.id.btn_test);
//        btn_stop = findViewById(R.id.btn_stop);
//        btn_update = findViewById(R.id.btn_update);
//        testView = findViewById(R.id.testView);
//        btn_map = findViewById(R.id.btn_map);
//        btn_voice = findViewById(R.id.btn_voice);
//
//        btn_switch.setOnClickListener(this);
//        btn_stop.setOnClickListener(this);
//        btn_update.setOnClickListener(this);
//        btn_map.setOnClickListener(this);
//        btn_voice.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_update:
//                isUpdating = true;
//                update();
//                break;
//            case R.id.btn_stop:
//                testView.stop();
//                ServiceUtils.stopService(HttpService.class);
//                isUpdating = false;
//                initPermission();
//                break;
//            case R.id.btn_test:
//                startActivity(new Intent(this, TestDataActivity.class));
//                break;
//            case R.id.btn_map:
//                startActivity(new Intent(this, MapActivity.class));
//                break;
//            case R.id.btn_voice:
//                VoiceUtil.speek("我是社会主义接班人");
////                startActivity(new Intent(mCtx, SynthActivity.class));
//                break;
//        }
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    public boolean isUpdating = false;
//
//    public void update() {
//        if (isUpdating) {
//            int rand = (int) (Math.random() * 3);
//            ArrayList<CarBean> list = new ArrayList<>();
//            for (int i = 0; i < rand; i++) {
//                list.add(RandomBodyUtils.getRandowBody());
//            }
//            CarBean[] beans = list.toArray(new CarBean[list.size()]);
////            carView.updateBodys(beans);
//            testView.updateBodys(beans);
//
//            ThreadUtils.runOnUiThreadDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    update();
//                }
//            }, 10);
//        } else {
//            ThreadUtils.stop();
//        }
//    }
//
//    @Override
//    public void receiveDatas(CarTest carBean) {
//    }
//
//    /**
//     * android 6.0 以上需要动态申请权限
//     */
//    private void initPermission() {
//        String permissions[] = {
//                Manifest.permission.INTERNET,
//                Manifest.permission.ACCESS_NETWORK_STATE,
//                Manifest.permission.MODIFY_AUDIO_SETTINGS,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_SETTINGS,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.CHANGE_WIFI_STATE
//        };
//
//        ArrayList<String> toApplyList = new ArrayList<String>();
//
//        for (String perm : permissions) {
//            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
//                toApplyList.add(perm);
//                //进入到这里代表没有权限.
//            }
//        }
//        String tmpList[] = new String[toApplyList.size()];
//        if (!toApplyList.isEmpty()) {
//            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        // 此处为android 6.0以上动态授权的回调，用户自行实现。
//        VoiceUtil.speek("啦啦啦，啦啦啦，我是卖报的小行家");
//    }
//}
