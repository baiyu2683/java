package com.zh.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Administrator <br/>
 * Date: 2018-07-18 <br/>
 */
@RestController
public class Entry {

    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public JSONObject home() {
        JSONObject object = new JSONObject();
        object.put("key", "value");
        return object;
    }
}
