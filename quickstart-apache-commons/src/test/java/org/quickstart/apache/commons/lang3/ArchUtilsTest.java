package org.quickstart.apache.commons.lang3;

import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.arch.Processor;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 09:19
 */
public class ArchUtilsTest {

  @Test
  public void test() {

    Processor processor = ArchUtils.getProcessor();
    processor = ArchUtils.getProcessor("");

    processor.getArch();// 获取电脑处理器体系结构 32 bit、64 bit、unknown
    processor.getType();// 返回处理器类型 x86、ia64、ppc、unknown
    processor.is32Bit();// 检查处理器是否为32位
    processor.is64Bit();// 检查处理器是否为64位
    processor.isIA64();// 检查是否是英特尔安腾处理器类型
    processor.isPPC();// 检查处理器是否是电源PC类型
    processor.isX86();// 检查处理器是否是x86类型

  }

}
