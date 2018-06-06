package com.zh.enumerated;

/**
 * 使用Class类中的getEnumConstants方法可以经过向上转型的枚举类的所有枚举项
 * Created by zh on 2017-03-18.
 */
public class UpcastEnum {

    public static void main(String[] args) {
        Search[] vals = Search.values();
        Enum e = Search.HITHER; //向上转型,写法有些奇怪，必须获得一个具体的枚举
        //e.values()没有此方法，无法获得所有的枚举项
        //使用Class类中的getEnumConstants方法可以获得所有枚举项
        for (Enum en : e.getClass().getEnumConstants())
            System.out.println(en);
    }
}

enum Search {
    HITHER, YON
}
