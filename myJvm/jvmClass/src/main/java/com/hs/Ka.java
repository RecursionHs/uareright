package com.hs;

/**
 * @ClassName Ka
 * @Description finalize()只会在对象内存回收前被调用一次
 * @Author hsir
 * @Date 2020/6/20 下午11:26
 * @Version 1.0
 */
public class Ka {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("回收辣鸡了...");
        super.finalize();
    }
}
