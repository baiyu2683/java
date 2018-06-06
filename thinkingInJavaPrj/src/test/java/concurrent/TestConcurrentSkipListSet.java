package concurrent;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zh on 2017-08-09.
 */
public class TestConcurrentSkipListSet {

    private ConcurrentSkipListSet<Entry<Long, String>> scores = new ConcurrentSkipListSet<>((o1, o2) -> {
        //排除空的
//        if (o1.getValue() == null || "".equals(o1.getValue())) {
//            return 0;
//        }
//        if (o2.getValue() == null || "".equals(o2.getValue())) {
//            return 0;
//        }
        int value = o1.getValue().compareTo(o2.getValue());
        if(value == 0)
            System.out.println(o1.getValue() + "-" + o2.getValue());
        //队列中不存在的,再根据优先级比较
//        //排除重复的
//        if(value == 0 && key == 0) return 0;
        return o1.getKey().compareTo(o2.getKey());
    });

    @Test
    public void testOrder() throws InterruptedException, IOException {
        List<String> lists = IOUtils.readLines(new FileInputStream("C:\\Users\\zh\\Desktop\\a (2).txt"));
        lists.stream().forEach(url -> {
            scores.add(new SimpleEntry<>(30l, url.trim()));
        });
//        System.out.println(scores.add(new SimpleEntry<>(30l, "http://weibo.com/1748594631/FgaDYFGuz")));
        System.out.println(scores.contains(new SimpleEntry<>(30l, "http://weibo.com/1748594631/FgaDYFGuz")));
//        System.out.println(scores.headSet(new SimpleEntry<>(30l, null), true).size());
    }

    @Test
    public void testMap() throws InterruptedException, IOException {

    }
}
