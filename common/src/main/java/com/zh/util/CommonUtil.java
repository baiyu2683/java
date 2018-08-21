package com.zh.util;

import java.util.UUID;

/**
 * 通用工具
 *
 * @author zh
 * 2018年8月21日
 */
public class CommonUtil {

    /**
     * 32位无-uuid
     * @return
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
