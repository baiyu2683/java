package edition8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

/**
 * Created by zhangheng on 2017/1/10.
 */
public class TestJava8 {

    @Test
    public void testMapCompute() {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 103; i++) {
            map.put(i, i);
        }
        Integer key = 10;
        map.compute(key, (k, value) -> {
            System.out.println(k + "-" + value);
            if (k % 2 == 0) {
                value = 1001;
            }
            return value;
        });
        System.out.println(key + "-" + map.get(key));
    }

    @Test
    public void testQueue() {
        ConcurrentLinkedQueue<Object> loseIdQueue = new ConcurrentLinkedQueue<>();
        loseIdQueue.add("asdf");
        loseIdQueue.add("qwe");
        System.out.println(loseIdQueue.poll());
        System.out.println(loseIdQueue.size());
        loseIdQueue.offer("zxcv");
        System.out.println(loseIdQueue.size());
    }

    @Test
    public void testMap() {
        int pageSize = 4;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 103; i++) {
            map.put(i, i);
        }
        for(int i = 1; i < 9; i++) {
            System.out.println(JSON.toJSON(map.entrySet().stream()
                    //过滤
                    .filter((v) -> {
                if (v.getKey() % 2 == 0)
                    return true;
                else
                    return false;
            })
                    //每个值进行处理
                    .map(entry->{
                JSONObject json = new JSONObject();
                json.put("name", entry.getKey());
                json.put("value", entry.getValue());
                return json;
            })
                    //分页： 1，游标设置         2, 个数设置
                    .skip((i - 1) * pageSize).limit(pageSize).collect(Collectors.toList())));
        }
    }
}
