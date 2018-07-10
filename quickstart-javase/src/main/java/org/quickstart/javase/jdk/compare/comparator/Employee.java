/**
 * 项目名称：quickstart-javase 
 * 文件名：Employee.java
 * 版本信息：
 * 日期：2018年4月23日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jdk.compare.comparator;

/**
 * Employee
 * 
 * @author：yangzl@asiainfo.com
 * @2018年4月23日 上午8:19:12
 * @since 1.0
 */
public final class Employee {

    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "name is: " + name + ", salary is: " + salary;
    }
}
