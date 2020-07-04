package com.hs.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SoftReference
 * @Description 软引用
 * 设置-Xmx20M
 * @Author hsir
 * @Date 2020/6/20 下午11:45
 * @Version 1.0
 */
public class TSoftRe {

    public static void main(String[] args) {

        //软引用指向11M对象
        SoftReference<byte[]> reference = new SoftReference<>(new byte[1024*1024*11]);
        //System.gc();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(reference.get());
        //再生成11M对象就超出Xmx设置的值了，会进行辣鸡回收，软应用对象直接被干掉
        byte[] b = new byte[1024*1024*11];
        System.out.println(reference.get());
        /**输出：
         * [B@66d3c617
         * null
         */
    }
}
