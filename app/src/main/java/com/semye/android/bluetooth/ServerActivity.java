package com.semye.android.bluetooth;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class ServerActivity extends Activity {
    private TextView tvServerState;
    private EditText etServerMsg, etServerSend;
    private Button btnServerSendMsg;
    // �㲥������
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (BluetoothTools.ACTION_DATA_TO_GAME.equals(action)) {
                // ��������
                byte[] data = intent.getExtras().getByteArray(
                        BluetoothTools.DATA);
                String msg = "from cmrx:" + new Date().toLocaleString()
                        + " :\r\n";

                File tagerF = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + File.separator + "aaa.db");

                if (tagerF.exists()) {

                    tagerF.delete();
                }

                try {
                    tagerF.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {

                    FileOutputStream fout = new FileOutputStream(
                            tagerF);

                    fout.write(data);

                    fout.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                etServerMsg.append(msg);

            } else if (BluetoothTools.ACTION_CONNECT_SUCCESS.equals(action)) {
                // ���ӳɹ�
                tvServerState.setText("���ӳɹ�");
                btnServerSendMsg.setEnabled(true);
            }

        }
    };

    @Override
    protected void onStart() {
        // ������̨service
        Intent startService = new Intent(ServerActivity.this,
                BluetoothServerService.class);
        startService(startService);

        // ע��BoradcasrReceiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothTools.ACTION_DATA_TO_GAME);
        intentFilter.addAction(BluetoothTools.ACTION_CONNECT_SUCCESS);

        registerReceiver(broadcastReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        // �رպ�̨Service
        Intent startService = new Intent(BluetoothTools.ACTION_STOP_SERVICE);
        sendBroadcast(startService);

        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    private OnClickListener btnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if ((etServerSend.getText().toString().trim()).equals("")) {
                Toast.makeText(ServerActivity.this, "���벻��Ϊ��",
                        Toast.LENGTH_SHORT).show();
            } else {
                // ������Ϣ
                MsgBean data = new MsgBean();
                data.setMsg(etServerSend.getText().toString());
                Intent sendDataIntent = new Intent(
                        BluetoothTools.ACTION_DATA_TO_SERVICE);
                sendDataIntent.putExtra(BluetoothTools.DATA, data);
                sendBroadcast(sendDataIntent);
            }
        }
    };

}
