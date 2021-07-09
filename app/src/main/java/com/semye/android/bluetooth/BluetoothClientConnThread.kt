package com.semye.android.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.*
import java.io.IOException

/**
 * @param handler
 * @param serverDevice
 */
class BluetoothClientConnThread(
    private val serviceHandler: Handler,
    private val serverDevice: BluetoothDevice?
) : Thread() {
    private var socket: BluetoothSocket? = null
    override fun run() {
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
        try {
            socket = serverDevice!!.createRfcommSocketToServiceRecord(BluetoothTools.PRIVATE_UUID)
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
            socket?.connect()
        } catch (ex: Exception) {
            try {
                socket!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            serviceHandler.obtainMessage(BluetoothTools.MESSAGE_CONNECT_ERROR).sendToTarget()
            return
        }
        val msg = serviceHandler.obtainMessage()
        msg.what = BluetoothTools.MESSAGE_CONNECT_SUCCESS
        msg.obj = socket
        msg.sendToTarget()
    }
}