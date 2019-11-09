package org.quickstart.apache.commons.io;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:12
 */
public class FileUtilsTest {

  @Test
  public void test() throws IOException {

    File file = new File("/commons/io/project.properties");
    List lines = FileUtils.readLines(file, "UTF-8");

    // org.apache.commons.io.LineIterator类提供了一种弹性的方式来操作line-based的文件。LineIterator的实例可以通过FileUtils或者IOUtils的钢厂方法直接创建。

    LineIterator it = FileUtils.lineIterator(file, "UTF-8");

    try {
      while (it.hasNext()) {
        String line = it.nextLine(); // do something with line
      }
    } finally {
      LineIterator.closeQuietly(it);
    }

  }

}
