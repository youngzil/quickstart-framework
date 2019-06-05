/**
 * 项目名称：quickstart-javase 
 * 文件名：RamdomExample.java
 * 版本信息：
 * 日期：2017年7月6日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.example;

import java.util.Random;

/**
 * RamdomExample
 * 
 * @author：youngzil@163.com
 * @2017年7月6日 下午11:34:47
 * @version 1.0
 */
public class RamdomExample {

    public static void main(String[] args) {
        // 方法一r.nextInt(int i) 返回一个伪随机数，它是取自此随机数生成器序列的、在
        // 0（包括）和指定值（不包括）之间均匀分布的int值
        Random r = new Random();
        System.out.println(r.nextInt(100));

        // 方法二
        // random.nextInt(max)表示生成[0,max]之间的随机数，然后对(max-min+1)取模。
        // 以生成[10,20]随机数为例，首先生成0-20的随机数，然后对(20-10+1)取模得到[0-10]之间的随机数，然后加上min=10，最后生成的是10-20的随机数
        int min = 100;
        int max = 300;
        Random random = new Random();
        int value = random.nextInt(max) % (max - min + 1) + min;
        System.out.println(value);

        // 方法三
        // (数据类型)(最小值+Math.random()*(最大值-最小值+1))
        int ra = (int) (min + Math.random() * (max - min + 1));
        System.out.println(ra);

        // 方法四
        // （类型）最小值+Random.nextInt((最大值-最小值 + 1))
        while (true) {

            int ra4 = min + r.nextInt(max - min + 1);
            System.out.println(ra4);
        }
    }

}
