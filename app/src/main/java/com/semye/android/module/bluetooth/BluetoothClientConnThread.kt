package com.semye.android.module.bluetooth

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.*
import androidx.core.content.ContextCompat
import com.semye.android.SemyeApplication
import java.io.IOException


class BluetoothClientConnThread(
    private val serviceHandler: Handler,
    private val serverDevice: BluetoothDevice?
) : Thread() {
    private var socket: BluetoothSocket? = null
    override fun run() {

        try {
            if (ContextCompat.checkSelfPermission(
                    SemyeApplication.application,
                    Manifest.permission.BLUETOOTH_SCAN
                ) ==
                android.content.pm.PackageManager.PERMISSION_GRANTED
            ) {
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                socket =
                    serverDevice!!.createRfcommSocketToServiceRecord(BluetoothTools.PRIVATE_UUID)
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
                socket?.connect()
            }
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