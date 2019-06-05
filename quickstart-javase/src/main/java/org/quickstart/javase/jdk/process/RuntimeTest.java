/**
 * 项目名称：quickstart-javase 
 * 文件名：RuntimeTest.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * RuntimeTest
 * 
 * @author：youngzil@163.com
 * @2017年7月23日 下午7:02:32
 * @version 2.0
 */
public class RuntimeTest {

    private static final int MY_TIMEOUT = 20;

    public static void main(String[] args) throws IOException, InterruptedException {
        String cmd = "cmd " + "/c " + "ipconfig/all";
        Process process = Runtime.getRuntime().exec(cmd);

        Scanner scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();

        // 如果进程没有在规定时间内退出，终止它并继续往前走。
        if (process.waitFor(MY_TIMEOUT, TimeUnit.MILLISECONDS)) {
            // 成功
        } else {
            process.destroyForcibly();
        }

        // 在你的代码结束前，确保所有的进程都已退出。僵尸进程会逐渐耗尽系统资源。
        List<Process> processes = new ArrayList<>();
        processes.add(process);
        for (Process p : processes) {
            if (p.isAlive()) {
                p.destroyForcibly();
            }
        }

    }
}
