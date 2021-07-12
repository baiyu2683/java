package com.zh;

public class TestMoveBits {

    public static void main(String[] args) {
        // 首先将最高位得1向右移动一位，然后与原先得自己或操作，则最高两位都变成了1，相当于复制了最高位得1到低位。
        // 为了将最高位的1复制到所有低位，即将所有低位都变成1。最后+1之后变成最近的2的倍数
        int newCapacity = 1;
        newCapacity |= newCapacity >>>  1;
        newCapacity |= newCapacity >>>  2;
        newCapacity |= newCapacity >>>  4;
        newCapacity |= newCapacity >>>  8;
        newCapacity |= newCapacity >>> 16; // int最高32位
        newCapacity ++;
        System.out.println(newCapacity + "-" + Integer.MAX_VALUE);
    }
}
