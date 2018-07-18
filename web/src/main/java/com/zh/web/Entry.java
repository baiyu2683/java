package com.zh.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/map")
    @ResponseBody
    public JSONObject map(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "value");
        return jsonObject;
    }
}
