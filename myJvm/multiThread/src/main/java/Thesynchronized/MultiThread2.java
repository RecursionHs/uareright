package Thesynchronized;

public class MultiThread2 {

    private static int num = 200;

    public  static synchronized  void printNum(String threadName,String tag){
        if("a".equals(tag)){
            num = num - 100;
            System.out.println(threadName + "tag a set num over");
        }else{
            num = num - 200;
            System.out.println(threadName + "tag b set num over");
        }
        System.out.println(threadName + ",tag: " + tag + ",num: " + num);
    }

    public static void main(String[] args) {
        final MultiThread2 multiThread1 = new MultiThread2();
        final MultiThread2 multiThread2 = new MultiThread2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                multiThread1.printNum("thread1","a");
            }
        }).start();new Thread(new Runnable() {
            @Override
            public void run() {
                multiThread2.printNum("thread2","b");
            }
        }).start();


    }
}
