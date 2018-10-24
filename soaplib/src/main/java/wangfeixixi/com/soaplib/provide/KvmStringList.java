package wangfeixixi.com.soaplib.provide;

import org.ksoap2.serialization.PropertyInfo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class KvmStringList extends KvmListBase<String> {

    public KvmStringList(String nameSpace, List<String> list) {
        super(nameSpace, list);
    }

    public KvmStringList(List<String> list) {
        this("", list);
    }

    public KvmStringList() {
        this(new ArrayList<String>());
    }

    @Override
    public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
        arg2.namespace = this.nameSpace;
        arg2.name = "String";
    }

    public void setProperty(String val) {
        setProperty(0, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (int i = 0; i < this.list.size(); i++) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(", ");
            }
            sb.append(this.list.get(i));
        }
        return sb.toString();
    }
}
