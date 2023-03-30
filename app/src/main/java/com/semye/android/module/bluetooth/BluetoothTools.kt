package com.semye.android.module.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import java.util.*

object BluetoothTools {
    private val adapter = BluetoothAdapter.getDefaultAdapter()
    val PRIVATE_UUID = UUID.fromString("0f3561b9-bda5-4672-84ff-ab1f98e349b6")
    const val DEVICE = "DEVICE"
    const val SERVER_INDEX = "SERVER_INDEX"
    const val DATA = "DATA"
    const val ACTION_READ_DATA = "ACTION_READ_DATA"
    const val ACTION_NOT_FOUND_SERVER = "ACTION_NOT_FOUND_DEVICE"
    const val ACTION_FOUND_SERVER_OVER = "ACTION_FOUND_SERVER_OVER"
    const val ACTION_START_DISCOVERY = "ACTION_START_DISCOVERY"
    const val ACTION_FOUND_DEVICE = "ACTION_FOUND_DEVICE"
    const val ACTION_SELECTED_DEVICE = "ACTION_SELECTED_DEVICE"
    const val ACTION_START_SERVER = "ACTION_STARRT_SERVER"
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"
    const val ACTION_DATA_TO_SERVICE = "ACTION_DATA_TO_SERVICE"
    const val ACTION_DATA_TO_GAME = "ACTION_DATA_TO_GAME"
    const val ACTION_CONNECT_SUCCESS = "ACTION_CONNECT_SUCCESS"
    const val ACTION_CONNECT_ERROR = "ACTION_CONNECT_ERROR"
    const val MESSAGE_CONNECT_SUCCESS = 0x00000002
    const val MESSAGE_CONNECT_ERROR = 0x00000003
    const val MESSAGE_READ_OBJECT = 0x00000004


    fun openDiscovery(duration: Int) {
        var duration = duration
        if (duration <= 0 || duration > 300) {
            duration = 200
        }
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, duration)
    }

    fun stopDiscovery() {
        adapter.cancelDiscovery()
    }
}