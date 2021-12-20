package com.zh.bridge;

public class MainEntry {

    public static void main(String[] args) {
        Implementor ia = new ImplementorA();
        Implementor ib = new ImplementorB();
        Abstraction a = new RefinedAbstractionA(ia);
        a.operation();
        a = new RefinedAbstractionA(ib);
        a.operation();
        a = new RefinedAbstractionB(ia);
        a.operation();
        a = new RefinedAbstractionB(ib);
        a.operation();
    }
}
