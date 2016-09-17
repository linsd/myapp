package com.tasxxz.myapp.util;


import com.tasxxz.myapp.enums.DataBaseType;

import java.sql.*;

public class JdbcUtil {
    private static DataBaseType dbt = DataBaseType.MYSQL;
    public static final String DRIVER = dbt.getDriver();
    public static final String URL = dbt.getUrl();
    public static final String USER = dbt.getUser();
    public static final String PASSWORD = dbt.getPassword();

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);// 指定连接类型
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Statement stmt, ResultSet rs) {
        close(null, stmt, rs);
    }

    public static void close(Connection conn) {
        close(conn, null, null);
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != stmt) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}