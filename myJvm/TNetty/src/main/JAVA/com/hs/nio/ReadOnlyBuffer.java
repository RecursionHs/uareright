package com.hs.nio;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for(int i = 0;i < 64;i++){
            buffer.put((byte) i);
        }
        buffer.flip();
        //转为只读buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        //读取数据
        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        //写入数据
        readOnlyBuffer.put((byte) 23);//抛异常ReadOnlyBufferException

    }
}
