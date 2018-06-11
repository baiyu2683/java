package com.zh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {

    private static class Dimension {
        private String name;
        private transient String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static void main(String[] args) {
        Dimension dimension = new Dimension();
        dimension.setName("asdf");
        dimension.setAddress("dddd");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(dimension);
        System.out.println(jsonObject.toJSONString());
    }
}
