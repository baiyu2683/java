package com.zh.image.textimagewatermark.param;

/**
 * 图片水印参数
 */
public class ImageWaterMarkParam {

    private String imagePath;

    private String name;

    private String description;

    public ImageWaterMarkParam() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
