package com.zh.ui;

import com.alibaba.fastjson.JSONObject;
import com.zh.domain.User;
import com.zh.domain.User.Sex;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Administrator <br/>
 * Date: 2018-07-18 <br/>
 */
@Controller
public class Entry {

    @RequestMapping(path = "/homepage", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * 返回json
     * @param request
     * @return
     */
    @RequestMapping(path = "/home", method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String home1(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");
        jsonObject.put("name", "heng");
        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "/obj")
    @ResponseBody
    public User getUser() {
        User user = new User();
        user.setAddress("杭州萧山");
        user.setAge(18);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, 8, 9, 10, 10, 10);
        user.setBrithday(calendar.getTime());
        user.setName("...");
        user.setSex(Sex.female);
        return user;
    }

    /**
     * 接收content-type为MediaType.APPLICATION_FORM_URLENCODED_VALUE的请求
     * 返回APPLICATION_JSON_UTF8_VALUE格式数据
     * @param user
     * @return
     */
    @RequestMapping(value = "/map", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String map(User user){
        System.out.println(JSONObject.toJSONString(user));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");
        return jsonObject.toJSONString();
    }

    /**
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/requestBody", method = RequestMethod.POST)
    @ResponseBody
    public String map2(@RequestBody User user) {
        System.out.println(user);
        return String.valueOf(true);
    }
}
