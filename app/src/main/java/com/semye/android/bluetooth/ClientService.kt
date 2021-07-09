package com.semye.android.bluetooth

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import java.io.IOException
import java.util.*

class ClientService : Service() {
    private val discoveredDevices: MutableList<BluetoothDevice?> = ArrayList()
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private var socket: BluetoothSocket? = null
    var mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }
    private val discoveryReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED == action) {
            } else if (BluetoothDevice.ACTION_FOUND == action) {
                val bluetoothDevice =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                discoveredDevices.add(bluetoothDevice)
                println("添加蓝牙设备")
                //把bluetoothDevice添加到list中去
                val deviceListIntent = Intent("ACTION_FOUND_DEVICE")
                deviceListIntent.putExtra("DEVICE", bluetoothDevice)
                sendBroadcast(deviceListIntent)
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                if (discoveredDevices.isEmpty()) {
                    println("未找到合适的蓝牙设备")
                } else {
                    val foundIntent = Intent("ACTION_FOUND_SERVER_OVER")
                    sendBroadcast(foundIntent)
                    println("蓝牙设备查找完毕")
                }
            }
        }
    }
    private val controlReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if ("ACTION_START_DISCOVERY" == action) {
            } else if ("ACTION_SELECTED_DEVICE" == action) {
                val device = intent.extras!!["DEVICE"] as BluetoothDevice
                BluetoothClientConnThread(device).start()
            } else if ("ACTION_STOP_SERVICE" == action) {
            } else if ("ACTION_DATA_TO_SERVICE" == action) {
            }
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        val discoveryFilter = IntentFilter()
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        discoveryFilter.addAction(BluetoothDevice.ACTION_FOUND)
        registerReceiver(discoveryReceiver, discoveryFilter)
        val controlFilter = IntentFilter()
        controlFilter.addAction("ACTION_START_DISCOVERY")
        controlFilter.addAction("ACTION_SELECTED_DEVICE")
        controlFilter.addAction("ACTION_STOP_SERVICE")
        controlFilter.addAction("ACTION_DATA_TO_SERVICE")
        registerReceiver(controlReceiver, controlFilter)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        discoveredDevices.clear()
        bluetoothAdapter.enable()
        bluetoothAdapter.startDiscovery()
        return super.onStartCommand(intent, flags, startId)
    }

    internal inner class BluetoothClientConnThread(private val serverDevice: BluetoothDevice) :
        Thread() {
        override fun run() {
            println("开启线程")
            bluetoothAdapter.cancelDiscovery()
            try {
                println("开启线程")
                socket = serverDevice.createRfcommSocketToServiceRecord(Constants.MY_UUID_SECURE)
                println("socket接收")
                bluetoothAdapter.cancelDiscovery()
                socket?.connect()
            } catch (ex: Exception) {
                try {
                    println("连接失败")
                    socket!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                mHandler.obtainMessage(2).sendToTarget()
                return
            }
            val msg = mHandler.obtainMessage()
            msg.what = 1
            msg.obj = socket
            msg.sendToTarget()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(discoveryReceiver)
        unregisterReceiver(controlReceiver)
    }
}