package com.zh.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.zh.fastjson.bean.SexEnum;
import com.zh.fastjson.bean.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * 测试fastjson
 */
public class TestFastJson {

    private User user;
    private Integer age = 10;
    private Date birthday = new Date();
    private String name = "zh";
    private SexEnum sex = SexEnum.male;
    private String address = "hangzhou";

    @Before
    public void before() {
        user = new User();
        user.setAge(age);
        user.setBirthday(birthday);
        user.setName(name);
        user.setSex(sex);
        user.setAddress(address);
    }

    @Test
    public void javaBeanToJson() {
        String jsonString = JSONObject.toJSONString(user);
        System.out.println(jsonString);
    }

    @Test
    public void StringToJavaBean() {
        String jsonString = JSONObject.toJSONString(user);
        User user = JSONObject.parseObject(jsonString, User.class);
        assert user.getAge() == age;
        assert user.getBirthday().getTime() == birthday.getTime();
        assert user.getSex() == sex;
        assert name.equals(user.getName());
        assert user.getAddress() == null;
    }

    @Test
    public void javaObjToJsonObj() {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(user);
        System.out.println(jsonObject.toJSONString());
    }
}
