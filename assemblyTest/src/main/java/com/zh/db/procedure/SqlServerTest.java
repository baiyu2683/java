package com.zh.db.procedure;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.*;

public class SqlServerTest {

    @Test
    public void testProcedures() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getProcedures(null, null, null);
        while (rs.next()) {
            String procedureName = rs.getString("PROCEDURE_NAME");
            System.out.println(procedureName.split(";")[0]);
            Short procedureType = rs.getShort("PROCEDURE_TYPE");
            String remark = rs.getString("REMARKS");
            System.out.println(procedureName + " - " + procedureType + " - " + remark);
        }
        conn.close();
    }

    @Test
    public void testProcedureInfo() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getProcedureColumns(null, null, "test_query", null);
        while (rs.next()) {
            String paramName = rs.getString("COLUMN_NAME");
            System.out.println(paramName);
            System.out.println(rs.getInt("COLUMN_TYPE"));
            System.out.println(rs.getInt("DATA_TYPE"));
        }
        conn.close();
    }

    @Test
    public void testQuery() throws SQLException, UnsupportedEncodingException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call test_query1(?, ?, ?, ?)}");
        prepareCall.setString("queryname", "a");
        prepareCall.setInt("procOutResultType", 0);
        prepareCall.registerOutParameter("procOutResultType", Types.INTEGER);
        prepareCall.registerOutParameter("procOutResultMsg", Types.VARCHAR);
        prepareCall.registerOutParameter("procOutErrorCode", Types.INTEGER);

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
                System.out.println(resultSet.getString("email"));
            }
            hasMoreResult = prepareCall.getMoreResults();
        }
        System.out.println("输出参数...");
        System.out.println(prepareCall.getInt("procOutResultType"));
        System.out.println(prepareCall.getString("procOutResultMsg"));
        System.out.println(prepareCall.getInt("procOutResultType"));

        conn.close();
    }

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

        CallableStatement prepareCall = conn.prepareCall("{call [test_page](?, ?, ?, ?)}");
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

    @Test
    public void testDuplicatePrimaryKey() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("insert into info(id, name, age) values('4', '2', '2')");
            statement.execute();
        } catch (Exception e) {
            SQLException sqlException = new SQLIntegrityConstraintViolationException("asdf", e);
            sqlException.printStackTrace();
            System.out.println(sqlException.getMessage());
        }
    }

    @Test
    public void testPrimary() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet resultSet = meta.getPrimaryKeys(conn.getCatalog(), null, "user");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("PK_NAME"));
        }
    }
}
