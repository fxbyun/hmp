package com.qiaobei.hmp.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {


    public static Connection getColdConnection() {
        String userName = "qiaobei";
        String pwd = "Qpart2015fred";
        String url = "jdbc:mysql://101.200.234" +
                ".252:3306/hmp?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull" +
                "&allowMultiQueries=true";
        //String userName = "root";
        //String pwd = "root";
        //String url = "jdbc:mysql://127.0.0.1:3306/hmp?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior
        // =convertToNull" +
        //        "&allowMultiQueries=true";


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("注册云端驱动失败！");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, pwd);
        } catch (SQLException e) {
            System.out.println("创建云端连接失败！");
        } finally {
            return conn;
        }
    }

    public static Connection getXcConnection() {
        String userName = "root";
        String pwd = "root";
        String url = "jdbc:mysql://192.168.1" +
                ".158:3306/hmp?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull" +
                "&allowMultiQueries=true";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("注册秀才驱动失败！");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, pwd);
        } catch (SQLException e) {
            System.out.println("创建秀才连接失败！");
        } finally {
            return conn;
        }
    }


}
