package com.semye.android.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientService extends Service {

    private List<BluetoothDevice> discoveredDevices = new ArrayList<BluetoothDevice>();

    private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private BluetoothSocket socket;


    Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }

    };


    private BroadcastReceiver discoveryReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                discoveredDevices.add(bluetoothDevice);
                System.out.println("添加蓝牙设备");
                //把bluetoothDevice添加到list中去
                Intent deviceListIntent = new Intent("ACTION_FOUND_DEVICE");
                deviceListIntent.putExtra("DEVICE", bluetoothDevice);
                sendBroadcast(deviceListIntent);
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                if (discoveredDevices.isEmpty()) {
                    System.out.println("未找到合适的蓝牙设备");
                } else {

                    Intent foundIntent = new Intent("ACTION_FOUND_SERVER_OVER");
                    sendBroadcast(foundIntent);
                    System.out.println("蓝牙设备查找完毕");


                }
            }
        }


    };

    private BroadcastReceiver controlReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("ACTION_START_DISCOVERY".equals(action)) {

            } else if ("ACTION_SELECTED_DEVICE".equals(action)) {

                BluetoothDevice device = (BluetoothDevice) intent.getExtras().get("DEVICE");

                new BluetoothClientConnThread(device).start();

            } else if ("ACTION_STOP_SERVICE".equals(action)) {

            } else if ("ACTION_DATA_TO_SERVICE".equals(action)) {

            }
        }

    };


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        IntentFilter discoveryFilter = new IntentFilter();
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        discoveryFilter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(discoveryReceiver, discoveryFilter);

        IntentFilter controlFilter = new IntentFilter();
        controlFilter.addAction("ACTION_START_DISCOVERY");
        controlFilter.addAction("ACTION_SELECTED_DEVICE");
        controlFilter.addAction("ACTION_STOP_SERVICE");
        controlFilter.addAction("ACTION_DATA_TO_SERVICE");
        registerReceiver(controlReceiver, controlFilter);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        discoveredDevices.clear();
        bluetoothAdapter.enable();
        bluetoothAdapter.startDiscovery();
        return super.onStartCommand(intent, flags, startId);
    }

    class BluetoothClientConnThread extends Thread {

        private BluetoothDevice serverDevice;


        public BluetoothClientConnThread(BluetoothDevice device) {
            super();

            this.serverDevice = device;
        }

        @Override
        public void run() {
            System.out.println("开启线程");
            bluetoothAdapter.cancelDiscovery();
            try {
                System.out.println("开启线程");
                socket = serverDevice.createRfcommSocketToServiceRecord(Constants.MY_UUID_SECURE);
                System.out.println("socket接收");
                bluetoothAdapter.cancelDiscovery();
                socket.connect();
            } catch (Exception ex) {
                try {
                    System.out.println("连接失败");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mHandler.obtainMessage(2).sendToTarget();
                return;
            }


            Message msg = mHandler.obtainMessage();
            msg.what = 1;
            msg.obj = socket;
            msg.sendToTarget();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(discoveryReceiver);
        unregisterReceiver(controlReceiver);
    }


}
