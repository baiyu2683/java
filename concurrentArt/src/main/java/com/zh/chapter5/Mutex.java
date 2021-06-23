package com.zh.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 状态分两种： 0 , 1
 * 0: 锁未被获取
 * 1： 锁已被获取
 */
public class Mutex  implements Lock {

    private static final ThreadLocal<String> th = new ThreadLocal<>();

    private static class Sync extends AbstractQueuedSynchronizer {

        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

//        protected int tryAcquireShared(int arg) {
//            throw new UnsupportedOperationException();
//        }
//
//        protected boolean tryReleaseShared(int arg) {
//            throw new UnsupportedOperationException();
//        }

        /**
         * 锁是否被当前线程独占
         * @return
         */
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
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
    public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException {
        return sync.tryAcquireNanos(1, timeUnit.toNanos(l));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
