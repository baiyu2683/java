package com.zh.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangheng
 * @date 2019/12/04
 */
@Slf4j
@RestController
public class ServerController {

    @RequestMapping(value = "/home",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> home() {
        Map<String, String> home = new HashMap<>(1);
        home.putIfAbsent("path", "主页");
        return home;
    }
}
