package com.zh;

import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultEventExecutorChooserFactory;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorChooserFactory;
import org.junit.Test;

public class TestChooser {

    @Test
    public void test1() {
        EventExecutorChooserFactory.EventExecutorChooser eventExecutorChooser = DefaultEventExecutorChooserFactory
                .INSTANCE.newChooser(new EventExecutor[]{
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                            new DefaultEventExecutor(),
                                        });
        for (int i = 0 ; i < 10 ; i++) {
            EventExecutor eventExecutor = eventExecutorChooser.next();
            System.out.print(eventExecutor);
            System.out.println();
        }
    }

    /**
     * 算术运算符优先级高于位运算符
     */
    @Test
    public void test2() {
        System.out.println(9 & (8 - 1));
    }
}
