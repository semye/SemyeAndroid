package com.semye.android.bluetooth;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class ClientActivity extends Activity {
    private ListView listView;
    private Button btnSearch, btnClientSendMsg;
    private TextView tvClientState;
    private EditText etClientChat, etClientSend;
    private BluetoothAdapter bluetoothAdapter;
    private List<String> devices;
    private List<BluetoothDevice> deviceList;
    private List<BluetoothDevice> finaldeviceList;
    private BaseAdapter baseAdapter;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothTools.ACTION_NOT_FOUND_SERVER.equals(action)) {
                tvClientState.append("not found device\r\n");

            } else if (BluetoothTools.ACTION_FOUND_DEVICE.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getExtras().get(BluetoothTools.DEVICE);
                deviceList.add(device);
                tvClientState.append(device.getName() + "\r\n");
//				if (deviceList.size()>0) {
//					for (int i = 0; i < deviceList.size(); i++) {
//						if (device.getName().equals(deviceList.get(i).getName())) {
//							deviceList.add(device);
//							tvClientState.append(device.getName() + "\r\n");
//						}
//					}
//				}

            } else if (BluetoothTools.ACTION_CONNECT_SUCCESS.equals(action)) {
                tvClientState.append("���ӳɹ�");
                btnClientSendMsg.setEnabled(true);

            } else if (BluetoothTools.ACTION_DATA_TO_GAME.equals(action)) {
                MsgBean data = (MsgBean) intent.getExtras()
                        .getSerializable(BluetoothTools.DATA);
                String msg = "from remote " + new Date().toLocaleString()
                        + " :\r\n" + data.getMsg() + "\r\n";
                etClientChat.append(msg);

            } else if (BluetoothTools.ACTION_FOUND_SERVER_OVER.equals(action)) {
                showDevices();
                Toast.makeText(ClientActivity.this, "������ɣ�", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onStart() {
        deviceList.clear();

        Intent startService = new Intent(ClientActivity.this, BluetoothClientService.class);
        startService(startService);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothTools.ACTION_NOT_FOUND_SERVER);
        intentFilter.addAction(BluetoothTools.ACTION_FOUND_DEVICE);
        intentFilter.addAction(BluetoothTools.ACTION_DATA_TO_GAME);
        intentFilter.addAction(BluetoothTools.ACTION_CONNECT_SUCCESS);
        intentFilter.addAction(BluetoothTools.ACTION_FOUND_SERVER_OVER);

        registerReceiver(broadcastReceiver, intentFilter);

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private OnClickListener btnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == 1) {
                tvClientState.setText("");
                finaldeviceList.clear();
                baseAdapter.notifyDataSetChanged();
                Intent startSearchIntent = new Intent(BluetoothTools.ACTION_START_DISCOVERY);
                sendBroadcast(startSearchIntent);

            } else if (i == 2) {
                String txt = etClientSend.getText().toString().trim();
                if (txt.equals("")) {
                    Toast.makeText(ClientActivity.this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
                } else {
                    MsgBean msgBean = new MsgBean();
                    msgBean.setMsg(txt);
                    Intent sendDataIntent = new Intent(BluetoothTools.ACTION_DATA_TO_SERVICE);
                    sendDataIntent.putExtra(BluetoothTools.DATA, msgBean);
                    sendBroadcast(sendDataIntent);
                }

            } else {
            }
        }
    };

    protected void onStop() {
        Intent startService = new Intent(BluetoothTools.ACTION_STOP_SERVICE);
        sendBroadcast(startService);

        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    ;


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent selectDeviceIntent = new Intent(BluetoothTools.ACTION_SELECTED_DEVICE);
            selectDeviceIntent.putExtra(BluetoothTools.DEVICE, finaldeviceList.get(position));
            sendBroadcast(selectDeviceIntent);
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
    };


    private boolean isLock(BluetoothDevice device) {
        boolean isLockName = (device.getName()).equals("cmrx");
        boolean isSingleDevice = devices.indexOf(device.getName()) == -1;
        //return isLockName && isSingleDevice;
        return true;
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

    private void showDevices() {
        if (deviceList.size() > 0) {
            for (int i = 0; i < deviceList.size(); i++) {
                if (isLock(deviceList.get(i))) {
                    finaldeviceList.add(deviceList.get(i));
                    devices.add("��������" + deviceList.get(i).getName() + "      ������ַ: " + deviceList.get(i).getAddress());
                }
            }
//			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//					android.R.layout.simple_list_item_1, devices);
//			listView.setAdapter(adapter);
            baseAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ClientActivity.this, "û�������������豸��", Toast.LENGTH_SHORT).show();
        }

    }

    class myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return finaldeviceList.size();
        }

        @Override
        public Object getItem(int position) {
            return finaldeviceList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint({"NewApi", "DefaultLocale"})
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            BluetoothDevice device = (BluetoothDevice) getItem(position);
            LinearLayout layout = new LinearLayout(ClientActivity.this);

			/*layout.inflate(ProjectList.this,
                    android.R.layout.simple_expandable_list_item_2, null);*/
            TextView tv = new TextView(ClientActivity.this);//���
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
            tv.setPadding(0, 10, 0, 10);
            tv.setWidth(40);
            tv.setText(String.format("%d", position + 1));
            tv.setGravity(Gravity.CENTER);
            layout.addView(tv);
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIB));
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIW));
            TextView tv2 = new TextView(ClientActivity.this);//�������
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
            tv2.setPadding(0, 10, 0, 10);
            tv2.setWidth(260);
            tv2.setText(device.getName());
//			tv2.setText(map.get("surveyBegin"));
            tv2.setGravity(Gravity.CENTER);
            layout.addView(tv2);
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIB));
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIW));
            TextView tv3 = new TextView(ClientActivity.this);
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
            tv3.setPadding(0, 10, 0, 10);
            tv3.setWidth(400);
            tv3.setGravity(Gravity.CENTER);
            tv3.setText(device.getAddress());
//			tv3.setText(map.get("surveyPlace"));
            layout.addView(tv3);
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIB));
            //layout.addView(new View(ProjectList.this,null,R.style.ViewIW));
            return layout;
        }
    }

}
