package com.zh.mysql.procedure;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;

public class MySqlTest {

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
}
