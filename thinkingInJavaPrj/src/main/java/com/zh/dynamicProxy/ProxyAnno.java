package com.zh.dynamicProxy;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProxyAnno {
    String value() default "";
}
