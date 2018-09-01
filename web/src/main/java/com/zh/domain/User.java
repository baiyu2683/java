package com.zh.domain;

/**
 * 用户
 *
 * @author zh
 * 2018年9月1日
 */
public class User {

    public static enum Sex {
        male, female
    }

    private int id;
    private String name;
    private int age;
    private int sex;
    private String address;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getSex() {
        return sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String toString() {
        return "User(" +
                "age=" + age + ", " +
                "sex=" + sex + ", "+
                "name=" + name + ")";
    }
}
