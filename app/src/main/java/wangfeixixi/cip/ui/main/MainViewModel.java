//package wangfeixixi.cip.ui.main;
//
//import android.app.Application;
//import android.databinding.ObservableArrayList;
//import android.databinding.ObservableBoolean;
//import android.databinding.ObservableField;
//import android.databinding.ObservableFloat;
//import android.databinding.ObservableInt;
//import android.support.annotation.NonNull;
//
//import java.util.ArrayList;
//
//import wangfeixixi.com.base.mvvm.base.BaseViewModel;
//import wangfeixixi.cip.beans.JsonRootBean;
//import wangfeixixi.cip.widget.carview.CarBean;
//import wangfeixixi.cip.widget.udp.UDPUtils;
//import wangfeixixi.cip.widget.udp.server.IUDPResultListener;
//import wangfeixixi.com.base.mvvm.binding.command.BindingAction;
//import wangfeixixi.com.base.mvvm.binding.command.BindingCommand;
//
//public class MainViewModelTest extends BaseViewModel {
//    public MainViewModelTest(@NonNull Application application) {
//        super(application);
//    }
//
//    public ObservableInt containerVisibility = new ObservableInt();
//    public ObservableFloat carviewSwitchSpeed = new ObservableFloat();
//    public ObservableArrayList<CarBean> carBeans = new ObservableArrayList<>();
//
//    public ObservableField<CarBean[]> carBeas = new ObservableField();
//
//    public ObservableBoolean isStartUDP = new ObservableBoolean(false);
//    public ObservableBoolean isShowContainer = new ObservableBoolean(false);
//
//    public ObservableField<String> warningText = new ObservableField<String>();
//
//    public BindingCommand startUDPClick = new BindingCommand(new BindingAction() {
//        @Override
//        public void call() {
//            if (!isStartUDP.get()) {
//                UDPUtils.startServer(new IUDPResultListener() {
//                    @Override
//                    public void onResultListener(final JsonRootBean jsonRootBean) {
//                        receiveCars(jsonRootBean);
//                    }
//                });
//            } else {
//                UDPUtils.stopServer();
//            }
//            isStartUDP.set(!isStartUDP.get());
//
//        }
//    });
//
//    public BindingCommand containerShowClick = new BindingCommand(new BindingAction() {
//        @Override
//        public void call() {
//            isShowContainer.set(!isShowContainer.get());
//        }
//    });
//
//    public long lastTime = 0;
//
//    public void receiveCars(JsonRootBean bean) {
//        ArrayList<CarBean> list = new ArrayList<>();
//        if (bean.hvDatas != null)
//            list.add(bean.hvDatas);
//        if (bean.rvDatas != null)
//            list.addAll(bean.rvDatas);
//
////        carBeas.set(list.toArray(new CarBean[list.size()]));
////        carBeans.clear();
////        carBeans.addAll(list);
//
//        carBeas.set(carBeans.toArray(new CarBean[carBeans.size()]));
//
//        long nowTime = System.currentTimeMillis();
//        long timeTemp = nowTime - lastTime;
//        StringBuffer sb = new StringBuffer();
//        sb.append("\n车辆数量：" + list.size());
//        sb.append("\n" + "时间：" + timeTemp);
//        sb.append("\n距离：" + Math.sqrt(Math.abs(list.get(1).x) * Math.abs(list.get(1).x) + Math.abs(list.get(1).y) * Math.abs(list.get(1).y)));
//        sb.append(bean.toString());
//        warningText.set(sb.toString());
//        if ((timeTemp) > 2000) {
//            carviewSwitchSpeed.set(bean.hvDatas.speed);
//        }
//        lastTime = nowTime;
//    }
//
//}
