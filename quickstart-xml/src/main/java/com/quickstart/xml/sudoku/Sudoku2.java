/**
 * 项目名称：quickstart-xmltest 
 * 文件名：Sudoku.java
 * 版本信息：
 * 日期：2016年12月16日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.xml.sudoku;

import javax.swing.JFrame;

/**
 * Sudoku
 * 
 * @author：youngzil@163.com
 * @2016年12月16日 上午9:41:28
 * @version 1.0
 */
public class Sudoku2 {
    public static void main(String[] args) {
        Myframe2 myf = new Myframe2();
        myf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myf.setTitle("sudoku");
        myf.setSize(500, 500);
        myf.setVisible(true);
    }
}
