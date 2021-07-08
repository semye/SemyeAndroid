package com.semye.android.bluetooth;


import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressLint("HandlerLeak")
public class NewBluetoothServerService extends Service {

	private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter
			.getDefaultAdapter();

	private BluetoothServerSocket mServerSocket;

	private BluetoothSocket socket;

	/**
	 * 接收两个线程传来的广播
	 */
	private BroadcastReceiver controlReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (Constants.ACTION_CONNECT_ERROR.equals(action)) {

			} else if (Constants.ACTION_CONNECT_SUCCESS.equals(action)) {

			}
		}

	};

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}

	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {

		// 注册广播
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(Constants.ACTION_CONNECT_ERROR);
		intentFilter.addAction(Constants.ACTION_CONNECT_SUCCESS);
		registerReceiver(controlReceiver, intentFilter);

		super.onCreate();

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand>>>>>>>>>>>>>>>>>开启服务");

		// 开启蓝牙
		bluetoothAdapter.enable();
		// 开启蓝牙发现功能（300秒）
		Intent discoveryIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
				300);
		discoveryIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(discoveryIntent);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new BluetoothThread().start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(controlReceiver);
	}

	/**
	 * @author yesheng
	 * @date 2014年11月18日
	 * @Description 内部类连接线程
	 */
	public class BluetoothThread extends Thread {

		public BluetoothThread() {
			try {
				// MY_UUID is the app's UUID string, also used by the client
				// code
				mServerSocket = bluetoothAdapter
						.listenUsingRfcommWithServiceRecord("Yesheng",
								Constants.MY_UUID_SECURE);
			} catch (IOException e) {
				System.out.println("BluetoothServerSocket>>>>>>>>>>>>>>>报错");
			}

			System.out.println("启动连接线程");
		}

		@Override
		public void run() {
			System.out.println("线程RUN方法运行");
			int i = 1;
			// Keep listening until exception occurs or a socket is returned
			while (true) {
				try {
					System.out.println("第" + i + "次s");
					// 此处开始阻塞线程
					socket = mServerSocket.accept();
					System.out.println("第" + i + "次");
					i++;
				} catch (IOException e) {
					System.out.println("socket接收错误");
					break;
				}
				// If a connection was accepted
				if (socket != null) {
					// Do work to manage the connection (in a separate thread)
					System.out.println("连接成功");
					new ConnectedThread().start();

					try {
						mServerSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}

	}

	/**
	 * @author yesheng
	 * @date 2014年11月18日
	 * @Description 内部类传输线程
	 */
	public class ConnectedThread extends Thread {

		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread() {
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			// Get the input and output streams, using temp objects because
			// member streams are final
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		@Override
		public void run() {
			System.out.println("传输线程开启");
			byte[] buffer = new byte[1024]; // buffer store for the stream
			int bytes; // bytes returned from read()

			// Keep listening to the InputStream until an exception occurs
			while (true) {
				try {
					// Read from the InputStream
					bytes = mmInStream.read(buffer);
					// Send the obtained bytes to the UI activity
					mHandler.obtainMessage(Constants.MESSAGE_READ, bytes, -1,
							buffer).sendToTarget();
				} catch (IOException e) {
					break;
				}
			}
		}

		/* Call this from the main activity to send data to the remote device */
		public void write(byte[] bytes) {
			try {
				mmOutStream.write(bytes);
			} catch (IOException e) {
			}
		}

		/* Call this from the main activity to shutdown the connection */
		public void cancel() {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}

	}

}
