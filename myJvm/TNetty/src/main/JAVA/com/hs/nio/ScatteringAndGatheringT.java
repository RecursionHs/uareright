package com.hs.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering ：将数据写入到buffer时，可以采用buffer数组依次写入。
 * Gathering : 从buffer读取数据时，可以采用buffer数组，依次读
 */
public class ScatteringAndGatheringT {

    public static void main(String[] args) throws IOException {
        //使用ServerSocketChannel和socketChannel 网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress(7894);

        //绑定端口到socket,并启动
        serverSocketChannel.socket().bind(socketAddress);

        //创建buffer数据
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接（Telnet方式）
        SocketChannel socketChannel = serverSocketChannel.accept();
        //只从客户端接收8个字节
        int messageLength = 8;
        while (true){
            int byteRead = 0;
            while (byteRead < messageLength){
                long readL = socketChannel.read(byteBuffers);
                byteRead += readL;//累计读取的字节数
                System.out.println("byteRead : " + byteRead);
                //使用流打印position及limit
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position: " + byteBuffer.position() +
                        ",limit: " + byteBuffer.limit()).forEach(System.out::println);

                //将所有byteBuffer进行flip
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

                //将数据读出显示到客户端
                long byteWrite = 0;
                while (byteWrite < messageLength){
                    long writeL = socketChannel.write(byteBuffers);
                    byteWrite += writeL;
                }
                //将所有的buffer进行clear，防止下次循环有问题
                Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

                System.out.println("byteRead: " + byteRead + ",byteWrite: " + byteWrite + ",messageLength: " + messageLength);
            }
        }

    }
    /**
     * 测试1：输入helloa
     * byteRead : 6
     * position: 5,limit: 5
     * position: 1,limit: 3
     * 测试2：输入helloabc
     * byteRead : 8
     * position: 5,limit: 5
     * position: 3,limit: 3
     * byteRead: 8,byteWrite: 8,messageLength: 8
     */
}
