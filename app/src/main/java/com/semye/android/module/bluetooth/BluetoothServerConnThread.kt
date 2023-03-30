package com.semye.android.module.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.os.Handler


class BluetoothServerConnThread(
    private val serviceHandler: Handler
) : Thread() {
    private val adapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var socket: BluetoothSocket? = null
    private var serverSocket: BluetoothServerSocket? = null
    override fun run() {
        try {
            serverSocket =
                adapter.listenUsingRfcommWithServiceRecord("Server", BluetoothTools.PRIVATE_UUID)
            socket = serverSocket?.accept()
        } catch (e: Exception) {

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
            val msg = serviceHandler.obtainMessage()
            msg.what = BluetoothTools.MESSAGE_CONNECT_SUCCESS
            msg.obj = socket
            msg.sendToTarget()
        } else {
            serviceHandler.obtainMessage(BluetoothTools.MESSAGE_CONNECT_ERROR).sendToTarget()
            return
        }
    }

}