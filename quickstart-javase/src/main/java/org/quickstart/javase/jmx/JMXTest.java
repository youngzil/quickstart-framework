/**
 * 项目名称：quickstart-javase 
 * 文件名：JMXTest.java
 * 版本信息：
 * 日期：2018年3月26日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.jmx;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 * JMXTest
 * 
 * @author：youngzil@163.com
 * @2018年3月26日 下午4:40:45
 * @since 1.0
 */
public class JMXTest {

    private static final Logger LOG = LoggerFactory.getLogger(JMXTest.class);

    static final long MB = 1024 * 1024;
    static final long GB = 1024 * 1024 * 1024;

    public static void main(String[] args) {

        // 初始化静态信息
        OperatingSystemMXBean osMXBean = java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        RuntimeMXBean runtimeMXBean = java.lang.management.ManagementFactory.getRuntimeMXBean();
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();
        List<MemoryPoolMXBean> memoryPoolMXBeanList = java.lang.management.ManagementFactory.getMemoryPoolMXBeans();

        String jvmInfo = osMXBean.getName() + ", " + osMXBean.getArch() + " ," + osMXBean.getVersion();
        int totalProcessorCount = osMXBean.getAvailableProcessors();
        System.out.println(jvmInfo);
        System.out.println(totalProcessorCount);
        System.out.println("VMInfo# operatingSystem class => " + osMXBean.getClass().getName());

        if (isSunOsMBean(osMXBean)) {
            long totalPhysicalMemory = getLongFromOperatingSystem(osMXBean, "getTotalPhysicalMemorySize");
            long freePhysicalMemory = getLongFromOperatingSystem(osMXBean, "getFreePhysicalMemorySize");
            long maxFileDescriptorCount = getLongFromOperatingSystem(osMXBean, "getMaxFileDescriptorCount");
            long currentOpenFileDescriptorCount = getLongFromOperatingSystem(osMXBean, "getOpenFileDescriptorCount");

            String dd = String.format("\ttotalPhysicalMemory:\t%,.2fG\n" + "\tfreePhysicalMemory:\t%,.2fG\n" + "\tmaxFileDescriptorCount:\t%s\n" + "\tcurrentOpenFileDescriptorCount:\t%s\n",
                    (float) totalPhysicalMemory / GB, (float) freePhysicalMemory / GB, maxFileDescriptorCount, currentOpenFileDescriptorCount);

            System.out.println(dd);
        }

        String osInfo = runtimeMXBean.getVmVendor() + " " + runtimeMXBean.getSpecVersion() + " " + runtimeMXBean.getVmVersion();
        System.out.println("osInfo=" + osInfo);

        long curUptime = runtimeMXBean.getUptime();
        System.out.println("curUptime=" + curUptime);

        // 初始化processGCStatus;
        System.out.println("----------------------garbageCollectorMXBeanList----------------------");
        for (GarbageCollectorMXBean garbage : garbageCollectorMXBeanList) {
            String name = garbage.getName();
            long curTotalGcCount = garbage.getCollectionCount();
            long curtotalGcTime = garbage.getCollectionTime();
            System.out.println("name=" + name + ",curTotalGcCount=" + curTotalGcCount + ",curtotalGcTime=" + curtotalGcTime);
        }

        // 初始化processMemoryStatus
        if (memoryPoolMXBeanList != null && !memoryPoolMXBeanList.isEmpty()) {

            System.out.println("----------------------memoryPoolMXBeanList----------------------");

            for (MemoryPoolMXBean pool : memoryPoolMXBeanList) {
                String name = pool.getName();
                long initSize = pool.getUsage().getInit();
                long maxSize = pool.getUsage().getMax();
                long commitedSize = pool.getUsage().getCommitted();
                long used = pool.getUsage().getUsed();
                System.out.println("name=" + name + ",initSize=" + initSize + ",maxSize=" + maxSize + ",commitedSize=" + commitedSize + ",used=" + used);
            }
        }

    }

    @Test
    public void testVM() {
        List<VirtualMachineDescriptor> vms = VirtualMachine.list();
        for (VirtualMachineDescriptor desc : vms) {
            System.out.println("----------------------------------");
            VirtualMachine vm;
            try {
                System.out.println("desc:" + desc);
                System.out.println("进程id:" + desc.id());
                vm = VirtualMachine.attach(desc);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            JMXConnector connector = null;
            try {
                Properties props = vm.getAgentProperties();
                for (Map.Entry<Object, Object> entry : props.entrySet()) {
                    System.out.println(entry.getKey() + "->" + entry.getValue());
                }

                String connectorAddress = props.getProperty("com.sun.management.jmxremote.localConnectorAddress");
                if (connectorAddress == null) {
                    System.out.println("connectorAddress  is  null");
                    continue;
                }
                System.out.println("conn:" + connectorAddress);
                // 以下代码用于连接指定的jmx，本地或者远程
                JMXServiceURL url = new JMXServiceURL(connectorAddress);
                // JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/TestJMXServer");
                connector = JMXConnectorFactory.connect(url);

                MBeanServerConnection mbeanConn = connector.getMBeanServerConnection();
                Set<ObjectName> beanSet = mbeanConn.queryNames(null, null);
                // ...
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connector != null)
                        connector.close();
                    break;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static long getLongFromOperatingSystem(OperatingSystemMXBean operatingSystem, String methodName) {
        try {
            final Method method = operatingSystem.getClass().getMethod(methodName, (Class<?>[]) null);
            method.setAccessible(true);
            return (Long) method.invoke(operatingSystem, (Object[]) null);
        } catch (final Exception e) {
            LOG.info(String.format("OperatingSystemMXBean %s failed, Exception = %s ", methodName, e.getMessage()));
        }

        return -1;
    }

    public static boolean isSunOsMBean(OperatingSystemMXBean operatingSystem) {
        final String className = operatingSystem.getClass().getName();

        return "sun.management.OperatingSystemImpl".equals(className);
        // return "com.sun.management.UnixOperatingSystem".equals(className);
    }

}
