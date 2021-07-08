package com.semye.android.bluetooth

import android.annotation.SuppressLint
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.IBinder
import android.os.Message
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

@SuppressLint("HandlerLeak")
class NewBluetoothServerService : Service() {
    private val bluetoothAdapter = BluetoothAdapter
        .getDefaultAdapter()
    private var mServerSocket: BluetoothServerSocket? = null
    private var socket: BluetoothSocket? = null

    /**
     * 接收两个线程传来的广播
     */
    private val controlReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (Constants.ACTION_CONNECT_ERROR == action) {
            } else if (Constants.ACTION_CONNECT_SUCCESS == action) {
            }
        }
    }
    var mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {

        // 注册广播
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.ACTION_CONNECT_ERROR)
        intentFilter.addAction(Constants.ACTION_CONNECT_SUCCESS)
        registerReceiver(controlReceiver, intentFilter)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        println("onStartCommand>>>>>>>>>>>>>>>>>开启服务")

        // 开启蓝牙
        bluetoothAdapter.enable()
        // 开启蓝牙发现功能（300秒）
        val discoveryIntent = Intent(
            BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE
        )
        discoveryIntent.putExtra(
            BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
            300
        )
        discoveryIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(discoveryIntent)
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        BluetoothThread().start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(controlReceiver)
    }

    /**
     * @author yesheng
     * @date 2014年11月18日
     * @Description 内部类连接线程
     */
    inner class BluetoothThread : Thread() {
        override fun run() {
            println("线程RUN方法运行")
            var i = 1
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    println("第" + i + "次s")
                    // 此处开始阻塞线程
                    socket = mServerSocket!!.accept()
                    println("第" + i + "次")
                    i++
                } catch (e: IOException) {
                    println("socket接收错误")
                    break
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    println("连接成功")
                    ConnectedThread().start()
                    try {
                        mServerSocket!!.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    break
                }
            }
        }

        init {
            try {
                // MY_UUID is the app's UUID string, also used by the client
                // code
                mServerSocket = bluetoothAdapter
                    .listenUsingRfcommWithServiceRecord(
                        "Yesheng",
                        Constants.MY_UUID_SECURE
                    )
            } catch (e: IOException) {
                println("BluetoothServerSocket>>>>>>>>>>>>>>>报错")
            }
            println("启动连接线程")
        }
    }

    /**
     * @author yesheng
     * @date 2014年11月18日
     * @Description 内部类传输线程
     */
    inner class ConnectedThread : Thread() {
        private val mmInStream: InputStream?
        private val mmOutStream: OutputStream?
        override fun run() {
            println("传输线程开启")
            val buffer = ByteArray(1024) // buffer store for the stream
            var bytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream!!.read(buffer)
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(
                        Constants.MESSAGE_READ, bytes, -1,
                        buffer
                    ).sendToTarget()
                } catch (e: IOException) {
                    break
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        fun write(bytes: ByteArray?) {
            try {
                mmOutStream!!.write(bytes)
            } catch (e: IOException) {
            }
        }

        /* Call this from the main activity to shutdown the connection */
        fun cancel() {
            try {
                socket!!.close()
            } catch (e: IOException) {
            }
        }

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket!!.inputStream
                tmpOut = socket!!.outputStream
            } catch (e: IOException) {
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }
    }
}