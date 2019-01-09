package com.zh.fastjson.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SexEnum {
    female(1, "female"),
    male(2, "male");

    private Integer code;
    private String desc;

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @JsonCreator
    public SexEnum getSex(Integer code) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.code == code) {
                return sexEnum;
            }
        }
        return null;
    }

    @JsonValue
    public int getCode() {
        return this.code;
    }

    public String toString() {
        return String.valueOf(this.ordinal());
    }
}
