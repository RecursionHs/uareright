package com.hs.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *MappedByteBuffer
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/myfile.txt","rw");
        FileChannel randomAccessFileChannel = randomAccessFile.getChannel();
        /**
         * FileChannel.MapMode.READ_WRITE：读写模式
         * position:可以直接修改的起始位置
         * size:映射到内存的大小（不是索引地址），即myfile.txt的多少个字节映射到内存
         * 可以直接修改的范围就是0-5
         * 实际类型是：DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = randomAccessFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte)'K');
        mappedByteBuffer.put(3,(byte)'L');
        System.out.println("修改完毕!");
        randomAccessFile.close();
    }
}
