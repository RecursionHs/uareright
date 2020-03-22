package com.hs.bio;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class UserThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    UserThreadFactory(String threadGroupName){
        namePrefix = "threadGroups :" + threadGroupName + "-Worker-";
    }
    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(r,name);
        System.out.println(thread.getName());
        return thread;
    }
}
