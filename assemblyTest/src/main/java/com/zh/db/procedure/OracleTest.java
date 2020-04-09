package com.zh.db.procedure;

import oracle.jdbc.OracleTypes;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.*;

/**
 * 查所有存储过程信息
 * SELECT * FROM user_procedures;
 *
 * 查存储过程名为PROC_NAME的参数信息
 * select * from user_arguments where object_name='PROC_NAME';
 *
 *  查看数据库版本
 * select * from v$version;
 */
public class OracleTest {


    @Test
    public void testProcedureMetaData() throws SQLException, ClassNotFoundException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection conn = bds.getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getProcedureColumns(null, "ZH", "TEST_QUERY", null);
        while (rs.next()) {
            String paramName = rs.getString("COLUMN_NAME");
            if ("CUR".equalsIgnoreCase(paramName)) {
                // 这个游标类型和OracleTypes里定义的不对应
                System.out.println(rs.getInt("DATA_TYPE"));
            }
        }
        conn.close();
    }

    @Test
    public void testQuery1() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection conn = bds.getConnection();
        String sql = "{ call TEST_QUERY(?, ?, ?, ?) }";
        System.out.println(sql);
        CallableStatement prepareCall = conn.prepareCall(sql);
        prepareCall.registerOutParameter("procOutResultMsg", Types.VARCHAR);
        prepareCall.registerOutParameter("procOutErrorCode", Types.INTEGER);
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
        System.out.println(prepareCall.getInt("procOutResultType"));
        System.out.println(prepareCall.getString("procOutResultMsg"));
        System.out.println(prepareCall.getInt("procOutErrorCode"));

        conn.close();
    }


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

    @Test
    public void testDuplicatePrimaryKeyException() throws SQLException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection connection = bds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"user\"(\"id\", \"code\") values('1', '3')");
        try {
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrimaryKey() throws SQLException, ClassNotFoundException {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        bds.setUrl("jdbc:oracle:thin:@129.204.212.96:1521:helowin");
        bds.setUsername("zh");
        bds.setPassword("1234qwer");

        Connection conn = bds.getConnection();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, "ZH", "user");
        ResultSetMetaData pkmd = resultSet.getMetaData();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("PK_NAME"));
        }
    }
}
