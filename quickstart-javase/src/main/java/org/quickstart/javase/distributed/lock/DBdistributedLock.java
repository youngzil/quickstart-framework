/**
 * 项目名称：quickstart-javase 
 * 文件名：DBdistributedLock.java
 * 版本信息：
 * 日期：2018年6月13日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.distributed.lock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * DBdistributedLock
 * 
 * @author：yangzl@asiainfo.com
 * @2018年6月13日 上午9:30:22
 * @since 1.0
 */
public class DBdistributedLock {

    private static DataSource dataSource;

    private static final String cmd = "select * from lock where uid = 1 for update";

    public DBdistributedLock(DataSource ds) {
        this.dataSource = ds;
    }

    public static interface CallBack {
        public void doAction();
    }

    public void lock(CallBack callBack) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // try get lock
            System.out.println(Thread.currentThread().getName() + " begin try lock");
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(cmd);
            rs = stmt.executeQuery();
            // do business thing
            callBack.doAction();
            // release lock
            conn.commit();
            System.out.println(Thread.currentThread().getName() + " release lock");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        final DBdistributedLock bdistributedLock = new DBdistributedLock(dataSource);
        bdistributedLock.lock(new CallBack() {
            @Override
            public void doAction() {
                System.out.println(Thread.currentThread().getName() + "beging do somthing");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "end do somthing");
            }
        });

    }
}
