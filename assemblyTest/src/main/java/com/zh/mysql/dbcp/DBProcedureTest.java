package com.zh.mysql.dbcp;

import java.sql.*;

import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

/**
 * dbcp连接mysql8
 *
 * @author zh
 * 2018年9月7日
 */
public class DBProcedureTest {

    @Test
    public void testmyql() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call first(?, ?)}");
        prepareCall.setString("name", "测试测试测试");
        prepareCall.registerOutParameter("tmpname", Types.VARCHAR);

        boolean flag = prepareCall.execute();
        boolean hasMoreResult = prepareCall.getMoreResults();
        while (hasMoreResult) {
            ResultSet resultSet = prepareCall.getResultSet();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
            }
        }
        ResultSetMetaData resultSetMetaData = prepareCall.getMetaData();
        System.out.println(prepareCall.getString("tmpname"));
        conn.close();
    }

    @Test
    public void testOracle() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call \"first\"(?, ?, ?)}");
        prepareCall.setString("name", "a");
        prepareCall.registerOutParameter("outname", Types.VARCHAR);
        prepareCall.registerOutParameter("cur", OracleTypes.CURSOR);

        boolean flag = prepareCall.execute();

        System.out.println("-------------------");
        ResultSet resultSet = (ResultSet) prepareCall.getObject("cur");

        int colCount = resultSet.getMetaData().getColumnCount();
        System.out.println(colCount);

        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("nickname"));
        }

        System.out.println("--------out params------");
        System.out.println(prepareCall.getString("outname"));

        conn.close();
    }

    @Test
    public void testDB2() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        bds.setUrl("jdbc:db2://129.204.212.96:50000/zh");
        bds.setUsername("db2inst1");
        bds.setPassword("db2inst1");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call \"first\"(?, ?, ?)}");
        prepareCall.setString("name", "dld3");
        prepareCall.registerOutParameter("outname", Types.VARCHAR);
        prepareCall.registerOutParameter("cur_arg", OracleTypes.CURSOR);

        prepareCall.executeQuery();
        System.out.println(prepareCall.getString("outname"));

        ResultSet resultSet = (ResultSet) prepareCall.getObject("cur_arg");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            System.out.println(resultSet.getString("nickname"));
        }
        conn.close();
    }

    @Test
    public void testSQLServer() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://129.204.212.96:1433;DatabaseName=zh");
        bds.setUsername("sa");
        bds.setPassword("!@#$QWERqwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call first(?, ?)}");
        prepareCall.setString("name", "a");
        prepareCall.registerOutParameter("tmpname", Types.VARCHAR);

        boolean flag = prepareCall.execute();

        boolean hasMoreResult = prepareCall.getMoreResults();
        while (hasMoreResult) {
            ResultSet resultSet = prepareCall.getResultSet();
            if (resultSet == null) {
                break;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("nickname"));
            }
            System.out.println(1);
        }
        System.out.println("输出参数...");
        System.out.println(prepareCall.getString("tmpname"));

        conn.close();
    }
}
