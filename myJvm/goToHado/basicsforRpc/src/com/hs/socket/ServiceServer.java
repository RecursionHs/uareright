package com.hs.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceServer {

    public static void main(String[] args) throws IOException {
        //绑定连接到本地8899端口
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost",8899));

        //获取socket连接。accept是一个阻塞的方法
        while (true) {
            Socket socket = server.accept();
            //调用线程执行任务
            new Thread(new ServiceServerTask(socket)).start();
        }
    }
}
