package com.zh.annotations.apt;

/**
 * Created by zhheng on 2016/4/10.
 */
@ExtractInterface("IMultipier")
public class Multiplier {
    public int multiplier(int x, int y){
        int total = 0;
        for(int i = 0; i < x; i++){
            total = add(total, y);
        }
        return total;
    }

    private int add(int x, int y){
        return x + y;
    }

    public static void main(String[] args){
        Multiplier m = new Multiplier();
        System.out.println("11 * 16 = " + m.multiplier(11, 16));
    }
}
