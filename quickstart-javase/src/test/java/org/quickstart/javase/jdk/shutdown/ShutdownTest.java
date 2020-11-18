package org.quickstart.javase.jdk.shutdown;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @version v1.0
 * @date 2020/11/13 10:13
 */
public class ShutdownTest {
    public static void main(String[] args) {
        System.out.println("Shutdown Test");

        Signal sg = new Signal("TERM"); // kill -15 pid
        // 监听信号量
        Signal.handle(sg, new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                System.out.println("signal handle: " + signal.getName());
                System.exit(0);
            }
        });
        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                // 在关闭钩子中执行收尾工作
                // 注意事项：
                // 1.在这里执行的动作不能耗时太久
                // 2.不能在这里再执行注册，移除关闭钩子的操作
                // 3 不能在这里调用System.exit()
                System.out.println("do shutdown hook");
            }
        });

        mockWork();

        System.out.println("Done.");
        System.exit(0);
    }

    // 模拟进程正在运行
    private static void mockWork() {
        //mockRuntimeException();
        //mockOOM();
        try {
            Thread.sleep(120 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 模拟在应用中抛出RuntimeException时会调用注册钩子
    private static void mockRuntimeException() {
        throw new RuntimeException("This is a mock runtime ex");
    }

    // 模拟应用运行出现OOM时会调用注册钩子
    // -xms10m -xmx10m
    private static void mockOOM() {
        List list = new ArrayList();
        for(int i = 0; i < 1000000; i++) {
            list.add(new Object());
        }
    }
}
