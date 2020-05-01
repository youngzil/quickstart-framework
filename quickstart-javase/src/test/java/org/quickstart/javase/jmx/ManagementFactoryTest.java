package org.quickstart.javase.jmx;

import java.io.IOException;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;
import java.util.logging.LoggingMXBean;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class ManagementFactoryTest {

    public static void main(String[] args) throws IOException, InterruptedException, NoClassDefFoundError {
        /*OperatingSystemMXBean：系统状态信息
        RuntimeMXBean：VM启动参数
        
        ClassLoadingMXBean：类加载情况
        
        MemoryMXBean：总内存内存使用状态
        MemoryPoolMXBeans：各个内存区使用状态
        
        ThreadMXBean：线程状态
        
        GarbageCollectorMXBeans：GC状态
        
        CompilationMXBean：Java虚拟机编译相关，编译器名称等
        MemoryManagerMXBeans：内存管理器的信息*/

        // 获取远程进程的信息
        /* // 协议、远程机器的ip地址，远程java进程运行的jmxremote端口
        JMXServiceURL serviceURL = new JMXServiceURL(null, "10.11.20.101", 2345);
        JMXConnector connector = JMXConnectorFactory.connect(serviceURL);
        MBeanServerConnection mbsBeanServerConnection = connector.getMBeanServerConnection();
        
        // 获取远程的memorymxbean
        MemoryMXBean memBean = ManagementFactory.newPlatformMXBeanProxy(mbsBeanServerConnection, ManagementFactory.MEMORY_MXBEAN_NAME, MemoryMXBean.class);
        // 获取远程的operatingsystemmcbean
        com.sun.management.OperatingSystemMXBean opMxBean =
                ManagementFactory.newPlatformMXBeanProxy(mbsBeanServerConnection, ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, com.sun.management.OperatingSystemMXBean.class);
        
        TimeUnit.SECONDS.sleep(5);
        
        MemoryUsage heap = memBean.getHeapMemoryUsage();
        MemoryUsage nonheap = memBean.getNonHeapMemoryUsage();
        heap.getUsed();// 堆使用大小
        nonheap.getUsed();
        heap.getCommitted();
        nonheap.getCommitted();
        
        // 采集CPU利用率需要自己计算一下，因为API只提供了获取cpu的使用时间，我得在两次系统时间间隔内获取两次CPU的使用时间，得到在该时间间隔内cpu使用的时间，相除即得到CPU的使用率，当然误差肯定存在。
        long start = System.currentTimeMillis();
        long startT = opMxBean.getProcessCpuTime();
        
        TimeUnit.SECONDS.sleep(5);
        
        long end = System.currentTimeMillis();
        long endT = opMxBean.getProcessCpuTime();
        // end-start 为采集时间，单位ms
        // endT-startT 采集周期内CPU的使用时间，单位为ns
        // cpu使用率为
        double ratio = (endT - startT) / 1000000.0 / (end - start) / opMxBean.getAvailableProcessors();
        */

        System.out.println("================系统状态======================");
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        // 获取服务器的CPU个数
        System.out.println(operatingSystemMXBean.getAvailableProcessors());
        // 获取服务器的平均负载。这个指标非常重要，它可以有效的说明当前机器的性能是否正常，如果load过高，说明CPU无法及时处理任务。
        System.out.println(operatingSystemMXBean.getSystemLoadAverage());

        System.out.println("===============程序运行参数==================");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        // JVM启动参数
        System.out.println(runtimeMXBean.getInputArguments());
        // 系统属性
        System.out.println(runtimeMXBean.getSystemProperties());
        // JVM名字
        System.out.println(runtimeMXBean.getVmName());

        System.out.println("===============类加载状态============================");
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();
        // 获取当前JVM加载的类数量
        System.out.println(classLoadingMXBean.getLoadedClassCount());
        // 获取JVM总加载的类数量
        System.out.println(classLoadingMXBean.getTotalLoadedClassCount());
        // 获取JVM卸载的类数量
        System.out.println(classLoadingMXBean.getUnloadedClassCount());

        System.out.println("================内存状态===========================");
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 获取堆内存使用情况，包括初始大小，最大大小，已使用大小等，单位字节
        System.out.println(memoryMXBean.getHeapMemoryUsage().toString());// 堆内存使用情况

        memoryMXBean.getHeapMemoryUsage().getInit();// 初始的总内存
        memoryMXBean.getHeapMemoryUsage().getMax();// 最大可用内存
        memoryMXBean.getHeapMemoryUsage().getUsed();// 已使用的内存

        // 获取堆外内存使用情况。
        System.out.println(memoryMXBean.getNonHeapMemoryUsage().toString());

        System.out.println("================堆内存状态======================");
        // 这里会返回老年代，新生代等内存区的使用情况，按需自取就好
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        memoryPoolMXBeans.forEach((pool) -> {
            System.out.println(pool.getName());
            System.out.println(pool.getUsage());
        });

        System.out.println("===============线程状态=======================");
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 获取当前JVM内的线程数量，该指标非常重要。
        // 之前遇到过用为没有对该指标进行监控而导致问题无法及时定位的情况。
        System.out.println(threadMXBean.getThreadCount());
        System.out.println(threadMXBean.getCurrentThreadCpuTime());
        System.out.println(threadMXBean.getCurrentThreadUserTime());

        System.out.println("===============GC状态==========================");
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory
                .getGarbageCollectorMXBeans();
        garbageCollectorMXBeans.forEach(collector -> {
            System.out.println(collector.getName());
            System.out.println(collector.getCollectionCount());
            System.out.println(collector.getCollectionTime());
        });

        System.out.println("===============Java虚拟机编译相关，编译器名称等==========================");
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        System.out.println(compilationMXBean.getName());
        System.out.println("getTotalCompilationTime=" + compilationMXBean.getTotalCompilationTime());

        System.out.println("===============内存管理器的信息==========================");
        List<MemoryManagerMXBean> memoryManagerMXBeans = ManagementFactory.getMemoryManagerMXBeans();
        memoryManagerMXBeans.forEach(mmbean -> {
            System.out.println(mmbean.getName());
            System.out.println(mmbean.getMemoryPoolNames());
        });

        System.out.println("===============LoggingMXBean的信息==========================");
        // LoggingMXBean 是 java.util.logging 包的一部分，所以，要用 LogManager 类而不是 ManagementFactory 类来访问它，
        LoggingMXBean logBean = LogManager.getLoggingMXBean();
        logBean.getLoggerNames().forEach(loggerName -> {
            System.out.println("loggername=" + loggerName + "parentloggername=" + logBean.getParentLoggerName(loggerName));
        });

    }

}
