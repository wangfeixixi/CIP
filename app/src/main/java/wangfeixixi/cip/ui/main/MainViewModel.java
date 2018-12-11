package wangfeixixi.cip.ui.main;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import wangfeixixi.cip.widget.carview.CarBean;
import wangfeixixi.cip.widget.udp.UDPUtils;

public class MainViewModel extends BaseViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public ObservableInt containerVisibility = new ObservableInt();
    public ObservableField<CarBean[]> carBeas = new ObservableField();
    public ObservableBoolean isStartUDP = new ObservableBoolean(false);
    public ObservableBoolean isShowContainer = new ObservableBoolean(false);

    public ObservableField<String> warningText = new ObservableField<String>();

    public BindingCommand startUDPClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            if (!isStartUDP.get()) {
                carBeas.set(new CarBean[0]);
                UDPUtils.startUDPServer();
            } else {
                UDPUtils.stopUDPServer();
            }
            isStartUDP.set(!isStartUDP.get());
        }
    });
    public BindingCommand containerShowClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            isShowContainer.set(!isShowContainer.get());
        }
    });


}
