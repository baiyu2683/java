package com.zh.innerclasses;

/**
 * 接口中可以定义嵌套类
 * Created by zh on 2017-04-16.
 */
public interface ClassInInterface {
    void howdy();
    class Test implements ClassInInterface {
        @Override
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}
