package org.quickstart.apache.commons.compress;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:25
 */

import java.io.File;

import org.junit.jupiter.api.Test;

/**
 * 测试ZipFileUtil的压缩和解压缩方法
 *
 * @author souvc
 */
public class ZipFileUtilTest {


  @Test
  public void testCompressFiles2Zip() {
    // 存放待压缩文件的目录
    File srcFile = new File("C://test");
    // 压缩后的zip文件路径
    String zipFilePath = "c://test2//test.zip";
    if (srcFile.exists()) {
      File[] files = srcFile.listFiles();
      ZipFileUtil.compressFiles2Zip(files, zipFilePath);
    }
  }

  @Test
  public void testDecompressZip() {
    // 压缩包所在路径
    String zipFilePath = "c://test2//test.zip";
    // 解压后的文件存放目录
    String saveFileDir = "c://test2//";
    // 调用解压方法
    ZipFileUtil.decompressZip(zipFilePath, saveFileDir);
  }
}
