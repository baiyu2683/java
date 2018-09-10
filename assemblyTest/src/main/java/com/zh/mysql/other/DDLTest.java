package com.zh.mysql.other;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * mysql5.6建表建库测试
 * 库: test
 * 表： test
 * 视图: test_view test_view2
 * 存储过程: test_proc
 * 
 * 混进去了一些查询表结构，查询视图、获取库中所有视图和调用存储过程的验证代码
 * @author zh
 * 2018年9月3日
 */
public class DDLTest {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://144.20.80.240:3306/excelreport650?nullCatalogMeansCurrent=true&serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=True&characterEncoding=utf-8";
        Connection conn = DriverManager.getConnection(jdbcUrl, "root", "123456");
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet rst = dmd.getTables(null, "test", null, 
                    new String[] {"TABLE", "VIEW", "SYSTEM TABLE", "TABLE_CAT"});
            while (rst.next()) {
                String tableName = rst.getString("TABLE_NAME");
                System.out.println(tableName);
            }
//            DatabaseMetaData dmd = conn.getMetaData();
//            ResultSet rst = dmd.getProcedures(null, "test", null);
//            while (rst.next()) {
//                String tableName = rst.getString("PROCEDURE_NAME");
//                System.out.println(tableName);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
