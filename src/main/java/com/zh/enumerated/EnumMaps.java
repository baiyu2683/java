package com.zh.enumerated;

import java.util.EnumMap;
import java.util.Map;

import static com.zh.enumerated.AlarmPoints.*;

/**
 * EnumMap，例子使用了命令模式
 * 有次序
 * Created by zh on 2017-03-19.
 */
public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> enumMap = new EnumMap<>(AlarmPoints.class);
        enumMap.put(KITCHEN, ()->{
            System.out.println("Kitchen fire!");
        });
        enumMap.put(BATHROOM, ()->{
            System.out.println("Bathroom fire!");
        });
        enumMap.put(KITCHEN, ()->{
            System.out.println("Kitchen fire!");
        });
        for (Map.Entry<AlarmPoints, Command> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            entry.getValue().action();
        }
        try {
            enumMap.get(UTILITY).action();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * 命令接口
 */
interface Command{
    void action();
}
