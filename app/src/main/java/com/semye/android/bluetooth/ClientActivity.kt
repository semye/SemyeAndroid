package com.semye.android.bluetooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.semye.android.bluetooth.ClientActivity
import java.util.*

class ClientActivity : Activity() {
    private val listView: ListView? = null
    private val btnSearch: Button? = null
    private val btnClientSendMsg: Button? = null
    private val tvClientState: TextView? = null
    private val etClientChat: EditText? = null
    private val etClientSend: EditText? = null
    private val bluetoothAdapter: BluetoothAdapter? = null
    private val devices: MutableList<String>? = null
    private val deviceList: MutableList<BluetoothDevice?>? = null
    private val finaldeviceList: MutableList<BluetoothDevice>? = null
    private val baseAdapter: BaseAdapter? = null
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (BluetoothTools.ACTION_NOT_FOUND_SERVER == action) {
                tvClientState!!.append("not found device\r\n")
            } else if (BluetoothTools.ACTION_FOUND_DEVICE == action) {
                val device = intent.extras!![BluetoothTools.DEVICE] as BluetoothDevice?
                deviceList!!.add(device)
                tvClientState!!.append(
                    """
    ${device!!.name}
    
    """.trimIndent()
                )
                //				if (deviceList.size()>0) {
//					for (int i = 0; i < deviceList.size(); i++) {
//						if (device.getName().equals(deviceList.get(i).getName())) {
//							deviceList.add(device);
//							tvClientState.append(device.getName() + "\r\n");
//						}
//					}
//				}
            } else if (BluetoothTools.ACTION_CONNECT_SUCCESS == action) {
                tvClientState!!.append("���ӳɹ�")
                btnClientSendMsg!!.isEnabled = true
            } else if (BluetoothTools.ACTION_DATA_TO_GAME == action) {
                val data = intent.extras?.getSerializable(BluetoothTools.DATA) as MsgBean?
                val msg = """from remote ${Date().toLocaleString()} :${data?.msg}"""
                etClientChat!!.append(msg)
            } else if (BluetoothTools.ACTION_FOUND_SERVER_OVER == action) {
                showDevices()
                Toast.makeText(this@ClientActivity, "������ɣ�", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        deviceList!!.clear()
        val startService = Intent(this@ClientActivity, BluetoothClientService::class.java)
        startService(startService)
        val intentFilter = IntentFilter()
        intentFilter.addAction(BluetoothTools.ACTION_NOT_FOUND_SERVER)
        intentFilter.addAction(BluetoothTools.ACTION_FOUND_DEVICE)
        intentFilter.addAction(BluetoothTools.ACTION_DATA_TO_GAME)
        intentFilter.addAction(BluetoothTools.ACTION_CONNECT_SUCCESS)
        intentFilter.addAction(BluetoothTools.ACTION_FOUND_SERVER_OVER)
        registerReceiver(broadcastReceiver, intentFilter)
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val btnClickListener = View.OnClickListener { v ->
        val i = v.id
        if (i == 1) {
            tvClientState!!.text = ""
            finaldeviceList!!.clear()
            baseAdapter!!.notifyDataSetChanged()
            val startSearchIntent = Intent(BluetoothTools.ACTION_START_DISCOVERY)
            sendBroadcast(startSearchIntent)
        } else if (i == 2) {
            val txt = etClientSend!!.text.toString().trim { it <= ' ' }
            if (txt == "") {
                Toast.makeText(this@ClientActivity, "���벻��Ϊ��", Toast.LENGTH_SHORT).show()
            } else {
                val msgBean = MsgBean()
                msgBean.msg = txt
                val sendDataIntent = Intent(BluetoothTools.ACTION_DATA_TO_SERVICE)
                sendDataIntent.putExtra(BluetoothTools.DATA, msgBean)
                sendBroadcast(sendDataIntent)
            }
        } else {
        }
    }

    override fun onStop() {
        val startService = Intent(BluetoothTools.ACTION_STOP_SERVICE)
        sendBroadcast(startService)
        unregisterReceiver(broadcastReceiver)
        super.onStop()
    }

    private val onItemClickListener = OnItemClickListener { parent, view, position, id ->
        val selectDeviceIntent = Intent(BluetoothTools.ACTION_SELECTED_DEVICE)
        selectDeviceIntent.putExtra(BluetoothTools.DEVICE, finaldeviceList!![position])
        sendBroadcast(selectDeviceIntent)
        //			Toast.makeText(ClientActivity.this, "������������" + finaldeviceList.get(position).getName(),
//					Toast.LENGTH_LONG).show();
//			BluetoothDevice device = deviceList.get(position);
//			Toast.makeText(ClientActivity.this, "������������" + device.getName(),
//					Toast.LENGTH_LONG).show();
        // client = new Bluetooth(device, handler);
        // try {
        // client.connect(message);
        // } catch (Exception e) {
        // Log.e("TAG", e.toString());
        // }
    }

    private fun isLock(device: BluetoothDevice?): Boolean {
        val isLockName = device!!.name == "cmrx"
        val isSingleDevice = devices!!.indexOf(device.name) == -1
        //return isLockName && isSingleDevice;
        return true
    }

    //	private class BluetoothReceiver extends BroadcastReceiver {
    //
    //		@Override
    //		public void onReceive(Context context, Intent intent) {
    //			String action = intent.getAction();
    //			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
    //				BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
    //				if (isLock(device)) {
    //					devices.add(device.getName());
    //				}
    //				deviceList.add(device);
    //			}
    //			showDevices();
    //		}
    //	}
    private fun showDevices() {
        if (deviceList!!.size > 0) {
            for (i in deviceList.indices) {
                if (isLock(deviceList[i])) {
                    finaldeviceList!!.add(deviceList[i]!!)
                    devices!!.add(
                        "��������" + deviceList[i]!!
                            .name + "      ������ַ: " + deviceList[i]!!.address
                    )
                }
            }
            //			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					android.R.layout.simple_list_item_1, devices);
//			listView.setAdapter(adapter);
            baseAdapter!!.notifyDataSetChanged()
        } else {
            Toast.makeText(this@ClientActivity, "û�������������豸��", Toast.LENGTH_SHORT).show()
        }
    }

    internal inner class myadapter : BaseAdapter() {
        override fun getCount(): Int {
            return finaldeviceList!!.size
        }

        override fun getItem(position: Int): Any {
            return finaldeviceList!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        @SuppressLint("NewApi", "DefaultLocale")
        override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
            val device = getItem(position) as BluetoothDevice
            val layout = LinearLayout(this@ClientActivity)

            /*layout.inflate(ProjectList.this,
                    android.R.layout.simple_expandable_list_item_2, null);*/
            val tv = TextView(this@ClientActivity) //���
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30f)
            tv.setPadding(0, 10, 0, 10)
            tv.width = 40
            tv.text = String.format("%d", position + 1)
            tv.gravity = Gravity.CENTER
            layout.addView(tv)
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIB));
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIW));
            val tv2 = TextView(this@ClientActivity) //�������
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30f)
            tv2.setPadding(0, 10, 0, 10)
            tv2.width = 260
            tv2.text = device.name
            //			tv2.setText(map.get("surveyBegin"));
            tv2.gravity = Gravity.CENTER
            layout.addView(tv2)
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIB));
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIW));
            val tv3 = TextView(this@ClientActivity)
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30f)
            tv3.setPadding(0, 10, 0, 10)
            tv3.width = 400
            tv3.gravity = Gravity.CENTER
            tv3.text = device.address
            //			tv3.setText(map.get("surveyPlace"));
            layout.addView(tv3)
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIB));
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIW));
            return layout
        }
    }
}