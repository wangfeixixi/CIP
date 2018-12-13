package wangfeixixi.cip.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import wangfeixixi.cip.R;
import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.fram.BaseActivity;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.carview.CarView;
import wangfeixixi.cip.widget.udp.UDPUtils;
import wangfeixixi.cip.widget.udp.server.UDPResultListener;
import wangfeixixi.share.circle.DialProgress;

public class CarViewActivity extends BaseActivity {
    private CarView carview;
    private LikeButton btn_start;
    private TextView tv_warning;
    private ImageView iv_hand_rv;
    private ImageView iv_hand_hv;
    private DialProgress dial_progress_hv;
    private DialProgress dial_progress_rv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.carview_activity);
        carview = findViewById(R.id.carview);
        btn_start = findViewById(R.id.btn_start);
        tv_warning = findViewById(R.id.tv_warning);
        iv_hand_hv = findViewById(R.id.iv_hand_hv);
        iv_hand_rv = findViewById(R.id.iv_hand_rv);
        dial_progress_hv = findViewById(R.id.dial_progress_hv);
        dial_progress_rv = findViewById(R.id.dial_progress_rv);
    }

    @Override
    protected void initData() {
        dial_progress_hv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_warning.setVisibility(tv_warning.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        btn_start.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                carview.updateBodys(new CarBean[0]);
//                VoiceUtil.getInstance().speek("开始驾驶");
                UDPUtils.startServer(new UDPResultListener() {
                    @Override
                    public void onResultListener(final JsonRootBean jsonRootBean) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    receiveCars(jsonRootBean);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                UDPUtils.stopServer();
            }
        });
    }

    public long lastTime = 0;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveCars(JsonRootBean bean) {
        ArrayList<CarBean> list = new ArrayList<>();
        if (bean.hvDatas != null)
            list.add(bean.hvDatas);
        if (bean.rvDatas != null)
            list.addAll(bean.rvDatas);
        carview.updateBodys(list.toArray(new CarBean[list.size()]));

        if (bean.hvDatas == null || bean.rvDatas == null) return;

        long nowTime = System.currentTimeMillis();
        long timeTemp = nowTime - lastTime;
        StringBuffer sb = new StringBuffer();
        sb.append("\n车辆数量：" + list.size());
        sb.append("\n" + "时间：" + timeTemp);
        sb.append("\n距离：" + Math.sqrt(Math.abs(list.get(1).x) * Math.abs(list.get(1).x) + Math.abs(list.get(1).y) * Math.abs(list.get(1).y)));
        sb.append(bean.toString());
        tv_warning.setText(sb.toString());
        if ((timeTemp) > 2000) {
            carview.switchSpeed((int) bean.hvDatas.speed);
            iv_hand_rv.setRotation(bean.rvDatas.get(0).heading);
            iv_hand_hv.setRotation(bean.hvDatas.heading);
            dial_progress_hv.setValue(bean.hvDatas.speed);
            dial_progress_rv.setValue(bean.rvDatas.get(0).speed);
        }
        lastTime = nowTime;
    }
}
