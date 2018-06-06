package com.zh.enumerated;

import java.lang.reflect.Method;
import java.util.EnumSet;

import static com.zh.enumerated.AlarmPoints.*;
/**
 * enumSet,有次序的，次序是由所持有的枚举类型定义的次序锁决定的，而不是添加顺序
 * Created by zh on 2017-03-19.
 */
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> pointss = EnumSet.noneOf(AlarmPoints.class); //Empty set
        pointss.add(BATHROOM);  //添加一个
        System.out.println(pointss);
        pointss.addAll(EnumSet.of(STAIR1, STAIR2, KITCHEN));
        System.out.println(pointss);
        pointss = EnumSet.allOf(AlarmPoints.class); //添加所有
        System.out.println(EnumSet.range(OFFICE1, OFFICE4));
        pointss.removeAll(EnumSet.range(OFFICE1, OFFICE4));  //移除一部分
        System.out.println(pointss);
        pointss = EnumSet.complementOf(pointss); //所有不在enumSet中的此类型的元素
        System.out.println(pointss);
        for (Method method : EnumSet.class.getMethods()) {
            System.out.print(method.getName() + ",");
        }
    }
}
