package edition8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.typeinfo.pets.Cat;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by zhangheng on 2017/1/10.
 */
public class TestJava8 {
    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
            10,
            r -> {
                Thread thread = new Thread(r);
                thread.setName("test");
                return thread;
            }
    ) {
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            try {
                Future<?> future = (Future<?>) r;
                if(future.isDone())
                    future.get();
            } catch (Exception e) {
                System.out.println("-----------------------" + 1);
                e.printStackTrace();
            }
        }
    };

    @Test
    public void testRandom() {
        Random random = new Random(47);
        for(int i = 0; i < 10; i++) {
            scheduledThreadPoolExecutor.execute(() -> {
                if (random.nextInt(10) < 5)
                    throw new RuntimeException();
                else {
                    String s = "123";
                }
            });
        }
    }
}
