package com.hs.cache;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/4 下午10:33
 * @Version 1.0
 */
public class TestMain {
    /**
     * 第一次会存入值，所以为null,第二次就直接获取到值了
     */
    @Test
    public void test1(){
        UserController userController = new UserController();
        Object getUser1 = CacheUtils.invokeMethod(userController, "getUser", 1);
        Object getUser2 = CacheUtils.invokeMethod(userController, "getUser", 5);
        Object getUse3 = CacheUtils.invokeMethod(userController, "getUser");
        Object getUser4 = CacheUtils.invokeMethod(userController, "getUser");
        System.out.println("缓存完毕...");
        try {
            TimeUnit.SECONDS.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
