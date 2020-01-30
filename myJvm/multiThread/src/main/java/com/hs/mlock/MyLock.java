package com.hs.mlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 通过继承AbstractQueuedSynchronizer类的helper类来实现独占模式
 *
 */
public class MyLock implements Lock {

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer {
        //获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            //获取state
            int state = getState();
            //CAS方式设置值
            if(compareAndSetState(0,arg)){
                //设置独占此线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }else if(getExclusiveOwnerThread() == Thread.currentThread()){
                setState(state + arg);
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            //通过改变state值，作为一个释放锁的标志
            int state = getState() - arg;
            if(state == 0){
                //释放锁操作
                setExclusiveOwnerThread(null);
                setState(state);
                return true;
            }
            setState(state);
            return false;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }
}
