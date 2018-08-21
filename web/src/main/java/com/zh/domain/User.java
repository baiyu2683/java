package com.zh.domain;

import java.util.Date;

/**
 * 用户信息
 * Author: Administrator <br/>
 * Date: 2018-07-19 <br/>
 */
public class User {
    
    /**性别*/
    public static enum Sex {
        male, female;
    }
    
    private String id;
    
    private String name;

    private String address;
    
    private Integer age;
    
    private Sex sex;
    
    private Date brithday;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }
    
}
