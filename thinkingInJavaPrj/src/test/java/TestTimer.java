
import org.junit.Test;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zh on 2017-07-20.
 */
public class TestTimer {

    @Test
    public void test1() throws IOException {
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("zhangheng");
            }
        };
        timer.schedule(tt, 1000);
        System.in.read();
    }
}
