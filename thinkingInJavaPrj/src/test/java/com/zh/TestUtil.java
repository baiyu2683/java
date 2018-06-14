package com.zh;

import com.alibaba.fastjson.JSONObject;

public class TestUtil {

    public static void main(String[] args) {
        People people = new People();
        people.setName("123");
        JSONObject object = (JSONObject) JSONObject.toJSON(people);
    }

    static class People {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
