package com.hs.reference;

import com.hs.Ka;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName TPhantomRe
 * @Description 虚引用
 * 虚引用get不到它的精髓，它的作用就是通知回收，入队列，gc线程扫描队列进行回收，一般用于管理堆外内存
 * @Author hsir
 * @Date 2020/6/21 上午11:52
 * @Version 1.0
 */
public class TPhantomRe {
    public static void main(String[] args) {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Ka> phantomReference = new PhantomReference<>(new Ka(),queue);
        System.out.println(phantomReference.get());
    }
}
