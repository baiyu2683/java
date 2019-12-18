package com.zh.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zh.model.UserDTO;
import com.zh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangheng
 * @date 2019/12/04
 */
@Slf4j
@RestController
public class ServerController {

    @Reference
    private UserService userService;

    @HystrixCommand(fallbackMethod = "homeFallback")
    @RequestMapping(value = "/home",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> home() {
        List<UserDTO> userDTOList = userService.list(1, 100);
        Map<String,String> result = userDTOList.stream().collect(Collectors.toMap(UserDTO::getId, UserDTO::getCode));
        return result;
    }

    public Map<String, String> homeFallback(Throwable throwable) {
        throwable.printStackTrace();
        return new HashMap<>(0);
    }
}
