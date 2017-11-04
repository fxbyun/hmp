package com.qiaobei.hmp.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public static String userName = "qiaobei";
    public static String pwd = "Qpart2015fred";
    public static String url = "jdbc:mysql://101.200.234.252:3306/hmp?useUnicode=true&characterEncoding=UTF-8" +
            "&zeroDateTimeBehavior=convertToNull" +
            "&allowMultiQueries=true";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动失败！");
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, pwd);
        } catch (SQLException e) {
            System.out.println("创建连接失败！");
        } finally {
            return conn;
        }
    }

}
