package com.hs.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 写出一个文件
 */
public class FileNio {

    public static void main(String[] args)throws Exception {
        String str = "hello my friend";
        //基于java io流建立通道
        FileOutputStream outputStream = new FileOutputStream("D:/myfile.txt");
        //其实是FileChannelImpl对象
        FileChannel fileChannel = outputStream.getChannel();
        //建立Buffer,现将数据写到Buffer在写到Channel
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //写入数据到ByteBuffer
        ByteBuffer buffer = byteBuffer.put(str.getBytes());

        //反转读写，position置为0，写到Channel
        buffer.flip();
        fileChannel.write(buffer);
        outputStream.close();
    }
}
