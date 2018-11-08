package com.zh;

/**
 * 变长参数例子解释java方法调用顺序
 *
 * 1.在不考虑对基本类型自动拆箱，以及可变长参数的情况下选取重载方法
 * 2.在1中没有找到适配方法时，在允许基本类型自动拆箱，不允许可变长参数的情况下选取重载方法
 * 3.在2中没有找到适配方法时，在允许基本类型自动拆箱，并且允许可变长参数的情况下选取重载方法
 */
public class TestVariableLengthParam {

    private static void invoke(Integer p1, int p2) {
        System.out.println("Integer int");
    }

    private static void invoke(int p1, Integer p2) {
        System.out.println("int Integer");
    }

    private static void invoke(Integer p1, Integer p2) {
        System.out.println("Integer Integer");
    }

    private static void invoke(Object obj, Object... args){
        System.out.println("1");
    }

    private static void invoke(String s, Object obj, Object... args) {
        System.out.println("2");
    }

    public static void main(String[] args) {
        invoke(null, 1);
        invoke(null, 1, 2);
        invoke(null, new Object[]{1});

        //
        invoke(Integer.valueOf(1), 1);
        invoke(1, Integer.valueOf(1));
        // 可以匹配两个方法，invoke(Integer i1, int i2)和invoke(int i1, Integer i2);
//        invoke(1, 1); // 报错
    }
}
