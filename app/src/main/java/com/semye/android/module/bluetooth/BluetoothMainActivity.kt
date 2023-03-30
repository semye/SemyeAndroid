package com.semye.android.module.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.semye.android.R

class BluetoothMainActivity : AppCompatActivity() {

    private var openBtn: Button? = null
    private var closeBtn: Button? = null
    private var scanBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        val bo = MyBo()
        val infl = IntentFilter()
        infl.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        infl.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        infl.addAction(BluetoothDevice.ACTION_FOUND)
        infl.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        registerReceiver(bo, infl)
        openBtn = findViewById(R.id.open)
        closeBtn = findViewById(R.id.close)
        scanBtn = findViewById(R.id.scan)
        openBtn?.setOnClickListener {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val result = bluetoothAdapter.enable()
            Log.i("yesheng", "open :$result")
        }
        closeBtn?.setOnClickListener {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val result = bluetoothAdapter.disable()
            Log.i("yesheng", "disable :$result")
        }
        scanBtn?.setOnClickListener {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val result = bluetoothAdapter.startDiscovery()//扫描12秒,需要定位权限
            Log.i("yesheng", "scan :$result")
        }
    }

    class MyBo : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Log.i("yesheng", "开始扫描蓝牙设备")
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Log.i("yesheng", "蓝牙设备扫描完成")
                }
                BluetoothDevice.ACTION_FOUND -> {

                    val device =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) as? BluetoothDevice
                    Log.i(
                        "yesheng",
                        "蓝牙设备列表:" + "mac地址:" + device?.address + ",设备名称:" + device?.name + ",设备别名:" + device?.alias + " " + device?.type
                    )
                    val name = intent.getStringExtra(BluetoothDevice.EXTRA_NAME)
                    Log.i("yesheng", "蓝牙名称:" + name)
                    val cla =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS) as? BluetoothClass
                    Log.i("yesheng", "蓝牙class" + cla.toString())

                    val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, 0)
                    Log.i("yesheng", "rssi:" + rssi)

                    device?.createBond()

                }

            }
        }

    }
}