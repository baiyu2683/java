package com.zh.image.textimagewatermark.handler;

import com.zh.image.textimagewatermark.param.WaterMarkParam;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;

/**
 * 水印处理
 */
public interface WaterMarkHandler {

    BufferedImage handle(WaterMarkParam waterMarkParam, BufferedImage src) throws MalformedURLException, Exception;
}
