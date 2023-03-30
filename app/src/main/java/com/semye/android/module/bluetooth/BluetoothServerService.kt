package com.semye.android.module.bluetooth

import android.annotation.SuppressLint
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.IBinder
import android.os.Message
import java.io.Serializable


class BluetoothServerService : Service() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    var bluetoothCommunThread: BluetoothCommunThread? = null
        private set

    private val controlReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothTools.ACTION_STOP_SERVICE == action) {
                if (bluetoothCommunThread != null) {
                    bluetoothCommunThread!!.isRun = false
                }
                stopSelf()
            } else if (BluetoothTools.ACTION_DATA_TO_SERVICE == action) {
                val data: Any? = intent.getSerializableExtra(BluetoothTools.DATA)
                if (bluetoothCommunThread != null) {
                    bluetoothCommunThread!!.writeObject(data)
                }
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private val serviceHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                BluetoothTools.MESSAGE_CONNECT_SUCCESS -> {
                    bluetoothCommunThread = BluetoothCommunThread(this, msg.obj as BluetoothSocket)
                    bluetoothCommunThread!!.start()

                    val connSuccIntent = Intent(BluetoothTools.ACTION_CONNECT_SUCCESS)
                    sendBroadcast(connSuccIntent)
                }
                BluetoothTools.MESSAGE_CONNECT_ERROR -> {
                    val errorIntent = Intent(BluetoothTools.ACTION_CONNECT_ERROR)
                    sendBroadcast(errorIntent)
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

    override fun onCreate() {
        //ControlReceiver��IntentFilter
        val controlFilter = IntentFilter()
        controlFilter.addAction(BluetoothTools.ACTION_START_SERVER)
        controlFilter.addAction(BluetoothTools.ACTION_STOP_SERVICE)
        controlFilter.addAction(BluetoothTools.ACTION_DATA_TO_SERVICE)
        registerReceiver(controlReceiver, controlFilter)

        bluetoothAdapter.enable()
        val discoveryIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        discoveryIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(discoveryIntent)
        BluetoothServerConnThread(serviceHandler).start()
        super.onCreate()
    }

    override fun onDestroy() {
        if (bluetoothCommunThread != null) {
            bluetoothCommunThread!!.isRun = false
        }
        unregisterReceiver(controlReceiver)
        super.onDestroy()
    }

    override fun onBind(arg0: Intent): IBinder? {
        return null
    }
}