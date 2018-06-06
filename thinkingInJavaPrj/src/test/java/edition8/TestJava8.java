package edition8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.typeinfo.pets.Cat;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @Test
    public void testHashMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("", "");
    }

    @Test
    public void test1() {
//        String s = "[{\"IR_SCREEN_NAME\":\"喵克Lee\",\"IR_STATUS_CONTENT\":\"本次爵士音乐节之旅由@TacoBell塔可贝尔 赞助 \",\"IR_CREATED_AT\":\"2017.10.14 16:03:09\",\"IR_URLNAME\":\"http://weibo.com/1804994770/FqlgmcEI5\",\"IR_MID\":\"4162782663016429\",\"IR_UID\":\"1804994770\",\"IR_VIA\":\"Welike\",\"IR_THUMBNAIL_PIC\":\"http://wx2.sinaimg.cn/thumb150/6b9608d2ly1fkhu0o8h41j21kw0w0x6s.jpg;http://wx3.sinaimg.cn/thumb150/6b9608d2ly1fkhu3gud63j21kw16okjn.jpg\",\"IR_RTTCOUNT\":\"0\",\"IR_COMMTCOUNT\":\"0\",\"IR_APPROVE_COUNT\":\"2\",\"IR_RETWEETED_RTTCOUNT\":\"0\",\"IR_RETWEETED_COMMTCOUNT\":\"0\",\"IR_RETWEETED_APPROVE_COUNT\":\"0\",\"DBNAME\":\"DCWeiboArticle\",\"IR_SITENAME\":\"新浪\",\"IR_GROUPNAME\":\"微博\",\"IR_CHANNEL\":\"weiboarticle\",\"IR_ROOT\":\"http://weibo.com/1804994770/FqlgmcEI5\",\"IR_ROOTURL\":\"http://weibo.com/1804994770/FqlgmcEI5\",\"IR_PROJECT\":\"百胜舆情项目监测\",\"IR_REALURL\":\"http://weibo.com/1804994770/FqlgmcEI5\",\"IR_LASTTIME\":\"2017.10.15 09:59:43\",\"IR_IMAGEFLAG\":\"0\",\"IR_URLSIZE\":\"103821\",\"IR_PKEY\":\"15207904543941406934\",\"IR_HKEY\":\"15207904543941406934\"}]";
//        String s = "[{\"IR_SCREEN_NAME\":\"有理麻花\",\"IR_STATUS_CONTENT\":\"【陕餐饮企业收集餐厨废弃物 需建立相关制度及台账】原标题：我省进一步加强“地沟油”治理 餐饮企业收集餐厨废弃物 要建立相关制度及台账本报讯（记者 康乔娜）为严厉打击制售“地沟油”违法犯罪行为，建立健全综 （分享自 @凤凰网） O陕餐饮企业收集餐厨废弃物 需建立相关制度及... \",\"IR_CREATED_AT\":\"2017.10.14 17:33:15\",\"IR_URLNAME\":\"http://weibo.com/1726362687/FqlQV9vBW\",\"IR_MID\":\"4162805332266468\",\"IR_UID\":\"1726362687\",\"IR_VIA\":\"凤凰网\",\"IR_THUMBNAIL_PIC\":\"http://r.sinaimg.cn/large/tc/p2_ifengimg_com/c9cb3e2077637c4d31c2c32ac56cf0b9.png\",\"IR_RTTCOUNT\":\"6\",\"IR_COMMTCOUNT\":\"1\",\"IR_APPROVE_COUNT\":\"0\",\"IR_RETWEETED_RTTCOUNT\":\"0\",\"IR_RETWEETED_COMMTCOUNT\":\"0\",\"IR_RETWEETED_APPROVE_COUNT\":\"0\",\"DBNAME\":\"DCWeiboArticle\",\"IR_SITENAME\":\"新浪\",\"IR_GROUPNAME\":\"微博\",\"IR_CHANNEL\":\"weiboarticle\",\"IR_ROOT\":\"http://weibo.com/1726362687/FqlQV9vBW\",\"IR_ROOTURL\":\"http://weibo.com/1726362687/FqlQV9vBW\",\"IR_PROJECT\":\"百胜舆情项目监测\",\"IR_REALURL\":\"http://weibo.com/1726362687/FqlQV9vBW\",\"IR_LASTTIME\":\"2017.10.15 10:00:37\",\"IR_IMAGEFLAG\":\"0\",\"IR_URLSIZE\":\"104868\",\"IR_PKEY\":\"4555112082845952387\",\"IR_HKEY\":\"4555112082845952387\"}]";
        String s = "[{\"IR_SCREEN_NAME\":\"观察者网\",\"IR_STATUS_CONTENT\":\"【香港机场女店员叫嚣：“我是香港人！永远不会是中国人”】8月25日，一名大陆女子在香港机场麦当劳点餐，遭到店员无理驱赶，其朋友上前理论时，店员叫嚣“香港人永远不会是中国人！”视频被上传到网络后，有人质疑是大陆女子“插队”在先，随后当事人回应，自己并未插队，排了10分钟队到自己时，店员一直说粤语，自己听不懂，反应较慢，于是店员就上来赶她们，态度恶劣，才发生了争执。L微丢的秒拍视频\",\"IR_CREATED_AT\":\"2017.10.14 13:20:03\",\"IR_URLNAME\":\"http://weibo.com/1887344341/Fqkc9dfFg\",\"IR_MID\":\"4162741613158482\",\"IR_UID\":\"1887344341\",\"IR_VIA\":\"微博 weibo.com\",\"IR_THUMBNAIL_PIC\":\"http://dslb.cdn.krcom.cn/stream/NEvpz6IhWIUfHHMZADWVhaLcyu4h1Jj4jhQXCw___32768.jpg\",\"IR_VIEDO_URL\":\"http://gslb.miaopai.com/stream/NEvpz6IhWIUfHHMZADWVhaLcyu4h1Jj4jhQXCw__.mp4?yx=&refer=weibo_app&mpflag=8&mpr=1508004143&Expires=1508036346&ssig=0JgvB6kGKZ&KID=unistore,video\",\"IR_RTTCOUNT\":\"7103\",\"IR_COMMTCOUNT\":\"17642\",\"IR_APPROVE_COUNT\":\"26040\",\"IR_RETWEETED_RTTCOUNT\":\"0\",\"IR_RETWEETED_COMMTCOUNT\":\"0\",\"IR_RETWEETED_APPROVE_COUNT\":\"0\",\"DBNAME\":\"DCWeiboArticle\",\"IR_SITENAME\":\"新浪\",\"IR_GROUPNAME\":\"微博\",\"IR_CHANNEL\":\"weiboarticle\",\"IR_ROOT\":\"http://weibo.com/1887344341/Fqkc9dfFg\",\"IR_ROOTURL\":\"http://weibo.com/1887344341/Fqkc9dfFg\",\"IR_PROJECT\":\"百胜舆情项目监测\",\"IR_REALURL\":\"http://weibo.com/1887344341/Fqkc9dfFg\",\"IR_LASTTIME\":\"2017.10.15 09:59:21\",\"IR_IMAGEFLAG\":\"0\",\"IR_URLSIZE\":\"99870\",\"IR_PKEY\":\"18102754948590002502\",\"IR_HKEY\":\"18102754948590002502\"}]";
    }

    @Test
    public void testPath() {
        String path = TestJava8.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println(path);
    }

    @Test
    public void testTime() {
        Instant instant = Instant.ofEpochMilli((new Date()).getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINESE)));
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void testAscii() throws UnsupportedEncodingException {
    }

    @Test
    public void testBit() {
        BitSet bitSet = new BitSet();
        bitSet.set(10);
        System.out.println(bitSet.get(10));
    }
}
