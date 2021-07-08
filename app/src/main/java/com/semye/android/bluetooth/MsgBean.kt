package com.semye.android.bluetooth;


import java.io.Serializable;

public class MsgBean implements Serializable {
    private String msg = "";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
