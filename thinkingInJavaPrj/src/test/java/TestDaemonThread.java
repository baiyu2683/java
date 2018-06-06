import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2016/12/18.
 */
public class TestDaemonThread {

    @Test
    public void test1() throws InterruptedException {
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    while(true){
                        TimeUnit.SECONDS.sleep(40);
                        System.out.println(Thread.currentThread().getId() + " " + this + "-" + this.getClass().getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();

        }
        TimeUnit.SECONDS.sleep(100);
    }
}
