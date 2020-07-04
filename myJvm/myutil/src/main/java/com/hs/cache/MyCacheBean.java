package com.hs.cache;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyCacheBean
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/7 下午2:42
 * @Version 1.0
 */
public class MyCacheBean {
    /**
     * 缓存大key
     * @return
     */
    String key;

    /**
     * 过期时间
     * @return
     */
    int timeOut;

    /**
     * 时间单位
     * @return
     */
    TimeUnit timeUnit;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public MyCacheBean(String key, int timeOut, TimeUnit timeUnit) {
        this.key = key;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;
    }
}
