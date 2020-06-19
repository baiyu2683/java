package com.zh.db.procedure;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;
import java.util.UUID;

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
    public void testInsertLarge() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();
        conn.setAutoCommit(false);
        try {
            CallableStatement prepareCall = conn.prepareCall("insert into `散点图`(sex, height, weight) values(?, ?, ?)");
            for (int i = 0 ; i < 1000000 ; i++) {
                prepareCall.setInt(1, 1);
                prepareCall.setInt(2, 2);
                prepareCall.setInt(3, 60);
                prepareCall.executeUpdate();
            }
            prepareCall.close();
        } finally {
            conn.commit();
        }
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

    @Test
    public void testDuplicatePrimaryKey() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();

        try {
            String sql = "insert into 导入(id, name1) values(1, null)";
            conn.prepareStatement(sql).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();
    }

    @Test
    public void testSel() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();

        try {
            String sql = "insert into data(name, address) values(?, ?)";
            conn.setAutoCommit(false);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for (int i = 0 ; i < 10000; i++) {
                preparedStatement.setString(1, "2222");
                preparedStatement.setString(2, "233");
                preparedStatement.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testForeignKey() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false&noAccessToProcedureBodies=true");

        Connection conn = bds.getConnection();

        try {
            String sql = "insert into main(id) values('1')";
            conn.prepareStatement(sql).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrimary() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false");

        Connection conn = bds.getConnection();
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet resultSet = meta.getPrimaryKeys(conn.getCatalog(), null, "导入");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("PK_NAME"));
        }
    }
}
