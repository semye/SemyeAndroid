package com.semye.android.retrofit;

/**
 * Created by yesheng on 2017/9/15.
 * base http response
 */
public class BaseHttpResponse<T> {

    private boolean success;
    private T data;
    private String msg;
    private String errorCode;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
