/**
 * 项目名称：quickstart-xmltest 
 * 文件名：Myframe.java
 * 版本信息：
 * 日期：2016年12月16日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.xml.sudoku;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Myframe
 * 
 * @author：youngzil@163.com
 * @2016年12月16日 上午9:38:47
 * @version 1.0
 */
class Myframe2 extends JFrame {
    public static Object obj = new Object();
    public final static JTextField[][] filed = new JTextField[9][9];

    public Myframe2() {
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                filed[a][b] = new JTextField();
                filed[a][b].setText("");
            }
        }
        JPanel jpan = new JPanel();
        jpan.setLayout(new GridLayout(9, 9));
        for (int a = 8; a > -1; a--) {
            for (int b = 0; b < 9; b++) {
                jpan.add(filed[b][a]);
            }
        }
        add(jpan, BorderLayout.CENTER);
        JPanel jpb = new JPanel();
        JButton button1 = new JButton("calc");
        JButton button2 = new JButton("close");
        jpb.add(button1);
        jpb.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                synchronized (obj) {
                    for (int a = 0; a < 9; a++) {
                        for (int b3 = 0; b3 < 9; b3++) {
                            int pp = 0;
                            if (!(filed[a][b3].getText().trim().equals(""))) {
                                pp = Integer.parseInt(filed[a][b3].getText().trim());
                                Calculate2.b[a][b3] = pp;
                            }
                        }
                    }
                }
                synchronized (obj) {
                    new Thread(new Calculate2()).start();
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        add(jpb, BorderLayout.SOUTH);
    }
}
