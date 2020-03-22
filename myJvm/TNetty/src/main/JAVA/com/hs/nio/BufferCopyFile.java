package com.hs.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferCopyFile {
    public static void main(String[] args)throws IOException {
        //利用channel及buffer复制文件
        FileInputStream inputStream = new FileInputStream("D:/a.jpg");
        FileOutputStream outputStream = new FileOutputStream("D:/b.jpg");

        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while(true){
            //重置buffer
            byteBuffer.clear();
            //从in通道将数据读到buffer中
            int read = inChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            //反转开始从buffer读取数据到写出通道
            byteBuffer.flip();
            outChannel.write(byteBuffer);
        }
        inputStream.close();
        outputStream.close();
    }
}
