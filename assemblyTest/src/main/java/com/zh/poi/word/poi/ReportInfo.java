package com.zh.poi.word.poi;

/**
 * 导出word和pdf时需要的参数信息
 * @author zhheng
 */
public class ReportInfo {
    // 导出文件名
    private String fileName;
    // 报表名称
    private String alias;
    // 版本
    private String version;
    // 发布人
    private String owner;
    // 发布时间
    private long timeStamp;
    // 需要转换的内容
    private String content;
    // 纸张设置信息
    private Paper paper;
    
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Paper getPaper() {
        return paper;
    }
    public void setPaper(Paper paper) {
        this.paper = paper;
    }
    
}
