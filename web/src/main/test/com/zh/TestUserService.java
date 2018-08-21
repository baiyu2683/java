package com.zh;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zh.domain.User;
import com.zh.domain.User.Sex;
import com.zh.service.UserService;
import com.zh.util.CommonUtil;

/**
 * 测试用户操作
 *
 * @author zh
 * 2018年8月21日
 */
public class TestUserService {

    private static UserService userService;
    
    @Before
    public void before(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("root-context.xml");
        userService = ctx.getBean(UserService.class);
    }
    
    @Test
    public void testAdd() {
        User user = new User();
        user.setId(CommonUtil.uuid());
        user.setAddress("杭州萧山");
        user.setAge(20);
        ZoneId zoneId = ZoneOffset.systemDefault();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("1999-10-10 20:30:10", dtf);
        ZonedDateTime zdt = ldt.atZone(zoneId);
        user.setBrithday(Date.from(zdt.toInstant()));
        user.setName("hh");
        user.setSex(Sex.female);
        userService.add(user);
    }
    
}
