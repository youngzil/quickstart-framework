/**
 * 项目名称：quickstart-proxy 
 * 文件名：ExampleTest.java
 * 版本信息：
 * 日期：2018年4月17日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.javassist.bytecode;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.quickstart.proxy.statics.javassist.Student;

/**
 * ExampleTest
 * 
 * @author：youngzil@163.com
 * @2018年4月17日 下午6:20:50
 * @since 1.0
 */
public class ExampleTest {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("Student");

        // byte[] b=cc.toBytecode();

        cc.stopPruning(true);

        CtMethod[] cms = cc.getDeclaredMethods();

        for (CtMethod cm : cms) {
            cm.insertBefore("System.out.println(\"hello before : \"+this.name);");
            cm.insertAfter("System.out.println(\"hello after : \"+this.name);");
        }

        Class clz = cc.toClass();
        Student u = (Student) clz.getConstructor(new Class[] {}).newInstance(new Object[] {});

        u.setName("alleni");

        System.out.println("ExampleTest.main=" + u.getName());

        // cc.writeFile();
    }

}
