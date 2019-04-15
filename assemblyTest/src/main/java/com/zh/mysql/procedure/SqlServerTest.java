package com.zh.mysql.procedure;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;

public class SqlServerTest {

    @Test
    public void testWriteBack() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call test_writeback(?)}");
        prepareCall.setString("name", "haha");

        boolean flag = prepareCall.execute();

        boolean hasMoreResult = true;
        while (hasMoreResult) {
            ResultSet resultSet = prepareCall.getResultSet();
            if (resultSet == null) {
                break;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("nickname"));
            }
            hasMoreResult = prepareCall.getMoreResults();
        }
//        System.out.println("输出参数...");
//        System.out.println(prepareCall.getString("procOutResultType"));

        conn.close();
    }


    @Test
    public void testPage() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call test_page(?, ?, ?, ?)}");
        prepareCall.setInt("pageBeginNum", 0);
        prepareCall.setInt("pageEndNum", 10);
        prepareCall.setInt("pageRowCount", 3);
        prepareCall.registerOutParameter("procOutResultType", Types.VARCHAR);

        boolean flag = prepareCall.execute();

        boolean hasMoreResult = true;
        while (hasMoreResult) {
            ResultSet resultSet = prepareCall.getResultSet();
            if (resultSet == null) {
                break;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("nickname"));
            }
            hasMoreResult = prepareCall.getMoreResults();
        }
        System.out.println("输出参数...");
        System.out.println(prepareCall.getString("procOutResultType"));

        conn.close();
    }
}
