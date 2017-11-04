package com.jackchan.takeoutservice.bean2;

import java.util.List;

/**
 * Author：lhb on 2017/10/19 16:21
 * Mail：lihaibo@znds.com
 */

public class LocalAppListDataBean extends BaseDataBean {

    private int item_count;

    private List<LocalAppBean> apps;

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }


    public List<LocalAppBean> getApps() {
        return apps;
    }

    public void setApps(List<LocalAppBean> apps) {
        this.apps = apps;
    }

    @Override
    public String toString() {
        return "LocalAppListDataBean{" +
                "item_count=" + item_count +
                ", apps=" + apps +
                '}';
    }
}
