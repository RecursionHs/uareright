package com.hs.bio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BioTest1 {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(InetSocketAddress.createUnresolved("127.0.0.1",5800));
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[128];
            int read = inputStream.read(bytes);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * IO多路复用
     */
    @Test
    public void Test1() throws Exception{
        SocketChannel socketChannel1 = SocketChannel.open();
        SocketChannel socketChannel2 = SocketChannel.open();
        socketChannel1.configureBlocking(false);
        socketChannel2.configureBlocking(false);

        socketChannel1.connect(InetSocketAddress.createUnresolved("127.0.0.1",3100));
        socketChannel2.connect(InetSocketAddress.createUnresolved("127.0.0.1",3200));

        //创建一个选择器
        Selector selector = Selector.open();

        socketChannel1.register(selector, SelectionKey.OP_READ);
        socketChannel2.register(selector,SelectionKey.OP_READ);

        ByteBuffer buffer = ByteBuffer.wrap(new byte[128]);

        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey selectionKey = iterator.next();
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            channel.read(buffer);
            iterator.remove();
        }


    }

    /**
     * 多线程BIO
     */
    @Test
    public void Test2()throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1",5888),50);
        //线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        Socket socket;
        while ((socket = serverSocket.accept()) != null){
            final Socket finalSocket = socket;
            threadPool.submit(()->{
                try {
                    InputStream inputStream = finalSocket.getInputStream();
                    byte[] bytes = new byte[16];
                    inputStream.read(bytes);
                    OutputStream outputStream = finalSocket.getOutputStream();
                    byte[] bb = "收到了!".getBytes();
                    byte[] byteMerger = byteMerger(bytes, bb);
                    outputStream.write(byteMerger);
                    finalSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

        }


    }

    public static byte[] byteMerger(byte[] b1,byte[] b2){
        byte[] newByte = new byte[b1.length + b2.length];
        System.arraycopy(b1,0,newByte,0,b1.length);
        System.arraycopy(b2,0,newByte,b1.length,b2.length);
        return newByte;
    }

    public Selector[] initWorkerSelectors()throws IOException{
        Selector[] selectors = new Selector[Runtime.getRuntime().availableProcessors()];
        for (int i = 0;i < selectors.length;i++){
            Selector selector = Selector.open();
            selectors[i] = selector;
            new Thread(()->{
                while (true){
                    try {
                        processWithSelector(selector);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        return selectors;
    }

    private void processWithSelector(Selector selector)throws IOException {
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey selectionKey = iterator.next();
            if(selectionKey.isValid() == false){
                continue;
            }

            if(selectionKey.isReadable()){
                ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                int read = socketChannel.read(byteBuffer);
                if(read == -1){
                    selectionKey.cancel();
                    socketChannel.close();
                }else{
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.limit() - byteBuffer.position()];
                    byteBuffer.get(bytes);
                    byte[] byteMerger = byteMerger(bytes, "收到了".getBytes());
                    ByteBuffer buffer = ByteBuffer.allocate(byteMerger.length);
                    buffer.clear();
                    buffer.get(byteMerger,0,byteMerger.length);
                    socketChannel.write(buffer);
                }
            }
            iterator.remove();
        }
    }

    public void start()throws IOException{
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        AtomicInteger atomicInteger = new AtomicInteger();
        Selector[] selectors = initWorkerSelectors();
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                iterator.next();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.register(selectors[atomicInteger.getAndIncrement() % selectors.length],SelectionKey.OP_READ);
                iterator.remove();
            }
        }
    }


}
