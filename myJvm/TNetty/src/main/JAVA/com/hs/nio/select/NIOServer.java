package com.hs.nio.select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到selector对象
        Selector selector = Selector.open();

        //绑定监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //将serverSocketChannel注册到selector,并监听OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while(true){
            //没有事件发生就继续下一次
            //selector.select(1000)会阻塞1s
            if(selector.select(1000) == 0){
                System.out.println("服务器等待了1s，无连接...");
                continue;
            }
            //如果返回的>0,就表示有事件发生了，这个时候就可以拿到所有的事件selectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历集合
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                //拿到相应的selectionKey
                SelectionKey selectionKey = iterator.next();
                //判断是啥事件
                if(selectionKey.isAcceptable()){
                    //如果是连接事件,就需要新建个SocketChannel给客户端使用
                    //accept()本身是阻塞方法，但是这里有事件发生再调用此方法，就不会阻塞。
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("有个客户端连接上了，hash为：" + socketChannel.hashCode());
                    //设置非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector，监听OP_READ事件,关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if(selectionKey.isReadable()){
                    //OP_READ事件
                    //通过key反向获得channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    //从channel读取消息到buffer
                    channel.read(buffer);
                    System.out.println("客户端发来的消息为: " + new String(buffer.array()));
                }
                //手动移除当前selectionKey,防止重复操作
                iterator.remove();

            }

        }
    }
}
