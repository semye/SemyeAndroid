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
import java.io.Serializable
import java.util.*

/**
 *
 */
class BluetoothClientService : Service() {
    private val discoveredDevices: MutableList<BluetoothDevice?> = ArrayList()
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var bluetoothCommunThread: BluetoothCommunThread? = null
        private set
    private val controlReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothTools.ACTION_START_DISCOVERY == action) {
                discoveredDevices.clear()
                bluetoothAdapter.enable()
                bluetoothAdapter.startDiscovery()
            } else if (BluetoothTools.ACTION_SELECTED_DEVICE == action) {
                val device = intent.extras!![BluetoothTools.DEVICE] as BluetoothDevice?
                BluetoothClientConnThread(handler, device).start()
            } else if (BluetoothTools.ACTION_STOP_SERVICE == action) {
                if (bluetoothCommunThread != null) {
                    bluetoothCommunThread!!.isRun = false
                }
                stopSelf()
            } else if (BluetoothTools.ACTION_DATA_TO_SERVICE == action) {
                val data: Any? = intent.getByteArrayExtra(BluetoothTools.DATA)
                if (bluetoothCommunThread != null) {
                    bluetoothCommunThread!!.writeObject(data)
                }
            }
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
                val deviceListIntent = Intent(BluetoothTools.ACTION_FOUND_DEVICE)
                deviceListIntent.putExtra(BluetoothTools.DEVICE, bluetoothDevice)
                sendBroadcast(deviceListIntent)
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                if (discoveredDevices.isEmpty()) {
                    val foundIntent = Intent(BluetoothTools.ACTION_NOT_FOUND_SERVER)
                    sendBroadcast(foundIntent)
                } else {
                    val foundIntent = Intent(BluetoothTools.ACTION_FOUND_SERVER_OVER)
                    sendBroadcast(foundIntent)
                }
            }
        }
    }
    var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                BluetoothTools.MESSAGE_CONNECT_ERROR -> {
                    val errorIntent = Intent(BluetoothTools.ACTION_CONNECT_ERROR)
                    sendBroadcast(errorIntent)
                }
                BluetoothTools.MESSAGE_CONNECT_SUCCESS -> {
                    bluetoothCommunThread = BluetoothCommunThread(this, msg.obj as BluetoothSocket)
                    bluetoothCommunThread!!.start()
                    val succIntent = Intent(BluetoothTools.ACTION_CONNECT_SUCCESS)
                    sendBroadcast(succIntent)
                }
                BluetoothTools.MESSAGE_READ_OBJECT -> {
                    val dataIntent = Intent(BluetoothTools.ACTION_DATA_TO_GAME)
                    dataIntent.putExtra(BluetoothTools.DATA, msg.obj as Serializable)
                    sendBroadcast(dataIntent)
                }
            }
            super.handleMessage(msg)
        }
    }

    override fun onStart(intent: Intent, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        val discoveryFilter = IntentFilter()
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        discoveryFilter.addAction(BluetoothDevice.ACTION_FOUND)
        val controlFilter = IntentFilter()
        controlFilter.addAction(BluetoothTools.ACTION_START_DISCOVERY)
        controlFilter.addAction(BluetoothTools.ACTION_SELECTED_DEVICE)
        controlFilter.addAction(BluetoothTools.ACTION_STOP_SERVICE)
        controlFilter.addAction(BluetoothTools.ACTION_DATA_TO_SERVICE)
        registerReceiver(discoveryReceiver, discoveryFilter)
        registerReceiver(controlReceiver, controlFilter)
        super.onCreate()
    }

    override fun onDestroy() {
        if (bluetoothCommunThread != null) {
            bluetoothCommunThread!!.isRun = false
        }
        unregisterReceiver(discoveryReceiver)
        super.onDestroy()
    }
}