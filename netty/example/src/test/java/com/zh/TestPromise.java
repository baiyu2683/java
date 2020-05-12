package com.zh;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPromise {

    @Test
    public void testPromise() {
        MyExecutor myExecutor = new MyExecutor();
        MyPromise myPromise = myExecutor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            System.out.println(myPromise.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyExecutor {
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new SynchronousQueue<>());
    private MyPromise myPromise;

    public MyPromise execute(Runnable runnable) {
        if (myPromise == null) {
            myPromise = new MyPromise();
        }
        threadPoolExecutor.execute(() -> {
            try {
                runnable.run();
                myPromise.setSuccess(true);
            }catch (Exception e) {
                myPromise.setSuccess(false);
            } finally {
                myPromise.setDone(true);
            }
            myPromise.setResult("完成了");
        });
        return myPromise;
    }
}


class MyPromise<T> implements Future<T> {

    private Boolean done = false;
    private Boolean success = false;
    private Boolean canceled = false;

    private T result;

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
        synchronized (this) {
            if (done) {
                //TODO 监听
                // 唤醒
                notifyAll();
                System.out.println("唤醒");
            }
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        synchronized (this) {
            while (!isDone()) {
                wait();
            }
        }
        return result;
    }

    @Override
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
