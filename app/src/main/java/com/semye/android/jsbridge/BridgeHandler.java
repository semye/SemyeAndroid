package com.semye.android.jsbridge;

/**
 * bridge handler 接口
 */
public interface BridgeHandler {

    /**
     * 处理数据
     *
     * @param data     数据
     * @param function 回调函数
     */
    void handler(String data, CallBackFunction function);

}
