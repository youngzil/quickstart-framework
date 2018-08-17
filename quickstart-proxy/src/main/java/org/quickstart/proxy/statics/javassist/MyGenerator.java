/**
 * 项目名称：quickstart-proxy 
 * 文件名：MyGenerator.java
 * 版本信息：
 * 日期：2018年8月11日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.statics.javassist;

import java.io.File;
import java.io.FileOutputStream;

import org.quickstart.proxy.clazz.loader.MyTest;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

/**
 * MyGenerator
 * 
 * @author：yangzl@asiainfo.com
 * @2018年8月11日 下午11:26:24
 * @since 1.0
 */
public class MyGenerator {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        // 创建Programmer类
        CtClass cc = pool.makeClass("org.quickstart.proxy.statics.javassist.Programmer");
        // 定义code方法
        CtMethod method = CtNewMethod.make("public void code(){}", cc);
        // 插入方法代码
        method.insertBefore("System.out.println(\"I'm a Programmer from javassist,Just Coding.....\");");
        cc.addMethod(method);
        
        File currentFile = new File(".");
        String clazzFile = currentFile.getCanonicalPath() + MyTest.javassistClazzBaseDir;
        
        // 保存生成的字节码
        cc.writeFile(clazzFile);
    }
}
