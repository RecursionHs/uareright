package com.hs.reference;

import java.lang.ref.WeakReference;

/**
 * @ClassName TWeakRe
 * @Description 弱引用
 * @Author hsir
 * @Date 2020/6/20 下午11:57
 * @Version 1.0
 */
public class TWeakRe {
    public static void main(String[] args) {
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024*1024*11]);
        weakReference.get();
    }
}
