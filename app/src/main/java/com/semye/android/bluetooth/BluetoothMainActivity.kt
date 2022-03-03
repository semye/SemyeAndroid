package com.semye.android.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BluetoothMainActivity : AppCompatActivity() {

    private val btnServer: Button? = null
    private val btnClient: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val btnClickListener = View.OnClickListener { v ->
        val i = v.id
        if (i == 1) {
            val serverIntent = Intent(this@BluetoothMainActivity, ServerActivity::class.java)
            startActivity(serverIntent)
        } else if (i == 2) {
            openBluetooth()
        } else {
        }
    }

    fun openBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            Toast.makeText(this@BluetoothMainActivity, "蓝牙适配器为空", Toast.LENGTH_SHORT).show()
        } else {
            if (!bluetoothAdapter.isEnabled) {
//				Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//				// startActivityForResult(enableIntent, 1);
//				startActivity(enableIntent);
                BluetoothTools.openBluetooth()
                val searchIntent = Intent(this@BluetoothMainActivity, ClientActivity::class.java)
                startActivity(searchIntent)
            } else {
                val searchIntent = Intent(this@BluetoothMainActivity, ClientActivity::class.java)
                startActivity(searchIntent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            Toast.makeText(this@BluetoothMainActivity, "成功", Toast.LENGTH_SHORT).show()
        }
    }
}