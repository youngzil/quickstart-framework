package org.quickstart.javase.jdk8.stream;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-09 16:10
 */
@Data
@AllArgsConstructor
public class Item {
  private String name;
  private int qty;
  private BigDecimal price;
  // constructors, getter/setters

}
