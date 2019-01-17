package wangfeixixi.com.cw.widget.udp.server;//package wangfeixixi.cip.widget.udp.server;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import wangfeixixi.cip.R;
//import wangfeixixi.cip.beans.JsonRootBean;
//import wangfeixixi.cip.fram.BaseActivity;
//import wangfeixixi.cip.widget.udp.server.UDPUtils;
//
//public class UDPServerTestActivity extends BaseActivity {
//
//    private Button btn;
//    private TextView tv;
//
//    @Override
//    protected void initView(Bundle savedInstanceState) {
//        setContentView(R.layout.udp_server_test_activity);
//        btn = findViewById(R.id.btn);
//        tv = findViewById(R.id.tv);
//    }
//
//    @Override
//    protected void initData() {
//        final IUDPResultListener listener = new IUDPResultListener() {
//            @Override
//            public void onResultListener(final JsonRootBean jsonRootBean) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            receiveCars(jsonRootBean);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//        };
//
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UDPUtils.udpServer(listener);
//            }
//        });
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void receiveCars(JsonRootBean bean) {
//        tv.setText(bean.toString());
//    }
//}
