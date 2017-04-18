import com.zh.string.regex.MyRegEx;
import jxtras.regex.Match;
import jxtras.regex.Regex;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * Created by zhangheng on 16-8-28.
 */
public class TestJava {

    @Test
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

    @Test
    public void test2(){
        new Thread(() -> {
            System.out.println("java 8");
        }).start();
    }

    @Test
    public void test3(){
        try {
            System.out.println(MyRegEx.addPrefix("zhangheng", "", "ss"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        Random random = new Random(47);
        for(int i = 0; i < 10; i++) {
            System.out.println(random.nextInt() + "-" + random.nextInt(100));
        }
    }

    @Test
    public void test5() throws IOException {
        File file = new File("f:/asdf.trs");
        System.out.println(file.getName());
    }

    @Test
    public void test6() {
//        System.out.println(TimeUnit.SECONDS.convert(100, TimeUnit.SECONDS));
        System.out.println(System.nanoTime());
        System.out.println(System.currentTimeMillis());
        System.out.println("-" + System.lineSeparator() + "-");
    }

    private static volatile int i = 0;

    private synchronized int getCounter() {
        return i++;
    }

    @Test
    public void test7() {
        File dirParent = new File("I:/xxx/xxx/xxx");
        File[] dirs = dirParent.listFiles();
        for(File dir : dirs) {
            File[] files = dir.listFiles();
            List<File> list = new ArrayList<>();
            Collections.addAll(list, files);
            list.parallelStream().filter((file -> {
                if(file.getName().contains("xxx")) return false;
                if(!file.getName().contains(".")) return false;
                return true;
            })).forEach((file)-> {
                int bytesum = 0;
                int byteread = 0;
                try {
                    InputStream inStream = new FileInputStream(file); //读入原文件
                    FileOutputStream fs = new FileOutputStream("I:/xxx/xxx/xxx/" + getCounter() + file.getName());
                    System.out.println("i:" + i);
                    byte[] buffer = new byte[2048];
                    int length;
                    while ((byteread = inStream.read(buffer)) != -1) {
                        bytesum += byteread; //字节数 文件大小
                        fs.write(buffer, 0, byteread);
                    }
                    fs.flush();
                    fs.close();
                    inStream.close();
                }catch (IOException e) {
                }
            });
            list.clear();
        }
    }

    @Test
    public void testRegex4j() throws IOException {
        FileInputStream fis = new FileInputStream("F:/temp/京剧猫吧_百度贴吧.htm");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String result = sb.toString();
        List<String> dataList = new ArrayList<>();
        String regex = "(<(li)(?:(?!data-field=)[^>])+data-field=[^>]+>(?>(?<o>)<\\2[^>]*>|(?<-o>)</\\2>|(?!</?\\2)[\\w\\W])*(?(o)(?!))</\\2>)";
        Match match = Regex.match(result, regex);
        while (match.success()) {
            dataList.add(match.groups().get(1).toString());
            match = match.nextMatch();
        }
        dataList.forEach((v)->{
            System.out.println(v);
        });
        System.out.println(dataList.size());
    }

    @Test
    public void testFile() throws IOException, ClassNotFoundException {
        String path = Class.forName(TestJava.class.getName()).getResource("testRead.json").getFile();
        File file = new File(path);
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void testCharset() {
        Charset charset = Charset.forName("819");
        System.out.println(charset.aliases());
        System.out.println(charset.displayName());
        System.out.println(charset.name());
    }

    @Test
    public void testByteBuffer() {
//        ByteBuffer buffer = ByteBuffer.allocate(24 * 1024);
//        buffer.wrap("Some text".getBytes());
//        CharBuffer charBuffer = buffer.asCharBuffer();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.wrap("Some text".toCharArray());
        System.out.println(charBuffer.toString());
    }

    @Test
    public void testProdicate() {
        boolean re = true;
        re |= false;
        System.out.println(re);
    }

}