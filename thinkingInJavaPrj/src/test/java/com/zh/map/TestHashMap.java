package com.zh.map;

public class TestHashMap {

    public static void main(String[] args) {

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1; // 减一，为了对已经是2的次幂的数正确求值，对大于0且不是2的次幂的数不会对最高位的1有影响
        n |= n >>> 1; // 将最高位的1后面一位变为1，其他位不管
        n |= n >>> 2; // 将最高两位的1，后面两位变为1，其他位不管
        n |= n >>> 4; // 按上面类推
        n |= n >>> 8; // 按上面类推
        n |= n >>> 16; // 按上面类推，此时最高位的1到最末尾都是1，及001111....11这种格式
        int result = (n < 0) ? 1 : (n >= 2 << 30) ? 2 << 30 : n + 1; // 最后加1，得到2的次幂
        return result;
    }
}
