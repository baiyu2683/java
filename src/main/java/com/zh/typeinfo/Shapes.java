package com.zh.typeinfo;

import java.awt.image.CropImageFilter;
import java.util.*;

public class Shapes{
    public static void main(String[] args){
        List<Shape> shapeList = Arrays.asList(
                new Circle(), new Square(), new Triangle()
        );
        for(Shape shape : shapeList){
            shape.draw();
            Shape.rotate(shape);
        }
        //Rhomboid向上转型然后向下转型为父类另外一个子类
        Shape shape = new Rhomboid();
        Rhomboid rhomboid = (Rhomboid)shape;
        /**
         *   Exception in thread "main" java.lang.ClassCastException: com.zh.typeinfo.Rhomboid cannot be cast to com.zh.typeinfo.Circle
         */
//        Circle circle = (Circle) shape;
        shape.draw();
        rhomboid.draw();
//        circle.draw();
        //
        System.out.println(shape instanceof  Circle);
        if(shape instanceof Circle){
            Circle c1 = (Circle) shape;
            c1.draw();
        }

        //打印rhomboid的所有父类
        CommonUtils.printInherit(rhomboid);
        //打印所有的属性值
        CommonUtils.printField(rhomboid);
    }
}

abstract class Shape{
    void draw(){
        System.out.println(this + ".draw()");
    }
    public abstract String toString();
    public static void rotate(Shape shape){
        if(shape instanceof Circle){
            System.out.println("圆形不需要旋转");
        }else{
            System.out.println(shape + "转动了。");
        }
    }
}

class Circle extends Shape{
    public String toString(){
        return "Circle";
    };
}

class Square extends Shape{
    public String toString(){
        return "Square";
    };
}

class Triangle extends Shape{
    public String toString(){
        return "Triangle";
    }
}

class Rhomboid extends Shape{
    private Integer i = 1;
    private Integer n = 2;
    private String s = "3";
    private Date date = new Date();
    public String toString(){ return "Rhomboid"; }
}