package com.zh.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by zh on 2017-02-26.
 */
public class MakeDirectories {

    public static void usage() {
        System.err.println(
                "Usage:MakeDirectories paths1 ...\n" +
                        "Creates each path\n" +
                        "Usage:MakeDirectories -d path1 ...\n" +
                        "Deletes each path\n" +
                        "Usage:MakeDirectories -r path1 path2\n" +
                        "Renames from path1 to path2"
        );
        System.exit(1);
    }

    private static void fileData(File file) {
        System.out.println(
                "Absolute path: " + file.getAbsolutePath() +
                        "\n Can read: " + file.canRead() +
                        "\n Can write: " + file.canWrite() +
                        "\n getName: " + file.getName() +
                        "\n getParent: " + file.getParent() +
                        "\n getPath: " + file.getPath() +
                        "\n lenth: " + file.length() +
                        "\n lastModified: " + file.lastModified()
        );
        if(file.isFile())
            System.out.println("it's a file");
        else if (file.isDirectory())
            System.out.println("It's a directory");
    }

    public static void main(String[] args) {
//        fileData(new File("f:/company"));
        File file = new File("f:/zhangheng1");
        if(!file.exists())
            file.mkdirs();
        //文件不存在时才能rename成功
        System.out.println(file.renameTo(new File("f:/company1")));
        System.out.println(file.getFreeSpace());
    }
}
