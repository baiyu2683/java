package com.zh.containers;

import com.zh.containers.testframework.Test;
import com.zh.containers.testframework.TestParam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 未完待续。。。
 * Created by zh on 2017-04-23.
 */
public class ListPerformance {
    static Random random = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<>();
    static List<Test<LinkedList<Integer>>> qTests = new ArrayList<>();
    static {
        tests.add(new Test<List<Integer>>("add") {
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize =tp.size;
                for(int i = 0; i < loops; i++){
                    list.clear();
                    for(int j = 0; j < listSize; j++)
                        list.add(j);
                }
                return loops * listSize;
            }
        });
    }
}
