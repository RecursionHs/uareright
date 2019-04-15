package com.hs.nio;

public class TimeServer {

    public static void main(String[] args) {
        //设置默认监听地址
        int port = 8080;

        //如果传入端口
        if(args != null && args.length > 0){
             port = Integer.parseInt(args[0]);
            //传入就采用此端口
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();

    }
}
