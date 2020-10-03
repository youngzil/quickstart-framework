package org.quickstart.oshi;

import org.junit.Test;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/9/28 15:40
 * @version v1.0
 */
public class OSHITest {

    //    获取硬件信息
    @Test
    public void testHardwareInfo() {

        //系统信息
        SystemInfo si = new SystemInfo();
        //操作系统信息
        OperatingSystem os = si.getOperatingSystem();
        //硬件信息
        HardwareAbstractionLayer hal = si.getHardware();

    }

    //    1.获取内存相关信息
    @Test
    public void testMemoryInfo() {
        //系统信息
        SystemInfo si = new SystemInfo();
        //操作系统信息
        OperatingSystem os = si.getOperatingSystem();
        //硬件信息
        HardwareAbstractionLayer hal = si.getHardware();

        //内存相关信息
        GlobalMemory memory = hal.getMemory();
        //获取内存总容量
        String totalMemory = FormatUtil.formatBytes(memory.getTotal());
        //获取可用内存的容量
        String availableMemory = FormatUtil.formatBytes(memory.getAvailable());

        System.out.println("totalMemory=" + totalMemory);
        System.out.println("availableMemory=" + availableMemory);

    }

    //    2.获取 CPU 相关信息
    @Test
    public void testCpuInfo() {

        //系统信息
        SystemInfo si = new SystemInfo();
        //操作系统信息
        OperatingSystem os = si.getOperatingSystem();
        //硬件信息
        HardwareAbstractionLayer hal = si.getHardware();

        //CPU相关信息
        CentralProcessor processor = hal.getProcessor();
        //获取CPU名字
        String processorName = processor.getProcessorIdentifier().getName();
        //获取物理CPU数
        int physicalPackageCount = processor.getPhysicalPackageCount();
        //获取物理核心数
        int physicalProcessorCount = processor.getPhysicalProcessorCount();

        System.out.println("processorName=" + processorName);

        System.out.println("physicalPackageCount=" + physicalPackageCount);

        System.out.println("physicalProcessorCount=" + physicalProcessorCount);

    }

}
