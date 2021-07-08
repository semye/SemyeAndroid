package com.semye.android.bluetooth

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import com.semye.android.bluetooth.ServerActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ServerActivity : Activity() {
    private val tvServerState: TextView? = null
    private val etServerMsg: EditText? = null
    private val etServerSend: EditText? = null
    private val btnServerSendMsg: Button? = null
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothTools.ACTION_DATA_TO_GAME == action) {
                val data = intent.extras!!.getByteArray(
                    BluetoothTools.DATA
                )
                val msg = """from cmrx:${Date().toLocaleString()} :
"""
                val tagerF = File(
                    Environment
                        .getExternalStorageDirectory().absolutePath
                            + File.separator + "aaa.db"
                )
                if (tagerF.exists()) {
                    tagerF.delete()
                }
                try {
                    tagerF.createNewFile()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }
                try {
                    val fout = FileOutputStream(
                        tagerF
                    )
                    fout.write(data)
                    fout.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                etServerMsg!!.append(msg)
            } else if (BluetoothTools.ACTION_CONNECT_SUCCESS == action) {
                tvServerState!!.text = "���ӳɹ�"
                btnServerSendMsg!!.isEnabled = true
            }
        }
    }

    override fun onStart() {
        val startService = Intent(
            this@ServerActivity,
            BluetoothServerService::class.java
        )
        startService(startService)
        val intentFilter = IntentFilter()
        intentFilter.addAction(BluetoothTools.ACTION_DATA_TO_GAME)
        intentFilter.addAction(BluetoothTools.ACTION_CONNECT_SUCCESS)
        registerReceiver(broadcastReceiver, intentFilter)
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStop() {
        val startService = Intent(BluetoothTools.ACTION_STOP_SERVICE)
        sendBroadcast(startService)
        unregisterReceiver(broadcastReceiver)
        super.onStop()
    }

    private val btnClickListener = View.OnClickListener {
        if (etServerSend!!.text.toString().trim { it <= ' ' } == "") {
            Toast.makeText(
                this@ServerActivity, "���벻��Ϊ��",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // ������Ϣ
            val data = MsgBean()
            data.msg = etServerSend.text.toString()
            val sendDataIntent = Intent(
                BluetoothTools.ACTION_DATA_TO_SERVICE
            )
            sendDataIntent.putExtra(BluetoothTools.DATA, data)
            sendBroadcast(sendDataIntent)
        }
    }
}