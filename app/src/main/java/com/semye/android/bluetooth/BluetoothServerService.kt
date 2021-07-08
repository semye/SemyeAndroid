package com.semye.android.bluetooth

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

/**
 * ����ģ���������������Service
 * @author GuoDong
 */
class BluetoothServerService : Service() {
    //����������
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    /**
     * ��ȡͨѶ�߳�
     * @return
     */
    //����ͨѶ�߳�
    var bluetoothCommunThread: BluetoothCommunThread? = null
        private set

    //������Ϣ�㲥������
    private val controlReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothTools.ACTION_STOP_SERVICE == action) {
                //ֹͣ��̨����
                if (bluetoothCommunThread != null) {
                    bluetoothCommunThread!!.isRun = false
                }
                stopSelf()
            } else if (BluetoothTools.ACTION_DATA_TO_SERVICE == action) {
                //��������
                val data: Any? = intent.getSerializableExtra(BluetoothTools.DATA)
                if (bluetoothCommunThread != null) {
                    bluetoothCommunThread!!.writeObject(data)
                }
            }
        }
    }

    //���������߳���Ϣ��Handler
    private val serviceHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                BluetoothTools.MESSAGE_CONNECT_SUCCESS -> {
                    //���ӳɹ�
                    //����ͨѶ�߳�
                    bluetoothCommunThread = BluetoothCommunThread(this, msg.obj as BluetoothSocket)
                    bluetoothCommunThread!!.start()

                    //�������ӳɹ���Ϣ
                    val connSuccIntent = Intent(BluetoothTools.ACTION_CONNECT_SUCCESS)
                    sendBroadcast(connSuccIntent)
                }
                BluetoothTools.MESSAGE_CONNECT_ERROR -> {
                    //���Ӵ���
                    //�������Ӵ���㲥
                    val errorIntent = Intent(BluetoothTools.ACTION_CONNECT_ERROR)
                    sendBroadcast(errorIntent)
                }
                BluetoothTools.MESSAGE_READ_OBJECT -> {
                    //��ȡ������
                    //�������ݹ㲥���������ݶ���
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

        //ע��BroadcastReceiver
        registerReceiver(controlReceiver, controlFilter)

        //����������
        bluetoothAdapter.enable() //������
        //�����������ֹ��ܣ�300�룩
        val discoveryIntent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        discoveryIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(discoveryIntent)
        //������̨�����߳�
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