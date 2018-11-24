package com.zh.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

public class TestArray {

    /**
     * 按固定长度切分数组
     */
    @Test
    public void testSplit() {
        int count = 4103;
        int size = 5;
        String[] userIds = new String[count];
        for (int i = 0 ; i < count ; i++) {
            userIds[i] = String.valueOf(i);
        }
        int readIndex = 0;
        int length = userIds.length;
        while (readIndex < userIds.length) {
            int tempLength = Math.min(size, length - readIndex);
            String[] userIdsTemp = new String[tempLength];
            System.arraycopy(userIds, readIndex, userIdsTemp, 0, tempLength);
            readIndex += size;
            System.out.println(Arrays.toString(userIdsTemp));
        }
    }

    @Test
    public void test2() {
        testArray(new String[]{"1"});
        testArray(new Date[]{new Date(), new Date()});
    }

    /**
     * 动态获取泛型数组的元素类型
     * @param a
     * @param <T>
     */
    private <T> void testArray(T[] a) {
        System.out.println(a.getClass().getComponentType());
    }
}
