package com.zh.annotations.database.exercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by zhheng on 2016/3/26.
 */
public class SQLExecuteUtil {
    public static boolean executeSql(String sql){
        Connection conn = MyJDBCHelper.getConnection();
        PreparedStatement ptst = null;
        boolean success = false;
        try {
            conn.setAutoCommit(false);
            ptst = conn.prepareStatement(sql);
            //....没加sql有参数的情况，这个练习只有建表
            success = ptst.execute();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("sql执行出现异常。" + e.getMessage());
            return success;
        } finally {
            try {
                conn.rollback();
                if(conn != null)
                    conn.close();
                conn = null;
            } catch (SQLException e) {
                System.out.println("回滚出现异常" + e.getMessage());
            }
        }
        return success;
    }
}
