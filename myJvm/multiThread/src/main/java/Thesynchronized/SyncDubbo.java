package Thesynchronized;

/**
 * 可重入锁：线程自己在锁里面可获取相同对象的锁。
 */
public class SyncDubbo {

    public synchronized void method1(){
        System.out.println("method1...");
        method2();
    }

    public synchronized void method2(){
        System.out.println("method2...");
        method3();
    }

    public synchronized void method3(){
        System.out.println("method3...");
    }

    public static void main(String[] args) {
        final SyncDubbo syncDubbo = new SyncDubbo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                syncDubbo.method1();
            }
        }).start();
    }
}
