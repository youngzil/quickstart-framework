/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：ConnectionPool.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.flyweight.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * ConnectionPool
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午11:25:39
 * @since 1.0
 */
public class ConnectionPool {

    private Vector<Connection> pool;

    /*公有属性*/
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "root";
    private String driverClassName = "com.mysql.jdbc.Driver";

    private int poolSize = 100;
    private static ConnectionPool instance = null;
    Connection conn = null;

    /*构造方法，做一些初始化工作*/
    private ConnectionPool() {
        pool = new Vector<Connection>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                conn = DriverManager.getConnection(url, username, password);
                pool.add(conn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /* 返回连接到连接池 */
    public synchronized void release() {
        pool.add(conn);
    }

    /* 返回连接池中的一个数据库连接 */
    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

}
