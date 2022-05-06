package org.quickstart.apache.commons.io;

import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:12
 */
public class FilenameUtilsTest {

  @Test
  public void test() {

    String filename = "C:/commons/io/../lang/project.xml";
    String normalized = FilenameUtils.normalize(filename);// 结果是  "C:/commons/lang/project.xml"

  }

}
