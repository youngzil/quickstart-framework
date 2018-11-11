/**
 * 项目名称：quickstart-javase 
 * 文件名：ProcessBuilderTest.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.process;

import java.io.IOException;
import java.util.Scanner;

/**
 * ProcessBuilderTest
 * 
 * @author：youngzil@163.com
 * @2017年7月23日 下午7:04:04
 * @version 2.0
 */
public class ProcessBuilderTest {
    public static void main(String[] args) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig/all");
        Process process = pb.start();
        Scanner scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
