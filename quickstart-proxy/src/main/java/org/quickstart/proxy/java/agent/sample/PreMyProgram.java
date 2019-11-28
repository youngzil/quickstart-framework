/**
 * 项目名称：quickstart-proxy 
 * 文件名：PreMyProgram.java
 * 版本信息：
 * 日期：2018年8月12日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.java.agent.sample;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import javassist.convert.Transformer;

/**
 * PreMyProgram
 * 
 * https://www.cnblogs.com/aspirant/p/8796974.html
 * 
 * @author：youngzil@163.com
 * @2018年8月12日 上午10:07:40
 * @since 1.0
 */
public class PreMyProgram {

    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中 并被同一个System ClassLoader装载 被统一的安全策略(security policy)和上下文(context)管理
     *
     * @param agentArgs
     * @param inst
     * @author SHANHY
     * @create 2016年3月30日
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("PreMyProgram.premain1 方法执行，agentArgs : " + agentArgs);
        
        // 添加Transformer
        inst.addTransformer(new MyTransformer());

      // inst.redefineClasses();//多个代理可以同时执行，按照代理指定的顺序被依次调用




          // ClassDefinition def = new ClassDefinition(MyProgram.class,Transformer.getBytesFromFile(Transformer.classNumberReturns2));
          // inst.redefineClasses(new ClassDefinition[] { def });
          // System.out.println("success");





//        inst.addTransformer(new ClassFileTransformer() {
//            @Override
//            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                System.out.println("premain load Class     :" + className);
//                return classfileBuffer;
//            }
//        }, true);
        
    }

    /**
     * 如果不存在 premain(String agentOps, Instrumentation inst) 则会执行 premain(String agentOps)
     *
     * @param agentArgs
     * @author SHANHY
     * @create 2016年3月30日
     */
    public static void premain(String agentArgs) {
        System.out.println("PreMyProgram.premain2 方法执行，agentArgs : " + agentArgs);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
