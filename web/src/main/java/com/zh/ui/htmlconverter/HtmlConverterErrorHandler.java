package com.zh.ui.htmlconverter;


import com.alibaba.fastjson.JSONObject;
import com.zh.exception.ResponseMessage;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一错误处理
 */
@ControllerAdvice(basePackages = "com.zh.ui.htmlconverter")
public class HtmlConverterErrorHandler implements BeanNameAware {
    
    private Logger logger = Logger.getLogger(HtmlConverterErrorHandler.class);
    private static AtomicInteger counter = new AtomicInteger(0);

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JSONObject fileErrorHandler() {
        return ResponseMessage.htmlConverter.build();
    }

    @Override
    public void setBeanName(String name) {
        logger.info(counter.incrementAndGet());
    }
}