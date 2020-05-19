package com.zh.image.textimagewatermark;

import com.zh.image.textimagewatermark.handler.WaterMarkHandler;
import com.zh.image.textimagewatermark.param.WaterMarkParam;

import java.awt.image.BufferedImage;

/**
 * 水印处理
 */
public class WaterMarkContext {

    private WaterMarkParam waterMarkParam;

    private WaterMarkHandler waterMarkHandler;

    public BufferedImage handle(BufferedImage src) throws Exception {
        return waterMarkHandler.handle(waterMarkParam, src);
    }

    public WaterMarkParam getWaterMarkParam() {
        return waterMarkParam;
    }

    public void setWaterMarkParam(WaterMarkParam waterMarkParam) {
        this.waterMarkParam = waterMarkParam;
    }

    public WaterMarkHandler getWaterMarkHandler() {
        return waterMarkHandler;
    }

    public void setWaterMarkHandler(WaterMarkHandler waterMarkHandler) {
        this.waterMarkHandler = waterMarkHandler;
    }
}
