/**
 * 项目名称：quickstart-javase 
 * 文件名：SupplierTest.java
 * 版本信息：
 * 日期：2018年3月28日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk8.function;

import java.util.function.Supplier;

/**
 * SupplierTest
 * 
 * 函数式接口Supplier
 * 
 * @author：youngzil@163.com
 * @2018年3月28日 下午9:16:26
 * @since 1.0
 */
public class SupplierTest {

    public static void main(String[] args) {
        
        //简写
        Supplier<String> supplier1 = () -> "Test supplier";
        System.out.println(supplier1.get());
        
        //标准格式
        Supplier<Integer> supplier2 = () -> {
            return 20;
        };
        System.out.println(supplier2.get() instanceof Integer);
        
        Supplier<String> supplier = () -> "hello world";

        // get方法不接受参数，返回一个结果
        System.out.println("supplier = [" + supplier.get() + "]");

        // 替代不接受参数的工厂方法
        Supplier<Student> studentSupplier = () -> new Student();
        System.out.println(studentSupplier.get());

        // 因为Student的构造方法不接受参数，返回一个结果，符合Supplier接口的要求，可以简写如下：
        Supplier<Student> studentSupplier2 = Student::new;
        System.out.println(studentSupplier2.get());
    }

}


class Student {
    private String name = "zhangsan";
    private int age = 25;

    // 删掉这个会报错
    public Student() {

    }

    // 加上这个也不报错
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
