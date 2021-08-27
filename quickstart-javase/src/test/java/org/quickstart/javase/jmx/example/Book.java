/**
 * 项目名称：quickstart-javase 
 * 文件名：Book.java
 * 版本信息：
 * 日期：2018年6月11日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Book
 * 
 * @author：youngzil@163.com
 * @2018年6月11日 下午9:50:43
 * @since 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Book {
    public String name;
    public double price;
}
