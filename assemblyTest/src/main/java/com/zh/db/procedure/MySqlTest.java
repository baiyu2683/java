package com.zh.db.procedure;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;

public class MySqlTest {

    @Test
    public void testUserPrivilege() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false");

        Connection conn = bds.getConnection();
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet resultSet = meta.getTablePrivileges(null, null, "user");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("PRIVILEGE"));
        }
        bds.close();
    }

    @Test
    public void testDataPro() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://144.20.80.146:3306/ddsl?user=root&password=123456&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();
        DatabaseMetaData meta = conn.getMetaData();

        ResultSet rs = meta.getProcedureColumns(null, null, "test_writeback", null);
        while (rs.next()) {
            System.out.println(rs.getString("COLUMN_NAME"));
            System.out.println(rs.getString("COLUMN_TYPE"));
            System.out.println(rs.getInt("DATA_TYPE"));
            System.out.println("");
        }
        conn.close();
    }

    @Test
    public void testquery() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call testquery()}");

        boolean flag = prepareCall.execute();
        boolean hasMoreResult = true;
        while (hasMoreResult) {
            ResultSet resultSet = prepareCall.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
            }
            hasMoreResult = prepareCall.getMoreResults();
        }
        ResultSetMetaData resultSetMetaData = prepareCall.getMetaData();
        conn.close();
    }


    @Test
    public void testInsert() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://144.20.80.146:3306/ddsl?user=root&password=123456&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call test_writeback(?, ?, ?, ?, ?, ?)}");
        prepareCall.registerOutParameter("out_var1", Types.VARCHAR);
        prepareCall.registerOutParameter("procOutResultType", Types.INTEGER);
        prepareCall.setString("p_yg_bh", "1");
        prepareCall.setString("p_mc", "2");
        prepareCall.setString("p_xb", "3");
        prepareCall.setString("p_dhhm", "4");

        boolean flag = prepareCall.execute();
        boolean hasMoreResult = true;
        while (hasMoreResult) {
            ResultSet resultSet = prepareCall.getResultSet();
            if (resultSet == null) {
                break;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString("bh"));
            }
            hasMoreResult = prepareCall.getMoreResults();
        }
        ResultSetMetaData resultSetMetaData = prepareCall.getMetaData();
        System.out.println(prepareCall.getString("out_var1"));
        System.out.println(prepareCall.getInt("procOutResultType"));
        conn.close();
    }
}
