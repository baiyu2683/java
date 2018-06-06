package com.zh.io;

import com.zh.util.CountingGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 接受一个文件路径信息，获取该路径下所有文件的排序目录列表
 * Created by zh on 2017-02-26.
 */
public class SortedDirList {

    private static String path;

    public SortedDirList(String path) {
        this.path = path;
    }

    /**
     * 获取所有的
     * @param path
     * @return
     */
    public static List<File> list(String path) {
        List<File> paths = new ArrayList<>();
        File dir = new File(path);
        if(dir.exists()) {
            if(dir.isFile()) {
                paths.add(dir);
            } else {
                File[] subFiles = dir.listFiles();
                Stream.of(subFiles).forEach( file -> {
                    if(file.isFile()){
                        paths.add(file);
                    } else {
                        paths.addAll(list(file.getAbsolutePath()));
                    }
                });
            }
        }
        return paths;
    }

    public static List<File> list(String path, String reg) {
        List<File> paths = new ArrayList<>();
        File dir = new File(path);
        if(dir.exists()) {
            if(dir.isFile()) {
                paths.add(dir);
            } else {
                File[] subFiles = dir.listFiles();
                Stream.of(subFiles).forEach( file -> {
                    if(file.isFile()){
                        if(Pattern.compile(reg).matcher(file.getName()).matches()) //过滤
                            paths.add(file);
                    } else {
                        paths.addAll(list(file.getAbsolutePath(), reg));
                    }
                });
            }
        }
        return paths;
    }

    /**
     * 计算目录下指定文件的大小总和
     * @param path
     * @param reg
     * @return
     */
    public static long sizeOf(String path, String reg) {
        return list(path, reg).parallelStream().mapToLong(file -> {
            System.out.println("filename:" + file.getAbsolutePath() + ", length:" + file.length());
            return file.length();
        }).sum();
    }

    public static void main(String[] args) {
        List<File> list = list("f://company", ".*?\\.txt");
        list.parallelStream().forEach(file -> System.out.println(file.getAbsolutePath()));
        System.out.println(sizeOf("f:/company", ".*?\\.json"));
    }
}
