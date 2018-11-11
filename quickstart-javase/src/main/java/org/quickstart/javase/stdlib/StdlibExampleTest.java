/**
 * 项目名称：quickstart-javase 
 * 文件名：StdlibExampleTest.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.stdlib;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * StdlibExampleTest
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午10:09:53
 * @since 1.0
 */
public class StdlibExampleTest {

    public static void main(String args[]) {
        // In&Out的用法
        Out out = new Out(System.out);
        out.println("Read a file of integers: ");
        In in = new In(args[0]); // args[0]的意思是输入的第一个参数, 即要读取的文件.
        int arr[] = in.readAllInts();
        for (int i : arr) {
            out.print(i + " ");
        }
        out.println("end");

        // StdIn&StdOut的用法
        StdOut.println("Please input an array of integers (Ctrl+Z end): ");
        while (!StdIn.isEmpty()) {
            int val = StdIn.readInt();
            StdOut.print(val + " ");
        }
        StdOut.println("end");
    }

}
