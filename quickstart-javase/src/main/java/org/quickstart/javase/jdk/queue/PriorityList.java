/**
 * 项目名称：quickstart-javase 
 * 文件名：PriorityList.java
 * 版本信息：
 * 日期：2017年12月8日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.jdk.queue;

import java.util.PriorityQueue;

/**
 * PriorityList 优先级队列 主要和次要的优先级排序 该列表的排序顺序也是通过实现Comparable而进行控制的
 * 
 * @author：youngzil@163.com
 * @2017年12月8日 下午5:53:55
 * @since 1.0
 */
public class PriorityList extends PriorityQueue<PriorityList.ToDoItem> {

    static class ToDoItem implements Comparable<ToDoItem> {
        private char primary;// 主要的
        private int secondary;// 二
        private String item;

        public ToDoItem(String item, char primary, int secondary) {
            this.primary = primary;
            this.secondary = secondary;
            this.item = item;
        }

        public int compareTo(ToDoItem o) {
            if (primary > o.primary)// 先比较主要的
                return +1;
            if (primary == o.primary)
                if (secondary > o.secondary) // 再比较次要的
                    return +1;
                else if (secondary == o.secondary)
                    return 0;
            return -1;
        }

        public String toString() {
            return Character.toString(primary) + secondary + ": " + item;
        }
    }

    public void add(String td, char pri, int sec) {
        super.add(new ToDoItem(td, pri, sec));
    }

    public static void main(String[] args) {
        PriorityList to = new PriorityList();
        to.add("Empty Trash", 'C', 4);
        to.add("Feed dog", 'A', 2);
        to.add("Feed bird", 'B', 7);
        to.add("Mow lawn", 'C', 3);
        to.add("Water lawn", 'A', 1);
        to.add("Feed cat", 'B', 1);

        while (!to.isEmpty()) {
            System.out.println(to.remove());
        }
    }
}
