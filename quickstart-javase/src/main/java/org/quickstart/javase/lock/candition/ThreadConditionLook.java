/**
 * 项目名称：quickstart-javase 
 * 文件名：ThreadConditionLook.java
 * 版本信息：
 * 日期：2018年4月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.lock.candition;

/**
 * ThreadConditionLook 
 *  
 * @author：youngzil@163.com
 * @2018年4月28日 下午1:34:51 
 * @since 1.0
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadConditionLook {

    /**
     * @param args
     */
    // create a user's Account,watch out the "static"
    private static Account account = new Account();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();
        System.out.println("Thread 1\t\tThread 2\t\t\t\tBalance");
    }

    private static class Account {
        // create a new lock
        private static Lock lock = new ReentrantLock();
        // craete a condition
        private static Condition newDeposit = lock.newCondition();
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int mount) throws InterruptedException {

            System.out.println(Thread.currentThread().getName() + "请求withdraw锁" + System.currentTimeMillis());
            lock.lock();// Acqurie the lock
            System.out.println(Thread.currentThread().getName() + "获得withdraw锁" + System.currentTimeMillis());
            Thread.sleep(100);
            try {
                while (balance < mount) {
                    System.out.println("\t\t\tWait for a deposit" + System.currentTimeMillis());
                    Thread.sleep(100);
                    newDeposit.await();
                }
                balance -= mount;
                System.out.println("\t\t\tWithdraw " + mount + "\t\t\t\t\t" + getBalance() + System.currentTimeMillis());
            } catch (InterruptedException e) {

            } finally {
                Thread.sleep(100);
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放withdraw锁" + System.currentTimeMillis());
            }
        }

        public void depsoit(int mount) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + "请求depsoit锁" + System.currentTimeMillis());
            lock.lock();// Acqurie the lock
            System.out.println(Thread.currentThread().getName() + "获得depsoit锁" + System.currentTimeMillis());
            Thread.sleep(100);
            try {
                balance += mount;
                System.out.println("Deposit " + mount + "\t\t\t\t\t\t\t\t" + getBalance() + System.currentTimeMillis());
                Thread.sleep(100);
                newDeposit.signalAll();
            } finally {
                Thread.sleep(100);
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放depsoit锁" + System.currentTimeMillis());
            }
        }
    }
    // Task for deposit account;
    private static class DepositTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            try {
                while (true) {
                    account.depsoit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {

            }

        }

    }
    // Task for withdraw account
    private static class WithdrawTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                while (true) {
                    account.withdraw((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {

            }
        }

    }
}
