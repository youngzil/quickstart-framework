package org.quickstart.apache.commons.io;

import java.io.IOException;

import org.apache.commons.io.FileSystemUtils;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:13
 */
public class EndianUtilsTest {

  @Test
  public void test() throws IOException {
    long freeSpace = FileSystemUtils.freeSpace("C:/");
  }

}
