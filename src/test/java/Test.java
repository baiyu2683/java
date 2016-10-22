import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zhangheng on 16-8-28.
 */
public class Test {

    @org.junit.Test
    public void test1(){
        BlockingQueue<Integer> deque = new LinkedBlockingQueue<>();
        for(int i = 0; i < 10; i++){
            try {
                deque.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Integer i = null;
            while(true){
                i = deque.take();
                System.out.println(i);
                if(i >= 9) break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
