package edition8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.typeinfo.pets.Cat;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    @Test
    public void testReplac() {
        String name1 = "{\"INTERVAL\":\"600\",\"BREADTH\":\"_$X_//body/div[@class='wp'][1]/div[@class='boardnav'][1]/div[@class='wp cl'][1]/div[@class='mn'][1]/div[@class='bm bw0 pgs cl'][2]/span[1]/div[@class='pg'][1]/a[contains(.,'下一页')]/@href\",\"TEMPLATE\":{\"BREADTH\":\"_$X_//body/div[@class='wp'][1]/div[@class='boardnav'][1]/div[@class='wp cl'][1]/div[@class='mn'][1]/div[@class='bm bw0 pgs cl'][2]/span[1]/div[@class='pg'][1]/a[contains(.,'下一页')]/@href\",\"国内论坛_瑞安在线_咨询求助_瑞安百晓-1\":{\"DEPTH\":\"_$X_//body/div[@class='wp'][1]/div[@class='boardnav'][1]/div[@class='wp cl'][1]/div[@class='mn'][1]/div[@class='tl bm bmw'][1]/div[@class='bm_c'][1]/form[1]/table[1]/tbody/tr[1]/th[@class='new'][1]/a[@class='s xst'][1]/@href_$MAP_\\\\s|\",\"FILTER\":\"_$X_//body/div[@class='wp'][1]/div[@class='boardnav'][1]/div[@class='wp cl'][1]/div[@class='mn'][1]/div[@class='tl bm bmw'][1]/div[@class='bm_c'][1]/form[1]/table[1]/tbody/tr[1]/th[@class='new'][1]/a[@class='s xst'][1]/@href_$MAP_\\\\s|\"}},\"DOWNLOADER\":\"HTTPCLIENT\",\"CLICK\":\"\"}";
        String str = "_$MAP_\\\\s|";
        System.out.println(name1.replace(str, ""));
    }

    @Test
    public void testSignal() {
        String config1 = "{\"INTERVAL\":\"-1\",\"BREADTH\":\"_$X_//body/div[@class='wp'][1]/div[@class='wp cl'][1]/div[@class='pgs mtm mbm cl'][1]/div[@class='pg'][1]/a[contains(.,'下一页')]/@href\",\"TEMPLATE\":{\"CUSTOMKEYS\":[{\"FILTER\":\"BLOCK_$R_<em id=\\\"authorposton.*?>发表于([\\\\S\\\\s]*?)<\\\\/em>_$NULL_\",\"IR_SITENAME\":\"瑞安在线\",\"IR_CHANNEL\":\"咨询求助_瑞安百晓\",\"IR_GROUPNAME\":\"国内论坛\",\"IR_URLTITLE\":\"_$X_//body/div[@class='wp'][1]/div[@class='wp cl'][1]/div[@class='pl bm'][1]/table[1]/tbody[1]/tr[1]/td[@class='plc ptm pbn vwthd'][1]/h1[@class='ts'][1]/span[1]/text()\",\"BLOCK\":\"_$X_//body/div[@class='wp'][1]/div[@class='wp cl'][1]/div[@class='pl bm'][1]/div\",\"IR_URLTIME\":\"BLOCK_$R_<em id=\\\"authorposton.*?>发表于([\\\\S\\\\s]*?)<\\\\/em>_$NULL__$TIME_\",\"IR_AUTHORS\":\"BLOCK_$R_<div class=\\\"i y\\\">[\\\\s\\\\S]*?<strong>([\\\\S\\\\s]*?)<\\\\/strong>_$NULL_\",\"IR_CONTENT\":\"BLOCK_$R_<div class=\\\"pcb\\\">([\\\\s\\\\S]*?)<td class=\\\"plc plm\\\">_$NULL_\",\"IR_BBSNUM\":\"BLOCK_$X_/div/table[@class='plhin'][1]/tbody[1]/tr[1]/td[@class='plc'][1]/div[@class='pi'][1]/strong[1]/a[1]\",\"IR_NRESERVED3\":\"_$X_//body/div[@class='wp'][1]/div[@class='wp cl'][1]/div[@class='pl bm'][1]/table[1]/tbody[1]/tr[1]/td[@class='pls ptn pbn'][1]/div[@class='hm ptn'][1]/span[@class='xi1'][2]/text()_$REG_(\\\\d+)\",\"IR_NRESERVED2\":\"_$X_//body/div[@class='wp'][1]/div[@class='wp cl'][1]/div[@class='pl bm'][1]/table[1]/tbody[1]/tr[1]/td[@class='pls ptn pbn'][1]/div[@class='hm ptn'][1]/span[@class='xi1'][1]/text()_$REG_(\\\\d+)\"}]},\"DOWNLOADER\":\"HTTPCLIENT\",\"CLICK\":\"\"}";
        String name = "国内论坛_瑞安在线_咨询求助_瑞安百晓-1";
        JSONObject config = JSON.parseObject(config1);
        System.out.println("[" + config.toJSONString() + ",");
        //0. 补名字
        config.put("NAME", name);
        //细览页名字
        String subName = name + "-1";
        JSONObject template = config.getJSONObject("TEMPLATE");
        //1. 细览页customkey中有size小于或等于3的就删除
        if(name.endsWith("-1")) {
            JSONArray customkeys = template.getJSONArray("CUSTOMKEYS");
            JSONArray newCustomkeys = new JSONArray();
            for(Object o : customkeys) {
                JSONObject obj = (JSONObject)o;
                if(obj.keySet().size() > 3) {
                    newCustomkeys.add(obj);
                }
            }
            String mapSuffix = "_$MAP_楼主|1;沙发|2;板凳|3";
            String numSuffix = "_$REG_(\\d+)";
            //FILTER后面加_$TIME_
            for(Object o : newCustomkeys) {
                JSONObject obj = (JSONObject)o;
                String filter = obj.getString("FILTER");
                String urltime = obj.getString("IR_URLTIME");
                filter = urltime;
                obj.put("FILTER", filter);

                //针对论坛细览页
                String BBSNUM = obj.getString("IR_BBSNUM");
                String NRESERVED2 = obj.getString("IR_NRESERVED2");
                String NRESERVED3 = obj.getString("IR_NRESERVED3");
                if(BBSNUM != null) {
                    BBSNUM = BBSNUM.replace(mapSuffix, "");
                    BBSNUM = BBSNUM.replace(numSuffix, "");
                    BBSNUM += mapSuffix + numSuffix;
                    obj.put("IR_BBSNUM", BBSNUM);
                }
                if(NRESERVED2 != null && !NRESERVED2.endsWith(numSuffix)) {
                    NRESERVED2 += NRESERVED2 + numSuffix;
                    obj.put("IR_NRESERVED2", NRESERVED2);
                }
                if(NRESERVED3 != null && !NRESERVED3.endsWith(numSuffix)) {
                    NRESERVED3 += NRESERVED3 + numSuffix;
                    obj.put("IR_NRESERVED3", NRESERVED3);
                }
            }
            template.put("CUSTOMKEYS", newCustomkeys);
        }
        //2. TEMPLATE中的BREADTH 删除
        template.remove("BREADTH");
        //3. 概览页中FILTER键加_$FORUM__$TIME_后缀, 去掉_$MAP_\\s|
        String oldChar = "_$MAP_\\s|";
        if(!name.endsWith("-1")) {
            JSONObject obj = template.getJSONObject(subName);
            if(obj != null && obj.containsKey("FILTER")) {
                String filter = obj.getString("FILTER");
                filter = filter.replace(oldChar, "");
                filter += "_$FORUM__$TIME_";
                obj.put("FILTER", filter);
            }
        }
        config.put("TEMPLATE", template);
        System.out.println(config + "]");
    }

    @Test
    public void testClone() {
    }

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

    @Test
    public void TestDefault() {
        Show show = new MyShow();
        show.show();
    }
    interface Show {
        default void show() {
            System.out.println("hello world");
        }
    }
    static class MyShow implements Show {
//        public void show() {
//            System.out.println("hello MyShow");
//        }
    }

    @Test
    public void testRandom() {
        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
            double num = Math.random() * 10;
            //round 对小数四舍五入， floor 丢掉小数
            System.out.println(num + "-" + Math.round(num) + "-" + Math.floor(num));
        }
    }
}
