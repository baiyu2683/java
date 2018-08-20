package com.zh.fastjson;

import com.alibaba.fastjson.JSONArray;

/**
 * 同一个对象向jsonarray中放置多次，序列化，第二个对象会变成引用形式
 * [{"dispValue":"yyyy年MM月dd","value":"yyyy-MM-dd"},{"$ref":"$[0]"}]
 * @author zh
 * 2018年8月17日
 */
public class TestJSONArrayDuplObj {
    @SuppressWarnings("unused")
    private static class Edit {
        private String value;
        private String dispValue;
        
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getDispValue() {
            return dispValue;
        }
        public void setDispValue(String dispValue) {
            this.dispValue = dispValue;
        }
    }

    public static void main(String[] args) {
        Edit edit = new Edit();
        edit.setDispValue("yyyy年MM月dd");
        edit.setValue("yyyy-MM-dd");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(edit);
        jsonArray.add(edit);
        System.out.println(jsonArray.toJSONString());
    }
}
