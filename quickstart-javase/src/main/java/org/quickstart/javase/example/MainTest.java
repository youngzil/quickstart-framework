/**
 * 项目名称：quickstart-javase 
 * 文件名：MainTest.java
 * 版本信息：
 * 日期：2018年3月22日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.example;

/**
 * MainTest http://blog.csdn.net/zhumintao/article/details/53818972
 * 
 * @author：youngzil@163.com
 * @2018年3月22日 下午4:08:35
 * @since 1.0
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println(new B().getValue());
    }

    static class A {
        protected int value;

        public A(int v) {
            setValue(v);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            try {
                value++;
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
            return value;
        }
    }

    static class B extends A {
        public B() {
            super(5);
            setValue(getValue() - 3);
        }

        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}
