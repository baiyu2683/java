package com.zh.ui.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zh.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public JSONObject save() {
        userService.save();
        JSONObject json = new JSONObject();
        json.put("success", true);
        return json;
    }
}
