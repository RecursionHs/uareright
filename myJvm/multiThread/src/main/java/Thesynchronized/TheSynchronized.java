package Thesynchronized;

public class TheSynchronized extends Thread{
        private  int num = 5;

    @Override
    public synchronized void run() {
        num--;
        System.out.println(TheSynchronized.currentThread().getName() + "num : " + num);
    }

    public static void main(String[] args) {
        TheSynchronized mt = new TheSynchronized();
        Thread thread1 = new Thread(mt,"thread1");
        Thread thread2 = new Thread(mt,"thread2");
        Thread thread3 = new Thread(mt,"thread3");
        Thread thread4 = new Thread(mt,"thread4");
        Thread thread5 = new Thread(mt,"thread5");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
    }
}
