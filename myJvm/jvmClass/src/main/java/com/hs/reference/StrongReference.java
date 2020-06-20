package com.hs.reference;

import com.hs.Ka;

import java.io.IOException;

/**
 * @ClassName StrongReference
 * @Description 强引用
 * 产生的对象一直会在，不会被回收
 * 把不用的对象指向null，gc时就会被回收
 * @Author hsir
 * @Date 2020/6/20 下午11:25
 * @Version 1.0
 */
public class StrongReference {

    public static void main(String[] args) throws IOException {
        Ka ka  = new Ka();
        ka = null;
        System.gc();
        System.in.read();
    }
}
