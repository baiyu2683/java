package com.zh.protobuf;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;

import java.io.UnsupportedEncodingException;

public class ProtobufTest {

    public static void main(String[] args) throws InvalidProtocolBufferException, UnsupportedEncodingException {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        builder.setName("zh2683");
        builder.setEmail("zh2683@163.com");

        PersonModel.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person byte length:" + person.toByteArray().length);
        for (byte b : person.toByteArray()) {
            System.out.print(b + "-");
        }
        System.out.println();

        byte[] byteArray = person.toByteArray();
        PersonModel.Person p2 = PersonModel.Person.parseFrom(byteArray);
//        System.out.println("after id:" + p2.getId());
//        System.out.println("after name:" + p2.getName());
//        System.out.println("after email:" + p2.getEmail());

        PersonJson personJson = new PersonJson();
        personJson.setId(1);
        personJson.setName("zh2683");
        personJson.setEmail("zh2683@163.com");
        byte[] bytes = JSONObject.toJSONString(personJson).getBytes("utf-8");
        System.out.println("===PersonJson byte length:" + bytes.length);
        for (byte b : bytes) {
            System.out.print(b + "-");
        }
    }
}
