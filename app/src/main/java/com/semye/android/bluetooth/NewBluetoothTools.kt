package com.semye.android.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import java.util.*

/**
 * 蓝牙工具类
 * @author GuoDong
 */
object NewBluetoothTools {
    private val adapter = BluetoothAdapter.getDefaultAdapter()

    /**
     * 本程序所使用的UUID
     */
    val PRIVATE_UUID = UUID.fromString("0f3561b9-bda5-4672-84ff-ab1f98e349b6")

    /**
     * 字符串常量，存放在Intent中的设备对象
     */
    const val DEVICE = "DEVICE"

    /**
     * 字符串常量，服务器所在设备列表中的位置
     */
    const val SERVER_INDEX = "SERVER_INDEX"

    /**
     * 字符串常量，Intent中的数据
     */
    const val DATA = "DATA"

    /**
     * Action类型标识符，Action类型 为读到数据
     */
    const val ACTION_READ_DATA = "ACTION_READ_DATA"

    /**
     * Action类型标识符，Action类型为 未发现设备
     */
    const val ACTION_NOT_FOUND_SERVER = "ACTION_NOT_FOUND_DEVICE"

    /**
     * Action类型标识符，Action类型为 发现设备完成
     */
    const val ACTION_FOUND_SERVER_OVER = "ACTION_FOUND_SERVER_OVER"

    /**
     * Action类型标识符，Action类型为 开始搜索设备
     */
    const val ACTION_START_DISCOVERY = "ACTION_START_DISCOVERY"

    /**
     * Action：设备列表
     */
    const val ACTION_FOUND_DEVICE = "ACTION_FOUND_DEVICE"
    const val ACTION_STOP_BLUETOOTH = "ACTION_STOP_BLUETOOTH"

    /**
     * Action：选择的用于连接的设备
     */
    const val ACTION_SELECTED_DEVICE = "ACTION_SELECTED_DEVICE"

    /**
     * Action：开启服务器
     */
    const val ACTION_START_SERVER = "ACTION_STARRT_SERVER"

    /**
     * Action：关闭后台Service
     */
    const val ACTION_STOP_SERVICE = "ACTION_STOP_SERVICE"

    /**
     * Action：到Service的数据
     */
    const val ACTION_DATA_TO_SERVICE = "ACTION_DATA_TO_SERVICE"

    /**
     * Action：到游戏业务中的数据
     */
    const val ACTION_DATA_TO_GAME = "ACTION_DATA_TO_GAME"

    /**
     * Action：连接成功
     */
    const val ACTION_CONNECT_SUCCESS = "ACTION_CONNECT_SUCCESS"

    /**
     * Action：连接错误
     */
    const val ACTION_CONNECT_ERROR = "ACTION_CONNECT_ERROR"

    /**
     * Message类型标识符，连接成功
     */
    const val MESSAGE_CONNECT_SUCCESS = 0x00000002

    /**
     * Message：连接失败
     */
    const val MESSAGE_CONNECT_ERROR = 0x00000003

    /**
     * Message：读取到一个对象
     */
    const val MESSAGE_READ_OBJECT = 0x00000004

    /**
     * 打开蓝牙功能
     */
    fun openBluetooth() {
        adapter.enable()
    }

    /**
     * 关闭蓝牙功能
     */
    fun closeBluetooth() {
        adapter.disable()
    }

    /**
     * 设置蓝牙发现功能
     * @param duration 设置蓝牙发现功能打开持续秒数（值为0至300之间的整数）
     */
    fun openDiscovery(duration: Int) {
        var duration = duration
        if (duration <= 0 || duration > 300) {
            duration = 200
        }
        val intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, duration)
    }

    /**
     * 停止蓝牙搜索
     */
    fun stopDiscovery() {
        adapter.cancelDiscovery()
    }
}