package com.zh.mysql.mysql8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * 设置时区为UTC时，时间会比当前时间少8小时，11:00 -> 03:00
 * 设置为Asia/Shanghai时正常
 * 
 * @author zh
 * 2018年9月6日
 */
public class TimeZoneTest {
    // &serverTimezone=Asia/Shanghai
    // allowPublicKeyRetrieval=true
    private String jdbcUrl = "jdbc:mysql://localhost:3306?allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=True&characterEncoding=utf-8";
    private Connection conn;
    
    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException {
        // 驱动更新
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
    public void testCreateTable() {
        String useDataBaseSql = "use test;";
        String dropExistTable = "drop table if exists test_timezone;";
        String createTableSql = "create table test_timezone(id int auto_increment, birthday datetime, primary key(id));";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            pstat = conn.prepareStatement(dropExistTable);
            Assert.assertEquals(false, pstat.execute());
            pstat = conn.prepareStatement(createTableSql);
            Assert.assertEquals(false, pstat.execute());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 插入记录
     */
    @Test
    public void testInsert() {
        String useDataBaseSql = "use test;";
        String insertDataSql = "insert into test_timezone values(null, ?);";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            pstat = conn.prepareStatement(insertDataSql);
            pstat.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
            Assert.assertEquals(1, pstat.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 查询记录
     */
    @Test
    public void testSelect() {
        String useDataBaseSql = "use test;";
        String selectDataSql = "select * from test_timezone;";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(selectDataSql);
            ResultSet rs = pstat.executeQuery();
            
            while (rs.next()) {
                System.out.println("id: " + rs.getInt(1) + " birthday: " + new Date(rs.getTimestamp(2).getTime()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
