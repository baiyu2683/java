package com.zh.mysql.mysql8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 增查改删
 *
 * @author zh
 * 2018年9月6日
 */
public class DMLTest {
    // &serverTimezone=Asia/Shanghai
    // allowPublicKeyRetrieval=true
    // nullCatalogMeansCurrent=true&
    private String jdbcUrl = "jdbc:mysql://193.112.95.23:3306?allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=True&characterEncoding=utf-8";
    private Connection conn;
    
    @BeforeClass
    public static void beforeClass() throws ClassNotFoundException {
        // 驱动更新
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    
    @Before
    public void before() throws SQLException {
        conn = DriverManager.getConnection(jdbcUrl, "zh", "!@#$asdf");
    }
    @After
    public void after() throws SQLException {
        conn.close();
    }
    
    /**
     * 建表
     */
    @Test
    public void testCreateTable() {
        String useDataBaseSql = "use test;";
        String dropExistTable = "drop table if exists test;";
        String createTableSql = "create table test(id int auto_increment, name varchar(40), primary key(id));";
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
        String insertDataSql = "insert into test values(null, 'zhangheng');";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            pstat = conn.prepareStatement(insertDataSql);
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
        String useDataBaseSql = "use zh;";
        String selectDataSql = "select * from `user` where `nickname` like '%zh%'";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(selectDataSql);
            ResultSet rs = pstat.executeQuery();
            
            while (rs.next()) {
                System.out.println("id: " + rs.getString(1) + " name: " + rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 更新记录
     */
    @Test
    public void testUpdate() {
        String useDataBaseSql = "use test;";
        String updateDataSql = "update test set name = 'zhangheng1';";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(updateDataSql);
            Assert.assertTrue(pstat.executeUpdate() >= 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 删除记录
     */
    @Test
    public void testDelete() {
        String useDataBaseSql = "use test;";
        String deleteDataSql = "delete from test where id <= 0";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            
            pstat = conn.prepareStatement(deleteDataSql);
            Assert.assertTrue(pstat.executeUpdate() >= 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
