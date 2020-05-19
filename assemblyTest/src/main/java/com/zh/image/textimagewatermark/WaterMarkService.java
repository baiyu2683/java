package com.zh.image.textimagewatermark;

import com.zh.image.textimagewatermark.handler.ImageWaterMarkHandler;
import com.zh.image.textimagewatermark.handler.TextWaterMarkHandler;
import com.zh.image.textimagewatermark.handler.TileWaterMarkHandler;
import com.zh.image.textimagewatermark.param.WaterMarkParam;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
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
            WaterMarkContext waterMarkContext = new WaterMarkContext();
            waterMarkContext.setWaterMarkHandler(new TileWaterMarkHandler());
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
