package com.hs.Distributedlocks;



import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZkLock implements Lock {
    
    private ThreadLocal<ZooKeeper> zk = new ThreadLocal<>();
    
    private String LOCK_NAME = "/LOCK";

    private ThreadLocal<String> CURRENT_NODE = new ThreadLocal<>();

    public void init(){
        if(zk.get() == null){
            try {
                zk.set( new ZooKeeper("localhost:2181", 300, new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {

                    }
                }));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lock(){
        init();
        if(tryLock()){
            System.out.println(Thread.currentThread().getName() + "已经获取到锁...");
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(){
        String nodeName = LOCK_NAME + "/zk_";

        try {
            CURRENT_NODE.set( zk.get().create(nodeName, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL));

            List<String> list = zk.get().getChildren(LOCK_NAME, false);
            System.out.println("list:" + list);

            Collections.sort(list);

            String minNodeName = list.get(0);

            if(CURRENT_NODE.get().equals(LOCK_NAME + "/" + minNodeName)){
                return true;
            }else{
                //取当前创建的节点的节点名，不包含父节点
                String cChidNode = CURRENT_NODE.get().substring(CURRENT_NODE.get().lastIndexOf("/") + 1);
                int CURRENT_NODEIndex = list.indexOf(cChidNode);
                System.out.println(CURRENT_NODEIndex);
                String prevNodeName = list.get(CURRENT_NODEIndex - 1);

                //阻塞
                CountDownLatch countDownLatch = new CountDownLatch(1);
                zk.get().exists(LOCK_NAME + "/" + prevNodeName, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        if(Event.EventType.NodeDeleted.equals(event.getType())){
                            countDownLatch.countDown();
                            System.out.println(Thread.currentThread().getName() + "被唤醒...");
                        }
                    }
                });
                System.out.println(Thread.currentThread().getName() + "阻塞住...");
                countDownLatch.await();
                return true;
            }

        } catch (KeeperException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void unlock(){
        try {
            //-1忽略版本号
            zk.get().delete(CURRENT_NODE.get(),-1);
            zk.get().close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }finally {
            CURRENT_NODE.remove();
        }


    }
    
}
