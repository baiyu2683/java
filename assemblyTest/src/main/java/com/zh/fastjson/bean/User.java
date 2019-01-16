package com.zh.fastjson.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String name;

    private Integer age;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date birthday;

    @JSONField(serialzeFeatures = SerializerFeature.WriteEnumUsingToString)
    private SexEnum sex;

    @JSONField(serialize = false, deserialize = false)
    private String address;
}
