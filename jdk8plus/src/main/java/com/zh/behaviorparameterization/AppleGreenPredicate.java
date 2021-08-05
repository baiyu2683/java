package com.zh.behaviorparameterization;

import com.zh.entity.Apple;
import com.zh.enums.Color;

public class AppleGreenPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}
