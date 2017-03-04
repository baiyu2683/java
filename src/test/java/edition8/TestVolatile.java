package edition8;

import javax.management.remote.JMXConnectorServerMBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zh on 2017-03-04.
 */
public class TestVolatile {

    /**
     * volatile不保证原子性,类似++count这种非原子性操作会出错
     * 但赋值(count = 1)这种操作其他线程会立即'看到'（保证当前线程的读操作发生在其他线程的写操作之后）
     */
    private volatile int count = 0;
    private AtomicInteger count1 = new AtomicInteger(0);
    static BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();


    public void increase() {
        count++;
        count1.incrementAndGet();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCount() {
        return count + ";" + count1.get();
    }

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i < 10; i++) {
            for (int k = 0; k < 3456; k++)
                queue.add(k);
            TestVolatile testVolatile = new TestVolatile();
            for (int j = 0; j < 3456; j++)
                new Thread(new Thread(() -> {
                    Thread.yield();
                    testVolatile.increase();
                })).start();
            TimeUnit.SECONDS.sleep(3);
            System.out.println("count:" + testVolatile.getCount());
        }
    }

    public static void main1(String[] args) throws InterruptedException {

        for(int i = 0; i < 10; i++) {
            for (int k = 0; k < 3456; k++)
                queue.add(k);
            TestVolatile testVolatile = new TestVolatile();
            for (int j = 0; j < 3456; j++)
                new Thread(new Thread(() -> {
                    Thread.yield();
                    try {
                        testVolatile.setCount(queue.remove());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })).start();
            while(queue.size() > 0)
                TimeUnit.SECONDS.sleep(3);
            if (queue.size() == 0)
                TimeUnit.SECONDS.sleep(5);
            System.out.println("count:" + testVolatile.getCount());
        }
    }
}
