package com.zh.typeinfo;

/**
 * Created by zhangheng on 16-8-28.
 */
class Candy{
    static {
        System.out.println("Loading Candy");
    }
};
class Gum{
    static {
        System.out.println("Loading Gum");
    }
};
class Cookie{
    static {
        System.out.println("Loading Cookie");
    }
};

/**
 * 类只在需要时才加载，下面例子中每个类都是使用了之后才发现加载的
 */
public class SweetShop {

    public static void main(String[] args){
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        System.out.println(Gum.class); // 这一行并没有真正加载类，没有执行static块
        System.out.println("After invoke class for Gum");
        try{
            Class.forName("com.zh.typeinfo.Gum");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");
        Cookie cookie = new Cookie();              //静态块只加载一次
        System.out.println("After creating Cookie 2");
    }
    //下面是输出
    /**
     inside main
     Loading Candy
     After creating Candy
     class com.zh.typeinfo.Gum
     After invoke class for Gum
     Loading Gum
     After Class.forName("Gum")
     Loading Cookie
     After creating Cookie
     After creating Cookie 2
     *
     */
}
