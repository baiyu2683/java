
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zh on 2017-01-15.
 */
public class TestQueue {

    @Test
    public void test1() {
        Queue<String> queue = new LinkedList<>();
        System.out.println(queue.offer("asdf"));
        //获取并移除元素,队列为empty返回null
        System.out.println(queue.poll());
        //获得但不移除元素，队列为empty返回null
        System.out.println(queue.peek());
        queue.add("asdfasdf");
        //获得并移除元素, 队列为empty抛异常
        System.out.println(queue.element());

        ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue<String>(1);
        queue1.add("1");
        System.out.println("put");
        try {
            //添加元素，队列满则阻塞直到可以添加
            queue1.put("4");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("take");
        try {
            //获取并移除元素，队列为empty时阻塞直到队列有元素为止
            queue1.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue1.add("2");
        queue1.poll();
        queue1.add("34");
    }
}
