package org.quickstart.apache.commons.compress;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019/11/9 10:25
 */
// 第三部分：把文件打成war包。

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
/**
 * 处理WAR文件工具类。可压缩或解压缩WAR文件。
 *
 * @author souvc.com
 * @date 2016-03-01
 */
public class WarUtils {
  /**
   * 解压压缩包
   * @param warPath
   * @param unzipPath
   */
  public static void unzip(String warPath, String unzipPath) {
    File warFile = new File(warPath);
    try {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(warFile));
      ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.JAR,
          bufferedInputStream);

      JarArchiveEntry entry = null;
      while ((entry = (JarArchiveEntry) in.getNextEntry()) != null) {
        if (entry.isDirectory()) {
          new File(unzipPath, entry.getName()).mkdir();
        } else {
          OutputStream out = FileUtils.openOutputStream(new File(unzipPath, entry.getName()));
          IOUtils.copy(in, out);
          out.close();
        }
      }
      in.close();
    } catch (FileNotFoundException e) {
      System.err.println("未找到war文件");
    } catch (ArchiveException e) {
      System.err.println("不支持的压缩格式");
    } catch (IOException e) {
      System.err.println("文件写入发生错误");
    }
  }
  /**
   * 打成ZIP,WAR包
   * @param destFile
   * @param zipDir
   */
  public static void zip(String destFile, String zipDir) {
    File outFile = new File(destFile);
    try {
      outFile.createNewFile();
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFile));
      ArchiveOutputStream out = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.JAR,
          bufferedOutputStream);

      if (zipDir.charAt(zipDir.length() - 1) != '/') {
        zipDir += '/';
      }

      Iterator<File> files = FileUtils.iterateFiles(new File(zipDir), null, true);
      while (files.hasNext()) {
        File file = files.next();
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getPath().replace(
            zipDir.replace("/", "\\"), ""));
        out.putArchiveEntry(zipArchiveEntry);
        IOUtils.copy(new FileInputStream(file), out);
        out.closeArchiveEntry();
      }
      out.finish();
      out.close();
    } catch (IOException e) {
      System.err.println("创建文件失败");
    } catch (ArchiveException e) {
      System.err.println("不支持的压缩格式");
    }
  }
  /**
   * 移动WAR包文件到指定目录
   * @param removeFile
   * @param deskDir
   */
  public static void removdFile(String removeFile,String deskDir){
    try {
      File removefile = new File(removeFile);
      if (removefile.renameTo(new File(deskDir + removefile.getName()))) {
        System.out.println("File is moved successful!");
      } else {
        System.out.println("File is failed to move!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * 复制一个目录及其子目录、文件到另外一个目录
   * @param src
   * @param dest
   * @throws IOException
   */
  public static void copyFolder(File src, File dest) throws IOException {
    if (src.isDirectory()) {
      if (!dest.exists()) {
        dest.mkdir();
      }
      String files[] = src.list();
      for (String file : files) {
        File srcFile = new File(src, file);
        File destFile = new File(dest, file);
        // 递归复制
        copyFolder(srcFile, destFile);
      }
    } else {
      InputStream in = new FileInputStream(src);
      OutputStream out = new FileOutputStream(dest);

      byte[] buffer = new byte[1024];

      int length;

      while ((length = in.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
      in.close();
      out.close();
    }
  }
  /**
   * 压缩整个文件夹中的所有文件，生成指定名称的zip压缩包
   * @param filepath 文件所在目录
   * @param zippath 压缩后zip文件名称
   * @param dirFlag zip文件中第一层是否包含一级目录，true包含；false没有
   * 2016年3月2日
   */
  public static void zipMultiFile(String filepath ,String zippath, boolean dirFlag) {
    try {
      File file = new File(filepath);// 要被压缩的文件夹
      File zipFile = new File(zippath);
      ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
      if(file.isDirectory()){
        File[] files = file.listFiles();
        for(File fileSec:files){
          if(dirFlag){
            recursionZip(zipOut, fileSec, file.getName() + File.separator);
          }else{
            recursionZip(zipOut, fileSec, "");
          }
        }
      }
      zipOut.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception{
    if(file.isDirectory()){
      File[] files = file.listFiles();
      for(File fileSec:files){
        recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
      }
    }else{
      byte[] buf = new byte[1024];
      InputStream input = new FileInputStream(file);
      zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
      int len;
      while((len = input.read(buf)) != -1){
        zipOut.write(buf, 0, len);
      }
      input.close();
    }
  }
  public static void main(String[] args) {
    String path = "F:\\testZip\\activity.war";
    String warpath="F:\\testZip\\war\\activity";


    //1、复制文件到WAR包
    	/*File afile = new File(path);
    	File bfile = new File(warpath);
    	try {
			copyFolder(afile,bfile);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

    //2、打成WAR包
    //zip(path,warpath);


    //3、移动WAR包到JBOSS
    // String removeFile,String deskDir
    zipMultiFile("F:\\testZip\\activity","F:\\testZip\\activity.war",false);
    //removdFile("F:\\testZip\\activity.war","F:\\testZip\\war");
  }
}
