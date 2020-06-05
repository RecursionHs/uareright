package com.hs.cache;

import org.junit.Test;

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
        //Object getUser = CacheUtils.invokeMethod(userController, "getUser", 1);
        //Object getUser2 = CacheUtils.invokeMethod(userController, "getUser", 1);
        Object getUser = CacheUtils.invokeMethod(userController, "getUser");
        Object getUser2 = CacheUtils.invokeMethod(userController, "getUser");
        System.out.println(getUser);
        System.out.println(getUser2);
    }
}
