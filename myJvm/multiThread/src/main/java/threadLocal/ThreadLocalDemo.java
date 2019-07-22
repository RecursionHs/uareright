package threadLocal;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalDemo {
    public static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    public void getThreadLocal() {
        System.out.println(Thread.currentThread().getName());
        threadLocal.get().forEach(name -> System.out.println(name));
    }

    public  void setThreadLocal(List<String> values) {
        threadLocal.set(values);
    }

    public static void main(String[] args) {
        final ThreadLocalDemo threadLocal = new ThreadLocalDemo();
        new Thread(() -> {
            List<String> param = new ArrayList<>(3);
            param.add("张三");
            param.add("李四");
            param.add("王五");
            threadLocal.setThreadLocal(param);
            threadLocal.getThreadLocal();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                List<String> param = new ArrayList<>(2);
                param.add("chinese");
                param.add("English");
                threadLocal.setThreadLocal(param);
                threadLocal.getThreadLocal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
