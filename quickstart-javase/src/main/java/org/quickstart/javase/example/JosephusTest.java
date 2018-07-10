/**
 * 项目名称：quickstart-javase 
 * 文件名：JosephusTest.java
 * 版本信息：
 * 日期：2018年6月19日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example;

import java.util.List;

/**
 * JosephusTest
 * 
 * https://blog.csdn.net/tingyun_say/article/details/52343897
 * 
 * @author：yangzl@asiainfo.com
 * @2018年6月19日 上午9:24:33
 * @since 1.0
 */
public class JosephusTest {
    
    public static void main(String[] args) {
        
    }

    void josephus(List list, int step) {
        int index = 0;
        while (list.size() > 1) {
            index = (index + step) % (list.size()) - 1;
            if (index < 0) {
                list.remove(list.size() - 1);
                index = 0;
            } else {
                list.remove(index);
            }
        }
    }

}
