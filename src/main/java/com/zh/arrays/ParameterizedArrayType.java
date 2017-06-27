package com.zh.arrays;

/**
 * Created by zh on 2017-06-27.
 */
public class ParameterizedArrayType {
    public static void main(String[] args) {
        Integer[] ints = {1,2,3,4,5};
        Double[] doubles = {1.1,2.2,3.3,4.4,5.5};
        Integer[] ints2 = new ClassParameter<Integer>().f(ints);
        Double[] doubles1 = new ClassParameter<Double>().f(doubles);
        ints2 = MethodParameter.f(ints);
        doubles =MethodParameter.f(doubles);
    }
}
//方法根据类传递的泛型确定返回值类型
class ClassParameter<T> {
    public T[] f(T[] arg) {
        return arg;
    }
}
//方法根据返回值类型确定类型
class MethodParameter {
    public static <T> T[] f(T[] arg) {
        return arg;
    }
}
