package com.zh.behaviorparameterization;

import com.zh.entity.Apple;

/**
 * 苹果重量行为抽象
 */
public class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150f;
    }
}
