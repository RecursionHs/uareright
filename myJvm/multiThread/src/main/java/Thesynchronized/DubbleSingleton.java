package Thesynchronized;

/**
 * 线程 A 在创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象设置为默认值。
 * 此时线程 A 就可以将分配的内存地址赋值给 instance 字段了，然而该对象可能还没有完成初始化操作。
 * 线程 B 来调用 newInstance() 方法，得到的 就是未初始化完全的单例对象，这就会导致系统出现异常行为。
 */
public class DubbleSingleton {
    private static volatile DubbleSingleton instance;

    public static DubbleSingleton getInstance(){
        if (null == instance){
            try {
                //模拟初始化对象的时间
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
            //类上加锁，表示当前对象不可以在其他线程的时候创建
            synchronized (DubbleSingleton.class){
            //如果不加这一层判断的话，这样的话每一个线程会得到一个实例
            //而不是所有的线程的到的是一个实例
                if(instance == null){
                    instance = new DubbleSingleton();
                }
            }
        }
        return instance;
    }
}
