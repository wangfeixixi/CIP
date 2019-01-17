package wangfeixixi.com.cw.widget.udp.client;//package wangfeixixi.cip.widget.udp.client;
//
//import android.os.Bundle;
//import android.view.View;
//
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import wangfeixixi.cip.R;
//import wangfeixixi.cip.beans.JsonRootBean;
//import wangfeixixi.cip.fram.BaseActivity;
//
//public class UDPClientTestActivity extends BaseActivity {
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        setContentView(R.layout.udp_client_test_activity);
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Test.testUDPSend();
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveCars(JsonRootBean bean) {
//    }
//}
