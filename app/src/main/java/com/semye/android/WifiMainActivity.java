package com.semye.android;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class WifiMainActivity extends AppCompatActivity {

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wifi);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        show();
    }


    private void show() {
        //获取当前的wifi网络配置
        for (WifiConfiguration configuration : wifiManager.getConfiguredNetworks()) {
            System.out.println(configuration.toString());
        }

        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        System.out.println(connectionInfo.toString());

        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        System.out.println(dhcpInfo.toString());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            System.out.println("isScanAlwaysAvailable" + wifiManager.isScanAlwaysAvailable());
        }

        System.out.println("isWifiEnabled" + wifiManager.isWifiEnabled());
        System.out.println("wifiState" + wifiManager.getWifiState());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            System.out.println("5G" + wifiManager.is5GHzBandSupported());
            System.out.println("isDeviceToApRttSupported" + wifiManager.isDeviceToApRttSupported());
            System.out.println("isEnhancedPowerReportingSupported" + wifiManager.isEnhancedPowerReportingSupported());
            System.out.println("isP2pSupported" + wifiManager.isP2pSupported());
            System.out.println("isPreferredNetworkOffloadSupported" + wifiManager.isPreferredNetworkOffloadSupported());
            System.out.println("isTdlsSupported" + wifiManager.isTdlsSupported());
        }

        List<ScanResult> scanResults = wifiManager.getScanResults();
        for (ScanResult result : scanResults) {
            System.out.println(result.toString());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                List<PasspointConfiguration> passpointConfigurations = wifiManager.getPasspointConfigurations();
                for (PasspointConfiguration passpointConfiguration : passpointConfigurations) {
                    System.out.println(passpointConfiguration.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
