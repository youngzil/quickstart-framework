package org.quickstart.javase.jdk.thread;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/9/3 19:39
 * @version v1.0
 */
// 实现Thread.UncaughtExceptionHandler 接口，创建异常处理器
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}
