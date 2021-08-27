package org.quickstart.proxy.java.agent.test2;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTestMain {
    public static void main(String[] args) throws Exception {

        Class clazz = MyTestMain.class;

        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().endsWith(clazz.getName())) {
                VirtualMachine vm = VirtualMachine.attach(vmd.id());
                try {
                    vm.loadAgent("/Users/lengfeng/git/quickstart-framework/quickstart-proxy/target/my-attach-agent.jar");//agent.jar全路径地址
                    System.out.println("attach ok");
                } finally {
                    vm.detach();
                }
                vm.detach();
            }
        }

        while (true) {
            System.out.println(foo());
            TimeUnit.SECONDS.sleep(3);
        }
    }

    public static int foo() {
        return 100; // 修改后 return 50;
    }

    public static void say(Student student) {
        System.out.println(student.getAge());
    }

    @Data
    static class Student {
        private int id;
        private String name;
        private int age;
    }

}
