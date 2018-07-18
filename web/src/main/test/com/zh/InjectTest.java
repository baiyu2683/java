package com.zh;

import com.zh.dao.BaseDao;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author: Administrator <br/>
 * Date: 2018-07-18 <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class InjectTest {

    private Logger logger = Logger.getLogger(InjectTest.class);

    @Autowired
    private BaseDao baseDao;

    @Test
    public void test() {
        logger.info(logger);
    }
}
