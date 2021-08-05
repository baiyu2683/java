package com.zh.behaviorparameterization;

import com.zh.entity.Apple;

// 一个谓词接口，定义抽象行为
public interface ApplePredicate {
    boolean test(Apple apple);
}
