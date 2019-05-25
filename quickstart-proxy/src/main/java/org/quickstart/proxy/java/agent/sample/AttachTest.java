/**
 * 项目名称：quickstart-proxy 文件名：AttachTest.java 版本信息： 日期：2018年8月12日 Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.proxy.java.agent.sample;

import java.io.IOException;
import java.util.List;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * AttachTest
 *
 * https://www.jianshu.com/c/7f48adacf83e
 *
 * @author：youngzil@163.com
 * @2018年8月12日 上午10:44:32
 * @since 1.0
 */
public class AttachTest {

  public static void main(String[] args)
      throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {
    // VirtualMachine vm = VirtualMachine.attach(args[0]);// args[0]传入的是jvm的pid号
    // vm.loadAgent("/Users/jiangbo/Workspace/code/java/javaagent/loadagent.jar");
    // vm.loadAgent("F:\\workspace_aging_rejuvenate\\AgentMain\\agent.jar");

    // 该main方法步骤如下：
    // 1、获取当前系统所有的虚拟机，类似 jps 命令。
    // 2、循环所有虚拟机，找到 AccountMain 虚拟机。
    // 3、将当前JVM 链接上目标JVM，并加载 loadAgent jar 包且传递参数。
    // 4、卸载虚拟机。

    List<VirtualMachineDescriptor> list = VirtualMachine.list();
    for (VirtualMachineDescriptor vmd : list) {
      if (vmd.displayName().endsWith("AccountMain")) {
        VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
        virtualMachine.loadAgent("E:\\self\\demo\\out\\artifacts\\test\\test.jar ", "cxs");
        System.out.println("ok");
        virtualMachine.detach();
      }
    }


  }

}
