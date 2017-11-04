package com.jackchan.takeoutservice.bean2;

import java.io.Serializable;

/**
 * Author：lhb on 2017/10/19 15:54
 * Mail：lihaibo@znds.com
 */

public class LocalAppBean implements Serializable {

    private String packagename;

    private String label;

    private String size;

    private String describe;

    private String url;

    private int vercode;

    private String vername;

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getVercode() {
        return vercode;
    }

    public void setVercode(int vercode) {
        this.vercode = vercode;
    }

    public String getVername() {
        return vername;
    }

    public void setVername(String vername) {
        this.vername = vername;
    }

    @Override
    public String toString() {
        return "LocalAppBean{" +
                "packagename='" + packagename + '\'' +
                ", label='" + label + '\'' +
                ", size='" + size + '\'' +
                ", describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                ", vercode=" + vercode +
                ", vername='" + vername + '\'' +
                '}';
    }
}
