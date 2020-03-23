package com.hs.bio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class BioServer {

    public static void main(String[] args)throws Exception {
        //创建线程池，一个请求一个线程
        ThreadFactory threadFactory = new UserThreadFactory("userThreadPool");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS,
                new LinkedBlockingDeque(100), threadFactory);

        //建立socketServer
        ServerSocket socketServer = new ServerSocket(1568);
        System.out.println("服务启动了...");

        while(true){
            System.out.println("阻塞等待连接...");
            Socket socket = socketServer.accept();//这一步阻塞等待客户端连接！
            System.out.println("有一个客户端连接...");

            poolExecutor.execute(() ->{
                handler(socket);
            });
        }
    }

    private static void handler(Socket socket) {
        //字节数组接受流
        byte[] bytes = new byte[1024];
        System.out.println(Thread.currentThread().getName());
        try {
            //阻塞等待消息...
            System.out.println("1");
            InputStream inputStream = socket.getInputStream();
            while(true){
                System.out.println("2");
                int read = inputStream.read(bytes);//这一步阻塞等待客户端输入流
                System.out.println("3");
                if(read != -1){
                    System.out.println(new String(bytes, 0, read, "gbk"));
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
