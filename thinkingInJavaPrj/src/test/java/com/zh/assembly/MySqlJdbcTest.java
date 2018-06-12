package com.zh.assembly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class MySqlJdbcTest
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:10155/dn_zsk?user=root&password=123456&useUnicode=true&characterEncoding=gb2312&autoReconnect=true&failOverReadOnly=false";
        String sql = "INSERT INTO DN_Organization(id,`year`,`name`,levelCode,alias,parentId,tenantId,isLeaf,description,`index`,code) VALUES ('1_0a115fe1-5158-423a-9',0,'org_36D49A80070D',NULL,'浙江瀜瀜1111','0','1',1,'',0,'5')";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcUrl);
        PreparedStatement statement = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        int count = statement.executeUpdate();
        connection.commit();
        System.out.println(count);
    }
}
