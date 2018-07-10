package com.quickstart.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String url = null;
        String user = null;
        String password = null;
        String sql = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载错误");
            e.printStackTrace();// 打印出错详细信息
        }
        try {
            url = "jdbc:mysql://localhost/test?user=root&password=yqs2602555&useUnicode=true&&characterEncoding=gb2312&autoReconnect = true";// 简单写法：url
            user = "root";
            password = "yqs2602555";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("数据库链接错误");
            e.printStackTrace();
        }
        try {
            stmt = conn.createStatement();
            sql = "select * from dept";// dept这张表有deptno，deptname和age这三个字段
            rs = stmt.executeQuery(sql);// 执行sql语句
            while (rs.next()) {
                System.out.print(rs.getInt("deptno") + "   ");
                System.out.print(rs.getString("deptname") + "   ");
                System.out.println(rs.getInt("age") + "   ");
            }
        } catch (SQLException e) {
            System.out.println("数据操作错误");
            e.printStackTrace();
        }
        // 关闭数据库
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            System.out.println("数据库关闭错误");
            e.printStackTrace();
        }
    }

    // 3.使用PreparedStatement + 批处理

    public void exec3(Connection conn) {

        try {

            conn.setAutoCommit(false);

            Long beginTime = System.currentTimeMillis();

            PreparedStatement pst = conn.prepareStatement("insert into t1(id) values (?)");

            for (int i = 1; i <= 100000; i++) {

                pst.setInt(1, i);

                pst.addBatch();

                if (i % 1000 == 0) {// 可以设置不同的大小；如50，100，500，1000等等

                    pst.executeBatch();

                    conn.commit();

                    pst.clearBatch();

                }

            }

            Long endTime = System.currentTimeMillis();

            System.out.println("pst+batch：" + (endTime - beginTime) / 1000 + "秒");

            pst.close();

            conn.close();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

    }

}
