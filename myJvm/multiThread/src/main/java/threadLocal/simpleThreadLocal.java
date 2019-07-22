package threadLocal;


import java.util.*;

public class simpleThreadLocal<T> {
    /**
     * map<thread,value>
     */
    private  Map<Thread,T> valueMap = Collections.synchronizedMap(new HashMap<Thread, T>());
    /**
     * setValue
     */
    public void set(T value){
        valueMap.put(Thread.currentThread(),value);
    }

    /**
     * getValue
     */
    public T get(){
        Thread currentThread = Thread.currentThread();
        //当前线程的值
        T t = valueMap.get(currentThread);
        //如果map中不存在当前线程。就设置进去
        if(t == null && !valueMap.containsKey(currentThread)){
            t = initialValue();
            valueMap.put(currentThread,t);
        }
        return t;
    }

    public T initialValue() {
        return null;
    }

    public void remove(){
        valueMap.remove(Thread.currentThread());
    }

    public static void main(String[] args) {
        simpleThreadLocal<List<String>> threadLocal = new simpleThreadLocal<>();

        new Thread(() ->{
            List<String> params = new ArrayList<>(3);
            params.add("张三");
            params.add("李四");
            params.add("王五");
            threadLocal.set(params);
            System.out.println(Thread.currentThread().getName());
            threadLocal.get().forEach(param -> System.out.println(param));
        }).start();

        new Thread(() ->{
            List<String> params = new ArrayList<>(2);
            params.add("chinese");
            params.add("English");
            threadLocal.set(params);
            System.out.println(Thread.currentThread().getName());
            threadLocal.get().forEach(param -> System.out.println(param));
        }).start();
    }

}
