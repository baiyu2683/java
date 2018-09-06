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
 * mysql8建表建库测试
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
    
    /**
     * 建库
     */
    @Test
    public void testCreateDataBases() {
        String dropDataBasesSql = "drop database if exists test;";
        String createDataBasesSql = "create database test;";
        try {
            PreparedStatement pstat = conn.prepareStatement(dropDataBasesSql);
            Assert.assertEquals(false, pstat.execute());
            pstat = conn.prepareStatement(createDataBasesSql);
            Assert.assertEquals(false, pstat.execute());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
     * 获取表结构
     */
    @Test
    public void testDescribeTable() {
        String useDataBaseSql = "use test;";
        String describeTableSql = "describe test;";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            boolean result = pstat.execute();
            Assert.assertEquals(false, result);
            pstat = conn.prepareStatement(describeTableSql);
            ResultSet rs = pstat.executeQuery();
            while(rs.next()) {
                System.out.println("field: " + rs.getString(1));
                System.out.println("type: " + rs.getString("type"));
                System.out.println("null: " + rs.getString("Null"));
                System.out.println("key: " + rs.getString("Key"));
                System.out.println("default: " + rs.getString("Default"));
                System.out.println("extra: " + rs.getString("Extra"));
                System.out.println("===============================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 新建一个视图test_view
     */
    @Test
    public void testCreateView() {
        String useDataBaseSql = "use test;";
        String dropExistViewSql = "drop view if exists test_view;";
        String createViewSql = "create view test_view as select id from test;";
        String describeViewSql = "describe test_view;";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(dropExistViewSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(createViewSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(describeViewSql);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("Field"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取所有视图
     */
    @Test
    public void testShowAllViews() {
        String useDataBaseSql = "use test;";
        String dropExistViewSql = "drop view if exists test_view2;";
        String createViewSql = "create view test_view2 as select id from test;";
        String showAllViewsSql = " show table status where comment = 'view';";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(dropExistViewSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(createViewSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(showAllViewsSql);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 调用存储过程
     */
    @Test
    public void testCreateProc() {
        String useDataBaseSql = "use test;";
        String dropExistProcSql = "drop procedure if exists test_proc;";
        // 比输入minId大的id
        StringBuilder createProcSql = new StringBuilder();
        createProcSql.append("create procedure test_proc(in minId int)");
        createProcSql.append("begin ");
        createProcSql.append("select id from test where id > minId;");
        createProcSql.append("end;");
        
        String callProcSql = "call test_proc(10);";
        try {
            PreparedStatement pstat = conn.prepareStatement(useDataBaseSql);
            System.out.println(pstat.execute());
            
            pstat = conn.prepareStatement(dropExistProcSql);
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(createProcSql.toString());
            Assert.assertEquals(false, pstat.execute());
            
            pstat = conn.prepareStatement(callProcSql);
            Assert.assertEquals(true, pstat.execute());
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
