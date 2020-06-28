package com.zh;

public class TestMoveBits {

    public static void main(String[] args) {
        // 为了将最高位的1复制到所有低位，即将所有低位都变成1。最后+1之后变成最近的2的倍数
        int newCapacity = 0;
        newCapacity |= newCapacity >>>  1;
        newCapacity |= newCapacity >>>  2;
        newCapacity |= newCapacity >>>  4;
        newCapacity |= newCapacity >>>  8;
        newCapacity |= newCapacity >>> 16; // int最高32位
        newCapacity ++;
        System.out.println(newCapacity + "-" + Integer.MAX_VALUE);
    }
}
