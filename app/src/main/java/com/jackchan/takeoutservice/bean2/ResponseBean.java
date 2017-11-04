package com.jackchan.takeoutservice.bean2;

import java.io.Serializable;

/**
 * Author：lhb on 2017/10/19 15:50
 * Mail：lihaibo@znds.com
 */

public class ResponseBean implements Serializable {

    private int status_code;
    private String msg;
    private BaseDataBean data;

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseDataBean getData() {
        return data;
    }

    public void setData(BaseDataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "status_code=" + status_code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
