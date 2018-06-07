package com.zh;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区和运行时常量池的OutOfMemory
 * 运行时常量池是包含在方法区的
 *
 *  设置永久带大小和最大大小
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @Author zh2683
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {


        //使用List保持着常量池引用，避免Full GC回收常量池的行为
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    public static void main1(String[] args) {

        String string1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(string1.intern() == string1);

        String string2 = new StringBuilder("ja").append("va").toString();
        System.out.println(string2.intern() == string2);
    }
}
