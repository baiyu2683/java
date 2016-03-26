package com.zh.annotations.database.exercise;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zhheng on 2016/3/26.
 * jdbc工具类，实现与数据库的交互
 */
public class MyJDBCHelper {
    private static String url = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&characterEncoding=utf-8";
    private static String driverClass = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "123";

    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println("连接数据库出错：" + e.getMessage());
            return null;
        }
        return conn;
    }
}
