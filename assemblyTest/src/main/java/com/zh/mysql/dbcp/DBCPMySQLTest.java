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
public class DBCPMySQLTest {

    @Test
    public void testmyql() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/zh?user=root&password=root&useUnicode=true&characterEncoding=gb18030&autoReconnect=true&failOverReadOnly=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&useSSL=false");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call first(?, ?)}");
        prepareCall.setString("name", "dld31");
        prepareCall.registerOutParameter("tmpname", Types.VARCHAR);

        ResultSet resultSet = prepareCall.executeQuery();
        System.out.println(prepareCall.getString("tmpname"));

        while (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
        }
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
}
