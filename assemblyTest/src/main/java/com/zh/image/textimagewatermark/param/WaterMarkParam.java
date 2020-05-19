package com.zh.image.textimagewatermark.param;

/**
 * 水印相关参数
 */
public class WaterMarkParam {

    /**
     * 最终生成图片宽度
     */
    private Integer width;

    /**
     * 最终生成图片高度
     */
    private Integer height;

    /**
     * 水印角度, 正数逆时针
     */
    private Integer angle;

    /**
     * 水平间距, 单位像素
     */
    private Integer horizontalSpacing;

    /**
     * 垂直间距， 单位像素
     */
    private Integer verticalSpacing;

    /**
     * 文字水印
     */
    private TextWaterMarkParam text;

    /**
     * 图片相对文字的布局
     */
    private ImageLayoutEnum imageLayoutEnum;

    /**
     * 图片水印
     */
    private ImageWaterMarkParam image;

    public WaterMarkParam() {

        width = 595;
        height = 842;

        angle = 30;
        horizontalSpacing = 200 / 2;
        verticalSpacing = 100 / 2;

        imageLayoutEnum = ImageLayoutEnum.LEFT;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getHorizontalSpacing() {
        return horizontalSpacing;
    }

    public void setHorizontalSpacing(Integer horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    public Integer getVerticalSpacing() {
        return verticalSpacing;
    }

    public void setVerticalSpacing(Integer verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    public TextWaterMarkParam getText() {
        return text;
    }

    public void setText(TextWaterMarkParam text) {
        this.text = text;
    }

    public ImageLayoutEnum getImageLayoutEnum() {
        return imageLayoutEnum;
    }

    public void setImageLayoutEnum(ImageLayoutEnum imageLayoutEnum) {
        this.imageLayoutEnum = imageLayoutEnum;
    }

    public ImageWaterMarkParam getImage() {
        return image;
    }

    public void setImage(ImageWaterMarkParam image) {
        this.image = image;
    }
}
