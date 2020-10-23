package com.semye.android.bluetooth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import android.os.Message;

/**
 * ����ģ��ͻ���������Service
 * @author GuoDong
 *
 */
public class BluetoothClientService extends Service {

	//��������Զ���豸����
	private List<BluetoothDevice> discoveredDevices = new ArrayList<BluetoothDevice>();

	//����������
	private final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	//����ͨѶ�߳�
	private BluetoothCommunThread communThread;

	//������Ϣ�㲥�Ľ�����
	private BroadcastReceiver controlReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();

			if (BluetoothTools.ACTION_START_DISCOVERY.equals(action)) {
				//��ʼ����
				discoveredDevices.clear();	//��մ���豸�ļ���
				bluetoothAdapter.enable();	//������
				bluetoothAdapter.startDiscovery();	//��ʼ����

			} else if (BluetoothTools.ACTION_SELECTED_DEVICE.equals(action)) {
				//ѡ�������ӵķ������豸
				BluetoothDevice device = (BluetoothDevice)intent.getExtras().get(BluetoothTools.DEVICE);

				//�����豸�����߳�
				new BluetoothClientConnThread(handler, device).start();

			} else if (BluetoothTools.ACTION_STOP_SERVICE.equals(action)) {
				//ֹͣ��̨����
				if (communThread != null) {
					communThread.isRun = false;
				}
				stopSelf();

			} else if (BluetoothTools.ACTION_DATA_TO_SERVICE.equals(action)) {
				//��ȡ����
				Object data = intent.getByteArrayExtra(BluetoothTools.DATA);
				if (communThread != null) {
					communThread.writeObject(data);
				}

			}
		}
	};

	//���������㲥�Ľ�����
	private BroadcastReceiver discoveryReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			//��ȡ�㲥��Action
			String action = intent.getAction();

			if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
				//��ʼ����
			} else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				//����Զ�������豸
				//��ȡ�豸
				BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				discoveredDevices.add(bluetoothDevice);

				//���ͷ����豸�㲥
				Intent deviceListIntent = new Intent(BluetoothTools.ACTION_FOUND_DEVICE);
				deviceListIntent.putExtra(BluetoothTools.DEVICE, bluetoothDevice);
				sendBroadcast(deviceListIntent);

			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
				//��������
				if (discoveredDevices.isEmpty()) {
					//��δ�ҵ��豸���򷢶�δ�����豸�㲥
					Intent foundIntent = new Intent(BluetoothTools.ACTION_NOT_FOUND_SERVER);
					sendBroadcast(foundIntent);
				}else {
					Intent foundIntent = new Intent(BluetoothTools.ACTION_FOUND_SERVER_OVER);
					sendBroadcast(foundIntent);
				}
			}
		}
	};

	//���������߳���Ϣ��Handler
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			//������Ϣ
			switch (msg.what) {
			case BluetoothTools.MESSAGE_CONNECT_ERROR:
				//���Ӵ���
				//�������Ӵ���㲥
				Intent errorIntent = new Intent(BluetoothTools.ACTION_CONNECT_ERROR);
				sendBroadcast(errorIntent);
				break;
			case BluetoothTools.MESSAGE_CONNECT_SUCCESS:
				//���ӳɹ�

				//����ͨѶ�߳�
				communThread = new BluetoothCommunThread(handler, (BluetoothSocket)msg.obj);
				communThread.start();

				//�������ӳɹ��㲥
				Intent succIntent = new Intent(BluetoothTools.ACTION_CONNECT_SUCCESS);
				sendBroadcast(succIntent);
				break;
			case BluetoothTools.MESSAGE_READ_OBJECT:
				//��ȡ������
				//�������ݹ㲥���������ݶ���
				Intent dataIntent = new Intent(BluetoothTools.ACTION_DATA_TO_GAME);
				dataIntent.putExtra(BluetoothTools.DATA, (Serializable)msg.obj);
				sendBroadcast(dataIntent);
				break;
			}
			super.handleMessage(msg);
		}

	};

	/**
	 * ��ȡͨѶ�߳�
	 * @return
	 */
	public BluetoothCommunThread getBluetoothCommunThread() {
		return communThread;
	}

	@Override
	public void onStart(Intent intent, int startId) {

		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	/**
	 * Service����ʱ�Ļص�����
	 */
	@Override
	public void onCreate() {
		//discoveryReceiver��IntentFilter
		IntentFilter discoveryFilter = new IntentFilter();
		discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
		discoveryFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		discoveryFilter.addAction(BluetoothDevice.ACTION_FOUND);

		//controlReceiver��IntentFilter
		IntentFilter controlFilter = new IntentFilter();
		controlFilter.addAction(BluetoothTools.ACTION_START_DISCOVERY);
		controlFilter.addAction(BluetoothTools.ACTION_SELECTED_DEVICE);
		controlFilter.addAction(BluetoothTools.ACTION_STOP_SERVICE);
		controlFilter.addAction(BluetoothTools.ACTION_DATA_TO_SERVICE);

		//ע��BroadcastReceiver
		registerReceiver(discoveryReceiver, discoveryFilter);
		registerReceiver(controlReceiver, controlFilter);
		super.onCreate();
	}

	/**
	 * Service����ʱ�Ļص�����
	 */
	@Override
	public void onDestroy() {
		if (communThread != null) {
			communThread.isRun = false;
		}
		//�����
		unregisterReceiver(discoveryReceiver);
		super.onDestroy();
	}

}
