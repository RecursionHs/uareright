package com.hs.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFile {

    public static void main(String[] args) throws IOException {
        //读取文件
        File file = new File("D:/myfile.txt");
        //流建立channel
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();

        //声明缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //读到缓冲区
        channel.read(byteBuffer);

        //从缓冲区读取数据
        System.out.println(new String(byteBuffer.array()));//hello my friend
    }
}
