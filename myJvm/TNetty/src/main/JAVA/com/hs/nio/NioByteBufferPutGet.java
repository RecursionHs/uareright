package com.hs.nio;

import java.nio.ByteBuffer;

public class NioByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        buffer.putInt(23);
        buffer.putChar('哈');
        buffer.putLong(123L);
        buffer.putShort((short) 4);

        //取出数据
        buffer.flip();
        System.out.println("--------------------");
        System.out.println(buffer.getInt());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getLong());
    }
}
