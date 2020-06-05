package com.hs.cache;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * @ClassName ReflectUtils
 * @Description TODO
 * @Author hsir
 * @Date 2020/6/4 下午10:34
 * @Version 1.0
 */
public class CacheUtils {
    /**
     * 定义缓存
     */
    private static Map<String,Object> cache = new ConcurrentHashMap();
    private static final String SPLITTER = "::";

    public static Object invokeMethod(Object obj,String methodName,Object... param){
        Object result = null;
        //根据Obj得到class对象，得到指定mehod，然后判断方法是否有注解
        Class<?> objClass = obj.getClass();
        try {
            //无参数的情况
            if(param == null || param.length == 0){
                //得到指定的方法
                Method targetMethod = objClass.getDeclaredMethod(methodName);
                MyCache myCache = targetMethod.getAnnotation(MyCache.class);
                //看看是否有这个注解
                if(myCache != null){
                    //得到注解值
                    String key = myCache.key();
                    Object value = cache.get(key);
                    //缓存有值
                    if(value != null){
                        return value;
                    }else{
                        //缓存没有值
                        result = targetMethod.invoke(obj);
                        cache.put(key,result);
                    }

                }
            }else{
                //解析参数情况
                int size = param.length;
                Class<?>[] classess = new Class[size];
                Object[] paramValue = new Object[size];
                for (int i = 0; i < size; i++) {
                    classess[i] = param[i].getClass();
                    paramValue[i] = param[i];
                }
                //获取到带参的指定方法
                Method method = objClass.getDeclaredMethod(methodName, classess);
                method.setAccessible(true);
                MyCache myCache = method.getAnnotation(MyCache.class);
                //判断是否有这个注解
                if(myCache != null){
                    //获取大key
                    String key = myCache.key();
                    //获取小key
                    Object o = paramValue[0];
                    key = key + SPLITTER + o;
                    //判断缓存是否存在此key
                    Object value = cache.get(key);
                    if(value != null){
                        return value;
                    }else{
                        Object objValue = method.invoke(obj, paramValue);
                        cache.put(key,objValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
