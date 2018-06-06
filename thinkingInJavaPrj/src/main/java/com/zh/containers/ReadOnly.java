package com.zh.containers;

import java.util.*;

/**
 * Created by zh on 2017-04-24.
 */
public class ReadOnly {
    static Collection<String> data = new ArrayList<>();
    static {
        data.addAll(Arrays.asList("zhangsan,lisi,wangwu,zhangliu,liuqi".split(",")));
    }
    public static void main(String[] args) {
        Collection<String> collection = Collections.unmodifiableCollection(new ArrayList<>(data));
        System.out.println(collection);
//        collection.add("one");

        List<String> a = Collections.unmodifiableList(new ArrayList<>(data));
        ListIterator<String> lit = a.listIterator();
        System.out.println(lit.next());
//        lit.add("one");

        Set<String> s = Collections.unmodifiableSet(new HashSet<>(data));
        System.out.println(s);
//        s.add("one");

        Set<String> ss = Collections.unmodifiableSortedSet(new TreeSet<>(data));
        System.out.println(ss);
//        ss.add("one");

        Map<String, String> map = new HashMap<>();
        map.put("beijing", "beiojing1");
        map.put("lijiang", "lijiang");
        map.put("guilin", "guilin");
        Map<String, String> m = Collections.unmodifiableMap(map);
        System.out.println(m);
//        m.put("zhengzhou", "zhengzhou");

        Map<String, String> sm = Collections.unmodifiableSortedMap(
                new TreeMap<>(map)
        );
    }
}
