package com.zh.array;

import org.junit.Test;

import java.util.Arrays;

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
}
