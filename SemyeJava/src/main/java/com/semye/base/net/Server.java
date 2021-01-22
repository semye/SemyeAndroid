package com.semye.base.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8081);
            //等待客户端的连接
            Socket socket = serverSocket.accept();
            //获取输入流,并且指定统一的编码格式
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            //读取一行数据
            String str;
            //通过while循环不断读取信息，
            while ((str = bufferedReader.readLine()) != null) {
                //输出打印
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
