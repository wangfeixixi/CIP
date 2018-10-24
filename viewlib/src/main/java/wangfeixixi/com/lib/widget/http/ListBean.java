package wangfeixixi.com.lib.widget.http;

import java.util.ArrayList;

public class ListBean<T> {
    public boolean haveNextPage;
    public boolean havePrePage;
    public int pageIndex;
    public int pageSize;
    public int totalPages;
    public int totalRecords;
    public ArrayList<T> datas;
}
