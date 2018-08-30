package com.zh.exception;

import com.alibaba.fastjson.JSONObject;

/**
 * 200成功
 * 其他自定义
 */
public enum ResponseMessage {

    success(200, "success"),
    failure(300, "false"),
    htmlConverter(500, "转换失败!")
    ;

    private Integer code;

    private String message;

    ResponseMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }
    public String getMessage() {
        return this.message;
    }

    public JSONObject build() {
        JSONObject response = new JSONObject();
        response.put("code", this.code);
        response.put("message", this.message);
        return response;
    }
}
