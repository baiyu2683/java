package com.zh.mysql.mysql8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * mysql8增删改查测试
 *
 * @author zh
 * 2018年9月3日
 */
public class JdbcTest {
    
    private String jdbcUrl = "jdbc:mysql://localhost:3306?useSSL=false&useUnicode=True&characterEncoding=utf8";
    private Connection conn;
    
    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    
    @Before
    public void before() throws SQLException {
        conn = DriverManager.getConnection(jdbcUrl, "root", "123456");
    }
    @After
    public void after() throws SQLException {
        conn.close();
    }
    
    @Test
    public void testCreateDataBases() {
        String sql = "create database test;";
        try {
            PreparedStatement pstat = conn.prepareStatement(sql);
            int result = pstat.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testCreateTable() {
        String sql = "drop table if exists test;";
    }
}
