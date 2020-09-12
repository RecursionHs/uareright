package mthead;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class ThreadMange {

    public static ConcurrentHashMap<String,ThreadPoolExecutor> cmp = new ConcurrentHashMap<String,ThreadPoolExecutor>();

    public synchronized ThreadPoolExecutor getThreadPooll(String proid){
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("sql-hdfs-%d").build();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5,
                5,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        cmp.put(proid,poolExecutor);
        return poolExecutor;
    }
}
