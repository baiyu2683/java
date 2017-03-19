package com.zh.enumerated;

/**
 * Created by zh on 2017-03-19.
 */
public enum OverrideConstantSpecific {
    NUT, BOLT,
    WASHER {
        void f() {
            System.out.println("Overridden method");
        }
    };
    void f() {
        System.out.println("default behavior");
    }

    public static void main(String[] args) {
        for (OverrideConstantSpecific overrideConstantSpecific : values()) {
            System.out.printf(overrideConstantSpecific + " : ");
            overrideConstantSpecific.f();
        }
    }
}
