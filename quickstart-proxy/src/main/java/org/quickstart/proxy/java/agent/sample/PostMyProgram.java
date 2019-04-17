/**
 * 项目名称：quickstart-proxy 
 * 文件名：PostMyProgram.java
 * 版本信息：
 * 日期：2018年8月12日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.java.agent.sample;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * PostMyProgram
 * 
 * https://www.jianshu.com/c/7f48adacf83e
 * 
 * @author：youngzil@163.com
 * @2018年8月12日 上午10:39:27
 * @since 1.0
 */
public class PostMyProgram {

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent Main called");
        System.out.println("agentArgs : " + agentArgs);

        // agentmain方法通过传入的Instrumentation实例获取当前系统中已加载的类。
        Class[] classes = inst.getAllLoadedClasses();
        for (Class cls : classes) {
            System.out.println(cls.getName());
        }

        inst.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                System.out.println("agentmain load Class  :" + className);
                return classfileBuffer;
            }
        }, true);

        // 重新转换目标类，也就是 Account 类。也就是说，你需要重新定义哪个类，需要指定，否则 JVM 不可能知道。
        // 还有一个类似的方法 redefineClasses ，注意，这个方法是在类加载前使用的。
        // 类加载后需要使用 retransformClasses 方法。
        // inst.retransformClasses(Account.class);

    }

    public static void agentmain(String args) {

    }

}
