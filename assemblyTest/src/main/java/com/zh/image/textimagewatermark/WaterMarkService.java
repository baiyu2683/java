package com.zh.image.textimagewatermark;

import com.zh.image.textimagewatermark.handler.*;
import com.zh.image.textimagewatermark.param.WaterMarkParam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 水印生成
 */
public class WaterMarkService {

    public byte[] getWaterMarkImage(WaterMarkParam waterMarkParam) throws Exception {
        List<WaterMarkContext> list = new ArrayList<>();
        boolean existContent = false;
        if (waterMarkParam.getText() != null) {
            WaterMarkContext waterMarkContext = new WaterMarkContext();
            waterMarkContext.setWaterMarkHandler(new TextWaterMarkHandler());
            waterMarkContext.setWaterMarkParam(waterMarkParam);
            list.add(waterMarkContext);
            existContent = true;
        }
        if (waterMarkParam.getImage() != null) {
            WaterMarkContext waterMarkContext = new WaterMarkContext();
            waterMarkContext.setWaterMarkHandler(new ImageWaterMarkHandler());
            waterMarkContext.setWaterMarkParam(waterMarkParam);
            list.add(waterMarkContext);
            existContent = true;
        }
        if (existContent) {

            WaterMarkContext transparentImage = new WaterMarkContext();
            transparentImage.setWaterMarkHandler(new ImageTransparencyHandler());
            transparentImage.setWaterMarkParam(waterMarkParam);
            list.add(transparentImage);

            WaterMarkContext waterMarkContext = new WaterMarkContext();
            waterMarkContext.setWaterMarkHandler(new RotateWaterMarkHandler());
            waterMarkContext.setWaterMarkParam(waterMarkParam);
            list.add(waterMarkContext);
        }
        // 水印处理
        BufferedImage bufferedImage = null;
        for (WaterMarkContext waterMarkContext : list) {
            bufferedImage = waterMarkContext.handle(bufferedImage);
        }
        // 格式转换
        if (bufferedImage == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
