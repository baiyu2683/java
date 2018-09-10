package com.zh.controller.test;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回json
 * 
 * 测试forward和redirect跳转
 * 携带参数
 * @author zh
 * 2018年9月9日
 */
@Controller
@RequestMapping("/test")
public class ForwardAndRedirectTest {
	
	@RequestMapping(path="/redirectAddress", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject redirectAddress(@ModelAttribute("name") String name,
			@ModelAttribute("success") Boolean success) {
		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("success", success);
		return result;
	}
	
	/**
	 * redirect传递参数
	 * @param ra
	 * @return
	 */
	@RequestMapping("/redirect")
	public ModelAndView redirect(RedirectAttributes ra) {
		ra.addFlashAttribute("name", "redirectName");
		// attribute会在url上带上参数: path?success=true
//		ra.addAttribute("success", true);
		ra.addFlashAttribute("success", true);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:redirectAddress");
		return mv;
	}
	
	@RequestMapping(path="/forwardAddress", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public JSONObject forwardAddress(@RequestParam("name") String name, 
			Boolean success) {
		JSONObject result = new JSONObject();
		result.put("name", name);
		result.put("success", success);
		return result;
	}
	
	/**
	 * forward传递参数
	 * @return
	 */
	@RequestMapping("/forward")
	public ModelAndView forward() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:forwardAddress?name=forward&success=true");
		return mv;
	}
}
