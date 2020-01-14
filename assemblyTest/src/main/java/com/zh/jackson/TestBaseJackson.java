package com.zh.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.fastjson.bean.SexEnum;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestBaseJackson {

    @Test
    public void testEmpty() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // 不打印空(空字符串和null)
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        Map<String, String> map = new HashMap<>();
        map.put("key1", "asdf");
        map.put("key2", "1234");
        map.put("key3", null);
        map.put("key4", "");
        System.out.println(objectMapper.writeValueAsString(map));
    }

    /**
     * jackson序列化反序列化枚举值
     * 可以在枚举类中@see SexEnum 中使用注解@JsonCreator和@JsonValue，自定义反序列化和序列化的值
     * @throws IOException
     */
    @Test
    public void testEnum() throws IOException {
        ObjectMapper om = new ObjectMapper();
//        // 枚举值使用index
//        om.enable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        User user = new User();
        user.setSexEnum(SexEnum.female);
        String jsonStr = om.writeValueAsString(user);
        System.out.println(jsonStr);
        User user1 = om.readValue(jsonStr, User.class);
        System.out.println(user1.sexEnum);
    }

    @Data
    static class User {
        private SexEnum sexEnum;
    }
}
