package com.zh.controller.test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 添加和获得cookie
 * @author zh
 * 2018年9月10日
 */
@Controller
@RequestMapping("/test")
public class CookieControllerTest {
    
    @RequestMapping("/addCookie")
    public String addCookie(HttpServletResponse response) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 100 ; i++) {
            sb.append("zhangheng").append(i).append("...");
        }
        Cookie cookie = new Cookie("name", sb.toString());
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 20);
        response.addCookie(cookie);
        return "/test/cookie";
    }
    
    @RequestMapping("/getCookie")
    @ResponseBody
    public void getCookie(@CookieValue("name") String name) {
        System.out.println("name=" + name);
        System.out.println("成功...");
    }
}
