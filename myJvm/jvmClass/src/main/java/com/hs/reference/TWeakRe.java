package com.hs.reference;

import com.hs.Ka;

import java.lang.ref.WeakReference;

/**
 * @ClassName TWeakRe
 * @Description 弱引用
 * 弱引用只要辣鸡回收线程执行就会被回收
 * @Author hsir
 * @Date 2020/6/20 下午11:57
 * @Version 1.0
 */
public class TWeakRe {
    public static void main(String[] args) {
        WeakReference<Ka> weakReference = new WeakReference<>(new Ka());
        System.out.println(weakReference.get());//com.hs.Ka@66d3c617
        System.gc();
        System.out.println(weakReference.get());//null
        //回收辣鸡了...
    }
}
