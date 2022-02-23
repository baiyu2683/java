package com.zh.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 锁, 不可冲入
 */
public class NewLock implements Lock {

    /**
     * 同步器
     * 初始同步状态为0
     * 规定：获取锁后同步状态为1，解锁后为0
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            // 尝试获取锁，就是尝试设置同步器状态到自己的状态
            if(compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 状态必须为1
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
//            if (compareAndSetState(1, 0)) {
//                setExclusiveOwnerThread(null);
//                return true;
//            }
            setExclusiveOwnerThread(null);
            setState(0);
            return false;
        }

        @Override
        protected boolean isHeldExclusively() {
//            return getExclusiveOwnerThread() != null;
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.tryAcquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.tryRelease(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

}
