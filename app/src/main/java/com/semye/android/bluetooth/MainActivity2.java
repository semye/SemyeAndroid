package com.semye.android.bluetooth;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends Activity {
    private Button btnServer, btnClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnClickListener btnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == 1) {
                Intent serverIntent = new Intent(MainActivity2.this, ServerActivity.class);
                startActivity(serverIntent);

            } else if (i == 2) {
                openBluetooth();

            } else {
            }
        }
    };

    public void openBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(MainActivity2.this, "���豸��֧��������", Toast.LENGTH_SHORT).show();
        } else {
            if (!bluetoothAdapter.isEnabled()) { // ����δ��������������
//				Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//				// startActivityForResult(enableIntent, 1);
//				startActivity(enableIntent);
                BluetoothTools.openBluetooth();
                Intent searchIntent = new Intent(MainActivity2.this, ClientActivity.class);
                startActivity(searchIntent);
            } else {
                Intent searchIntent = new Intent(MainActivity2.this, ClientActivity.class);
                startActivity(searchIntent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Toast.makeText(MainActivity2.this, "�����ѿ�����", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


}
