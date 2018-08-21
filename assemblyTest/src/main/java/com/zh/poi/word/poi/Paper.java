package com.zh.poi.word.poi;

public class Paper {
    
    public static enum Orientation {
        portrait, // 纵向
        landscape // 横向
    }

    private Integer height;
    
    private Integer width;
    
    private Integer marginLeft;
    
    private Integer marginRight;
    
    private Integer marginTop;
    
    private Integer marginBottom;
    
    private Orientation orientation;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(Integer marginLeft) {
        this.marginLeft = marginLeft;
    }

    public Integer getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(Integer marginRight) {
        this.marginRight = marginRight;
    }

    public Integer getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(Integer marginTop) {
        this.marginTop = marginTop;
    }

    public Integer getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(Integer marginBottom) {
        this.marginBottom = marginBottom;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
}
