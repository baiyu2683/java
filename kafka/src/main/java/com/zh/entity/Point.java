package com.zh.entity;

import java.util.Date;

/**
 * 采集点
 */
public class Point {

    /**
     * key，唯一标识
     */
    private String key;

    /**
     * 起始链接
     */
    private String rootUrl;
    /**
     * 当前采集链接
     */
    private String url;
    /**
     * 开始采集时间
     */
    private Date startTime;
    /**
     * 页面采集规则
     */
    private String config;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public void setRootUrl(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
