package edition8;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhangheng on 2017/1/11.
 */
public class Java8Tester {

    @Test
    public void testSort() {
        List<String> names1 = new ArrayList<>();
        names1.add("Adele ");
        names1.add("Tom ");
        names1.add("Jesery ");

        List<String> names2 = new ArrayList<>();
        names2.add("Adele ");
        names2.add("Tom ");
        names2.add("Jesery ");

        java7Sort(names1);
        System.out.println(names1);
        java8Sort(names2);
        System.out.println(names2);
    }

    private void java7Sort(List<String> list){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.compareTo(s1);
            }
        });
    }

    private void java8Sort(List<String> list) {
        Collections.sort(list, (s1, s2) -> {
            return s2.compareTo(s1);
        });
    }
}
