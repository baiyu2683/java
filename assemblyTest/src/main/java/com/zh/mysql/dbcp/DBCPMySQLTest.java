package com.zh.mysql.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * dbcp连接mysql8
 *
 * @author zh
 * 2018年9月7日
 */
public class DBCPMySQLTest {

    public static void main(String[] args) throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=True&characterEncoding=utf-8");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/test?user=root&password=123456&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false");
//        bds.setUsername("root");
//        bds.setPassword("123456");
        Connection conn = bds.getConnection();
        System.out.println(conn.createStatement().execute("select 1"));
        conn.close();
    }
}
