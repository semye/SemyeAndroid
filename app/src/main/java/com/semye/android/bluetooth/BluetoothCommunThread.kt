package com.semye.android.bluetooth

import android.bluetooth.BluetoothSocket
import android.os.*
import java.io.BufferedInputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

//package com.semye.android.module.bluetooth;
class BluetoothCommunThread(
    private val serviceHandler: Handler, socket: BluetoothSocket
) : Thread() {
    private val socket: BluetoothSocket?
    private var inStream //����������
            : ObjectInputStream? = null
    private var outStream //���������
            : ObjectOutputStream? = null

    @Volatile
    var isRun = true //���б�־λ
    override fun run() {
        while (true) {
            if (!isRun) {
                break
            }
            try {
                val obj = inStream!!.readObject()
                val msg = serviceHandler.obtainMessage()
                msg.what = BluetoothTools.MESSAGE_READ_OBJECT
                msg.obj = obj
                msg.sendToTarget()
            } catch (ex: Exception) {
                serviceHandler.obtainMessage(BluetoothTools.MESSAGE_CONNECT_ERROR).sendToTarget()
                ex.printStackTrace()
                return
            }
        }

        if (inStream != null) {
            try {
                inStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (outStream != null) {
            try {
                outStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (socket != null) {
            try {
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun writeObject(obj: Any?) {
        try {
            outStream!!.flush()
            outStream?.writeObject(obj)
            outStream?.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    init {
        this.socket = socket
        try {
            outStream = ObjectOutputStream(socket.outputStream)
            inStream = ObjectInputStream(BufferedInputStream(socket.inputStream))
        } catch (e: Exception) {
            try {
                socket.close()
            } catch (e1: IOException) {
                e1.printStackTrace()
            }
            serviceHandler.obtainMessage(BluetoothTools.MESSAGE_CONNECT_ERROR).sendToTarget()
            e.printStackTrace()
        }
    }
}