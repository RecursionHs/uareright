package com.hs.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ChannelCopyFile {
    public static void main(String[] args)throws IOException {
        //利用channel及buffer复制文件
        FileInputStream inputStream = new FileInputStream("D:/b.jpg");
        FileOutputStream outputStream = new FileOutputStream("D:/c.jpg");

        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        outChannel.transferFrom(inChannel,0,inChannel.size());

        inputStream.close();
        outputStream.close();
    }
}
