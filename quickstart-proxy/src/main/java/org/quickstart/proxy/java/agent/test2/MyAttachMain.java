package org.quickstart.proxy.java.agent.test2;

import com.sun.tools.attach.VirtualMachine;

public class MyAttachMain {
    public static void main(String[] args) throws Exception {
        //        VirtualMachine vm = VirtualMachine.attach(args[0]);
        String PID="5660";
        VirtualMachine vm = VirtualMachine.attach(PID);
        try {
            vm.loadAgent("/Users/lengfeng/git/quickstart-framework/quickstart-proxy/target/my-attach-agent.jar");//agent.jar全路径地址
        } finally {
            vm.detach();
        }
    }
}
