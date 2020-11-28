package com.zh.fastjson.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.zh.fastjson.bean.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGson {

    @Test
    public void test1() {
        User user = new User();
        user.setName("1\n2");
        List<User> userList = new ArrayList<>();
        userList.add(user);

        Holder holder = new Holder();
        holder.users = userList;

        Gson gson = new GsonBuilder().create();
        JsonElement tree =  gson.toJsonTree(holder);
        System.out.println(tree.toString());


        System.out.println("123\nasdf");
    }

    static class Holder {
        List<User> users;
    }
}
