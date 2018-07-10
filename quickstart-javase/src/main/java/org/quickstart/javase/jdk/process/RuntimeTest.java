/**
 * 项目名称：quickstart-javase 
 * 文件名：RuntimeTest.java
 * 版本信息：
 * 日期：2017年7月23日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.process;

import java.io.IOException;
import java.util.Scanner;

/**
 * RuntimeTest
 * 
 * @author：yangzl@asiainfo.com
 * @2017年7月23日 下午7:02:32
 * @version 2.0
 */
public class RuntimeTest {
    public static void main(String[] args) throws IOException {
        String cmd = "cmd " + "/c " + "ipconfig/all";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream());

        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
