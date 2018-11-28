package com.zh.fastjson.bean;

public enum SexEnum {
    female, male;

    public String toString() {
        return String.valueOf(this.ordinal());
    }
}
