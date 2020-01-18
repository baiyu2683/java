package com.zh.util;


import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassUtil {

    public static Set<String> all(String path) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(path);
        if (url == null) {
            return null;
        }
        Set<String> clazzs = new LinkedHashSet<>();
        recursionDir(new File(url.getFile()), clazzs);
        return clazzs;
    }


    private static void recursionDir(File root, Set<String> clazzs) {
        if (!root.exists()) {
            return;
        }

        if (root.isFile()) {
            String fileName = root.getName();
            if (fileName.endsWith(".class")) {
                clazzs.add(fileName.replace(".class", ""));
            }
        } else if (root.isDirectory()) {
            File[] subFiles = root.listFiles();
            if (ArrayUtils.isNotEmpty(subFiles)) {
                for (File subFile : subFiles) {
                    recursionDir(subFile, clazzs);
                }
            }
        }
    }

}
