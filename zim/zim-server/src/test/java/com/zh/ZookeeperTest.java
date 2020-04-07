package com.zh;

import com.zh.netty.zk.ServerRegister;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangheng
 * @date 2019/12/24
 */
public class ZookeeperTest {

    @Test
    public void testRegister() throws InterruptedException {
        new ServerRegister().register();
        new CountDownLatch(1).await();
    }
}
