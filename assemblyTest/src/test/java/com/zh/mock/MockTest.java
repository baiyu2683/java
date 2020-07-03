package com.zh.mock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * 测试mock使用
 */
public class MockTest {


    @Test
    public void testMockBase() {
        List<String> mockList = mock(ArrayList.class);
        Assert.assertTrue(mockList instanceof ArrayList);

        Mockito.when(mockList.add("张三")).thenReturn(true);
        Mockito.when(mockList.size()).thenReturn(10);
        Assert.assertTrue(mockList.add("张三"));
        Assert.assertFalse(mockList.add("李四"));
        Assert.assertEquals(mockList.size(), 10);
        System.out.println(mockList.get(0));
    }

    @Test
    public void testInvokeMethodCount() {
        List mockList = mock(ArrayList.class);
        mockList.add("once");
        mockList.add("twice");
        mockList.add("twice");

        mockList.add("three times");
        mockList.add("three times");
        mockList.add("three times");
        // 调用了add("once")
        Mockito.verify(mockList).add("once");
        // 调用了一次add("once");
        Mockito.verify(mockList, Mockito.times(1)).add("once");
        // 从来没有调用过add("never")
        Mockito.verify(mockList, Mockito.never()).add("never");
        // 最少一次
        Mockito.verify(mockList, Mockito.atLeastOnce()).add("three times");
        // 最少两次
        Mockito.verify(mockList, Mockito.atLeast(2)).add("three times");
        // 最多四次
        Mockito.verify(mockList, Mockito.atMost(4)).add("three times");
    }

    @Test
    public void testMethodTime() {
        TestTimeOut testTimeOut = mock(TestTimeOut.class);
        testTimeOut.show();
        Mockito.verify(testTimeOut, Mockito.timeout(10)).show();
    }

    class TestTimeOut {

        public void show() {
            for (int i = 0 ; i < Integer.MAX_VALUE ; i++) {
                BigDecimal bigDecimal = new BigDecimal(i);
                bigDecimal.setScale(100, BigDecimal.ROUND_FLOOR)
                        .add(BigDecimal.valueOf(100000))
                        .divide(BigDecimal.valueOf(0.111));
            }
        }
    }

    @Test
    public void testStubing() {
        List<String> list = mock(ArrayList.class);

        Mockito.when(list.get(0)).thenReturn("first");
//        Mockito.when(list.get(1)).thenThrow(new RuntimeException());

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(9999));

        verify(list).get(0);
    }

    /**
     * 参数匹配器
     */
    @Test
    public void testArgMatcher() {
        List mockList = mock(ArrayList.class);

        when(mockList.get(anyInt())).thenReturn("element");
        // 匹配的才返回指定的值
        when(mockList.contains(argThat(b -> {
            if (b == null) {
                return false;
            } else {
                return true;
            }
        }))).thenReturn(true);

        System.out.println(mockList.get(0000));
        verify(mockList).get(anyInt());

        System.out.println(mockList.contains(1));
    }

    /**
     * 测试void方法
     */
    @Test
    public void testVoidReturnMethod() {
        List<String> list = mock(ArrayList.class);
        doNothing().when(list).clear();
        list.clear();
        verify(list).clear();
    }

}
