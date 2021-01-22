package com.semye.base.net;

import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws UnknownHostException {

        System.out.println("获取本机的host");
        InetAddress inetAddress = InetAddress.getLocalHost();
        InetAddress localHost = Inet4Address.getLocalHost();
        InetAddress localHost1 = Inet6Address.getLocalHost();
        System.out.println(localHost);
        System.out.println(localHost1);
        System.out.println(inetAddress);

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            System.out.println(networkInterfaces.nextElement().getDisplayName());
            System.out.println(networkInterfaces.nextElement().getInetAddresses().nextElement().getHostName());

        } catch (SocketException e) {
            e.printStackTrace();
        }
//        doGet();
    }


    public static void doGet() {
        try {
            URL url = new URL("http://www.icy7.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setAllowUserInteraction(true);//设置是否允许用户互动,比如弹出授权对话框
            urlConnection.setConnectTimeout(3000);//设置请求超时时长
            urlConnection.setReadTimeout(30000);//设置读取超时时长
            urlConnection.setUseCaches(true);//设置是否可以缓存
            urlConnection.setDefaultUseCaches(true);//设置默认的UseCache
            urlConnection.setDoInput(true);//从连接中读取
            urlConnection.setRequestProperty("yesheng", "my custom header");//设置请求头
            urlConnection.setRequestProperty("Content-type", "text/html");
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);//往连接中写入
            //urlConnection.getOutputStream().write("hahaha".getBytes());//这个操作会把GET请求变成POST请求
            urlConnection.getInputStream();//此方法会阻塞线程
            int code = urlConnection.getResponseCode();
            System.out.println(code);
            Object content = urlConnection.getContent();
            System.out.println(content);
            String contentEncoding = urlConnection.getContentEncoding();
            System.out.println(contentEncoding);

            CookieManager.getDefault();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
