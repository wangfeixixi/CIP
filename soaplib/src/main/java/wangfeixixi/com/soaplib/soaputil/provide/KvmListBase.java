package wangfeixixi.com.soaplib.soaputil.provide;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;
import java.util.List;

public abstract class KvmListBase<T> implements KvmSerializable {

    List<T> list;
    String nameSpace;


    public KvmListBase(String nameSpace, List<T> list) {
        this.nameSpace = nameSpace;
        this.list = list;
    }

    @Override
    public Object getProperty(int arg0) {
        return list.get(arg0);
    }

    @Override
    public int getPropertyCount() {
        return list.size();
    }

    @Override
    public abstract void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2);


    @Override
    public void setProperty(int arg0, Object arg1) {
        list.add((T) arg1);
    }
}