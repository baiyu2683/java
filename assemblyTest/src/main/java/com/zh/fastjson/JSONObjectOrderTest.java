package com.zh.fastjson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.config.FastJsonConfig;

import java.util.TreeMap;

/**
 * 测试JSONObject的顺序
 * @Author zh2683
 */
public class JSONObjectOrderTest {

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("10", "asdfasdf");
        jsonObject.put("01", "asdfasdf");
        System.out.println(jsonObject.toString());

        jsonObject = new JSONObject(new TreeMap<>());
        jsonObject.put("10", "aaa");
        jsonObject.put("01", "bbb");
        System.out.println(jsonObject.toString());
    }
}
