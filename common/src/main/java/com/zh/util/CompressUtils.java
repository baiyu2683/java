package com.zh.util;

import com.zh.log4j.Log4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 利用GZIP进行压缩/解压
 */
public class CompressUtils {

    public static byte[] gzipCompress(String source){
        return gzipCompress(source, "utf-8");
    }

    public static byte[] gzipCompress(String source, String charsetName) {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            byte[] sourceBytes = source.getBytes(charsetName);
            bais = new ByteArrayInputStream(sourceBytes);
            gzipOutputStream = new GZIPOutputStream(baos);
            byte[] buffer = new byte[8192];
            int length = 0;
            while ((length = bais.read(buffer)) != -1) {
                gzipOutputStream.write(buffer, 0, length);
            }
            //gzipOutputStream.close需要在调用baos.toByteArray()之前调用
            //close中会做一些处理，如果放在finally中，会在return之前，toByteArray()之后调用，少做了一些处理， 无法正确解压
            gzipOutputStream.close();
            return baos.toByteArray();
        } catch (IOException e) {
            Log4j.error("压缩失败", e);
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String gzipDeCommpress(byte[] compressedSource) {
        return gzipDeCompress(compressedSource, "utf-8");
    }

    public static String gzipDeCompress(byte[] compressedSource, String charsetName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(compressedSource);
        GZIPInputStream gzipInputStream = null;
        try {
            gzipInputStream = new GZIPInputStream(bais);
            byte[] buffer = new byte[512];
            int length = 0;
            while ((length = gzipInputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return new String(baos.toByteArray(), charsetName);
        } catch (IOException e) {
            Log4j.error("解压缩失败", e);
        } finally {
            if (gzipInputStream != null) {
                try {
                    gzipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
