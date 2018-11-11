/**
 * 项目名称：quickstart-design-pattern 
 * 文件名：BuilderTest.java
 * 版本信息：
 * 日期：2018年1月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.design.pattern.builder;

/**
 * BuilderTest
 * 
 * @author：youngzil@163.com
 * @2018年1月26日 下午5:06:08
 * @since 1.0
 */
public class BuilderTest {

    public static void main(String[] args) {
        PersonDirector pd = new PersonDirector();
        Person manPerson = pd.constructPerson(new ManBuilder());
        Person womanPerson = pd.constructPerson(new WomanBuilder());

        System.out.println("manPerson head=" + manPerson.getHead());
        System.out.println("manPerson body=" + manPerson.getBody());
        System.out.println("manPerson foot=" + manPerson.getFoot());

        System.out.println("womanPerson head=" + womanPerson.getHead());
        System.out.println("womanPerson body=" + womanPerson.getBody());
        System.out.println("womanPerson foot=" + womanPerson.getFoot());
    }

}
