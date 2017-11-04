package com.qiaobei.hmp.test.dataImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlutil {
    static private Connection conn = null;
    static private Statement statement = null;
    //    static private String username = "qiaobei";
//    static private String password = "Qpart2015fred";
//    static private String url = "jdbc:mysql://yijiazhen.com:3306/hmp?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull" +
//            "&allowMultiQueries=true";
    static private String username = "root";
    static private String password = "root";
    static private String url = "jdbc:mysql://192.168.1.222:3306/hmp?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull" +
            "&allowMultiQueries=true";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC驱动未找到!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库密码错误或数据库未开启!");
        }
    }

    public MySqlutil() {
    }

    public static Connection getConnect() {
        return conn;
    }

}
