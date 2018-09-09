package com.zh.controller.test;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

/**
 * 在方法上使用ModelAttribute注解测试
 * 
 * @see ForwardAndRedirectTest
 * @author zh
 * 2018年9月9日
 */
@Controller
@RequestMapping("/test")
public class ModelAttributeMethodTest {

	@ModelAttribute
	public void modelAttributeMethod(ModelAndView mv) {
		mv.addObject("success", true);
		mv.addObject("name", "modelAttrubute");
	}
	
	@RequestMapping(path="/modelAttributeTest",
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject json(ModelAndView mv) {
		JSONObject obj = new JSONObject();
		obj.put("success", mv.getModel().get("success"));
		obj.put("name", mv.getModel().get("name"));
		return obj;
	}
}
