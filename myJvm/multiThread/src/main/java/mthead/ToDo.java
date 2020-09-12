package mthead;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ToDo {

    public static void main(String[] args) {
        ThreadMange threadMange = new ThreadMange();
        int sleepTime = 20;
        for(int i = 0;i < 10;i++){
            final int proid = i;
            new Thread(()->{
                ThreadPoolExecutor thpool = threadMange.getThreadPooll(String.valueOf(proid));
                for(int j = 0;j < 5;j++){
                    thpool.execute(()->{
                        try {
                            System.out.println(Thread.currentThread().getName() + " 线程开始...");
                            TimeUnit.SECONDS.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }).start();
        }




    }


}
