package com.zh.typeinfo;

/**
 * Created by zhangheng on 16-8-28.
 */

interface HasBatteries{}
interface Waterproof{}
interface Shoots{}
//test2
interface zhheng{}

class Toy{
    Toy(){};
    Toy(int i){}
}
class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots, zhheng{
    FancyToy(){super(1);}
}

public class ToyTest {
    static void printInfo(Class cc){
        System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name:" + cc.getSimpleName());
        System.out.println("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args){
        Class c = null;
        try{
            c = Class.forName("com.zh.typeinfo.FancyToy");
        }catch (ClassNotFoundException e){
            System.out.println("Can't find FancyToy");
            e.printStackTrace();
        }
        printInfo(c);
        System.out.println("实现的接口");
        for(Class face : c.getInterfaces()){
            printInfo(face);
        }
        System.out.println("实现的接口打印完毕");
        Class up = c.getSuperclass();
        Object obj = null;
        try{
            //需要默认无参数构造函数，没有会报错 Cannot instantiate
             obj = up.newInstance();
        }catch (InstantiationException e){
            System.out.println("Cannot instantiate");
            System.exit(1);
        }catch (IllegalAccessException e){
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
    /**
     Class name: com.zh.typeinfo.FancyToy is interface? [false]
     Simple name:FancyToy
     Canonical name: com.zh.typeinfo.FancyToy
     实现的接口
     Class name: com.zh.typeinfo.HasBatteries is interface? [true]
     Simple name:HasBatteries
     Canonical name: com.zh.typeinfo.HasBatteries
     Class name: com.zh.typeinfo.Waterproof is interface? [true]
     Simple name:Waterproof
     Canonical name: com.zh.typeinfo.Waterproof
     Class name: com.zh.typeinfo.Shoots is interface? [true]
     Simple name:Shoots
     Canonical name: com.zh.typeinfo.Shoots
     实现的接口打印完毕
     Class name: com.zh.typeinfo.Toy is interface? [false]
     Simple name:Toy
     Canonical name: com.zh.typeinfo.Toy
     */
}
