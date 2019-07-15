package Thesynchronized;

public class MyThread extends Thread{

    private int num = 5;

    @Override
    public void run() {
        num--;
        System.out.println(this.currentThread().getName() + " num:" + num);
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread();
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
