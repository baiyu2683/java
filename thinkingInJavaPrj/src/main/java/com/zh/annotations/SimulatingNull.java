package com.zh.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhheng on 2016/3/23
 * <ul>
 *     <li>注解必须有值，要么定义有默认值，要么在使用时赋值</li>
 *     <li>对于非基本元素，默认值和赋的值都不能为null，可以像下面定义的使用一些特殊的值表示不存在</li>
 * </ul>
 *
 * 注解定义允许的类型：
 * <ul>
 *     <li>八种基本类型,byte,short,int,long,float,double,char,boolean</li>
 *     <li>enum</li>
 *     <li>Class</li>
 *     <li>String</li>
 *     <li>Annotion</li>
 *     <li>上述几种类型的数组</li>
 * </ul>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {
    public int id() default -1;
    public String description() default "";
}
