package com.zh.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * File类
 * 
 * 接收正则表达式参数进行匹配
 * 
 * Created by zh on 2017-02-26.
 */
public class DirList {

    public static FilenameFilter fileter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    /**
     * 加运行参数,.*?ml.*
     * @param args
     */
    public static void main(final String[] args) {
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else {
//            list = path.list(new DirFilter(args[0]));
//            list = path.list(fileter(args[0]));
            Pattern pattern = Pattern.compile(args[0]);
            list = path.list((dir, name) -> {
                return pattern.matcher(name).matches();
            });
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list)
            System.out.println(dirItem);
    }
}

class DirFilter implements FilenameFilter {

    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
