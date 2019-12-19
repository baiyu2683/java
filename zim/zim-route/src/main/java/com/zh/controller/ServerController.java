package com.zh.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zh.model.UserDTO;
import com.zh.service.UserService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@DefaultProperties(defaultFallback = "defaultFallback",
                    commandProperties =  {
                            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
                    })
public class ServerController {

    @Reference(check = false)
    private UserService userService;

    @HystrixCommand
    @RequestMapping(value = "/home",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> home() {
        List<UserDTO> userDTOList = userService.list(1, 100);
        Map<String,String> result = userDTOList.stream().collect(Collectors.toMap(UserDTO::getCode, UserDTO::getNickName));
        return result;
    }

    public Map<String, String> defaultFallback(Throwable throwable) {
        log.error("调用失败", throwable);
        return new HashMap<>(0);
    }
}
