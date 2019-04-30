package com.zh.db.procedure;

import oracle.jdbc.OracleTypes;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;

public class DB2Test {

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
}
