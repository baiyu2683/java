package com.zh.assembly.fastjson;

import com.alibaba.fastjson.JSONArray;

public class FastJsonTest {

    public static void main(String[] args) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONArray());
        System.out.println(jsonArray.toJSONString());
    }
}
