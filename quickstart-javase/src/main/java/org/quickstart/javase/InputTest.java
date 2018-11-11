/**
 * 项目名称：quickstart-javase 
 * 文件名：InputTest.java
 * 版本信息：
 * 日期：2018年11月5日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * InputTest
 * 
 * @author：youngzil@163.com
 * @2018年11月5日 下午8:01:50
 * @since 1.0
 */
public class InputTest {

    public static void main(String[] args) throws IOException {

        // 1、使用标准输入串对象System.in
        System.in.read();// 一次只读入一个字节数据，而我们通常要取得一个字符串或一组数字，这就很不适合，需要其他方法取得这样的输入，这时可以使用java.util.Scanner类。

        // System.in.read()一次只读入一个字节数据，而我们通常要取得一个字符串或一组数字
        // System.in.read()返回一个整数
        // 必须初始化
        // int read = 0;
        char read = '0';
        System.out.println("输入数据：");
        try {
            // read = System.in.read();
            read = (char) System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("输入数据：" + read);

        // 2、使用Scanner取得一个字符串或一组数字
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter a string : ");
        System.out.print("Your input is :" + sc.next());

        System.out.print("输入");
        Scanner scan = new Scanner(System.in);
        String read2 = scan.nextLine();
        System.out.println("输入数据：" + read2);
        /*在新增一个Scanner对象时需要一个System.in对象，因为实际上还是System.in在取得用户输入。
        Scanner的next( )方法用以取得用户输入的字符串；
        nextInt( )将取得的输入字符串转换为整数类型；
        同样，nextFloat( )转换成浮点型；
        nextBoolean( )转换成布尔型。*/

        // 3、使用BufferedReader取得含空格的输入
        // Scanner取得的输入以space, tab, enter 键为结束符，要想取得包含space在内的输入，可以用java.io.BufferedReader类来实现。
        // 使用BufferedReader的readLine( )方法 ,必须要处理java.io.IOException异常

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // java.io.InputStreamReader继承了Reader类
        String tx = br.readLine();
        System.out.println(tx);

        String read3 = null;
        System.out.print("输入数据：");
        try {
            read3 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输入数据：" + read3);

    }

}
