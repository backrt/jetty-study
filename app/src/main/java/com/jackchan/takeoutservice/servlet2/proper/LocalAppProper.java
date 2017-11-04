package com.jackchan.takeoutservice.servlet2.proper;

/**
 * Author：lhb on 2017/10/23 16:46
 * Mail：lihaibo@znds.com
 */

public class LocalAppProper {

    private String appname;

    private String packagename;

    private String vername;

    private int vercode;

    private String url;

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPackagename() {
        return packagename;
    }

    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    public String getVername() {
        return vername;
    }

    public void setVername(String vername) {
        this.vername = vername;
    }

    public int getVercode() {
        return vercode;
    }

    public void setVercode(int vercode) {
        this.vercode = vercode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LocalAppProper{" +
                "appname='" + appname + '\'' +
                ", packagename='" + packagename + '\'' +
                ", vername='" + vername + '\'' +
                ", vercode=" + vercode +
                ", url='" + url + '\'' +
                '}';
    }
}
