package com.zh.mysql.procedure;

import oracle.jdbc.OracleTypes;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;

public class OracleTest {


    @Test
    public void testQuery() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call \"TEST_PAGE\"(?, ?, ?, ?, ?)}");
        prepareCall.setInt("pageBeginNum", 0);
        prepareCall.setInt("pageEndNum", 10);
        prepareCall.registerOutParameter("pageRowCount", Types.INTEGER);
        prepareCall.registerOutParameter("procOutResultType", Types.INTEGER);
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
        System.out.println(prepareCall.getInt("pageRowCount"));
        System.out.println(prepareCall.getInt("procOutResultType"));

        conn.close();
    }

    @Test
    public void testWriteBack() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection conn = bds.getConnection();

        CallableStatement prepareCall = conn.prepareCall("{call \"TEST_WRITEBACK\"(?, ?)}");
        prepareCall.setString("name", "a");
        prepareCall.registerOutParameter("procOutResultType", Types.INTEGER);

        boolean flag = prepareCall.execute();

        if (flag) {
            System.out.println("-------------------");
            ResultSet resultSet = (ResultSet) prepareCall.getObject("cur");

            int colCount = resultSet.getMetaData().getColumnCount();
            System.out.println(colCount);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("nickname"));
            }
        }

        System.out.println("--------out params------");
        System.out.println(prepareCall.getInt("procOutResultType"));

        conn.close();
    }
}
