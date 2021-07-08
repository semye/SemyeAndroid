package com.semye.android.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.os.*

/**
 * �����������߳�
 * @author GuoDong
 */
class BluetoothServerConnThread(  //����ͬServiceͨ�ŵ�Handler
    private val serviceHandler: Handler
) : Thread() {
    private val adapter: BluetoothAdapter
    private var socket //����ͨ�ŵ�Socket
            : BluetoothSocket? = null
    private var serverSocket: BluetoothServerSocket? = null
    override fun run() {
        try {
            serverSocket =
                adapter.listenUsingRfcommWithServiceRecord("Server", BluetoothTools.PRIVATE_UUID)
            socket = serverSocket.accept()
        } catch (e: Exception) {
            //��������ʧ����Ϣ
            serviceHandler.obtainMessage(BluetoothTools.MESSAGE_CONNECT_ERROR).sendToTarget()
            e.printStackTrace()
            return
        } finally {
            try {
                serverSocket!!.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (socket != null) {
            //�������ӳɹ���Ϣ����Ϣ��obj�ֶ�Ϊ���ӵ�socket
            val msg = serviceHandler.obtainMessage()
            msg.what = BluetoothTools.MESSAGE_CONNECT_SUCCESS
            msg.obj = socket
            msg.sendToTarget()
        } else {
            //��������ʧ����Ϣ
            serviceHandler.obtainMessage(BluetoothTools.MESSAGE_CONNECT_ERROR).sendToTarget()
            return
        }
    }

    /**
     * ���캯��
     * @param handler
     */
    init {
        adapter = BluetoothAdapter.getDefaultAdapter()
    }
}