package com.hs.nio;

import java.nio.IntBuffer;

public class TBuffer {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //写入数据
        for(int i = 0;i < intBuffer.capacity();i++){
            intBuffer.put(i * 3);
        }

        //切换读写模式。读取数据。
        intBuffer.flip();
        intBuffer.position(1);
        intBuffer.limit(3);
        //读取数据
        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
