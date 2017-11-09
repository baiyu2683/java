package com.zh.chapter14;

import javax.rmi.PortableRemoteObject;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by zh on 2017-11-08.
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        protected int tryAcquireShared(int ignored) {
            return (getState() == 1) ? 1 : -1;
        }
        protected boolean tryReleaseShared(int ignored) {
            setState(1); //1表示可用
            return true;
        }
    }
}
