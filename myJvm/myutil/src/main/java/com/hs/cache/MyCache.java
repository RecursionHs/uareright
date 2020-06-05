package com.hs.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;
/**
 * @Author: hsir 
 * @Date: 2020/6/3 下午10:52
 * @Description: 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCache {

    /**
     * 缓存大key
     * @return
     */
    String key();

    /**
     * 过期时间
     * @return
     */
    int timeOut() default 10;

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MINUTES;
}
