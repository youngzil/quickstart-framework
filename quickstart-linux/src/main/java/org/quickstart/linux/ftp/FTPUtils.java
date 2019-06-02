package org.quickstart.linux.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.MalformedURLException;

/**
 * @Auther: hrabbit
 * @Date: 2018-04-21 下午12:35
 * @Description:
 */
public class FTPUtils {

  public static FTPClient ftpClient = null;

  /**
   * 初始化ftp服务器
   */
  public static void initFtpClient(String hostname,String username,String password,Integer port) {
    ftpClient = new FTPClient();
    ftpClient.setControlEncoding("utf-8");
    try {
      //连接ftp服务器
      ftpClient.connect(hostname, port);
      //登录ftp服务器
      ftpClient.login(username, password);
      //是否成功登录服务器
      int replyCode = ftpClient.getReplyCode();
      if(!FTPReply.isPositiveCompletion(replyCode)){
        System.out.println("ftp服务器登录成功");
      }
    }catch (MalformedURLException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 上传文件
   * @param pathname ftp服务保存地址
   * @param fileName 上传到ftp的文件名
   * @param inputStream 输入文件流
   * @return
   */
  public static boolean uploadFile(String hostname,String username,String password,Integer port, String pathname, String fileName,InputStream inputStream){
    boolean flag = false;
    try{
      System.out.println("开始上传文件");
      initFtpClient(hostname,username,password,port);
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
      ftpClient.enterLocalPassiveMode();
      CreateDirecroty(pathname);
      ftpClient.makeDirectory(pathname);
      ftpClient.setControlEncoding("utf-8");
      ftpClient.storeFile(fileName, inputStream);
      System.out.println("上传结束");
      inputStream.close();
      ftpClient.logout();
      flag = true;
      System.out.println("上传文件成功");

    }catch (Exception e) {
      System.out.println("上传文件失败");
      e.printStackTrace();
    }finally{
      if(ftpClient.isConnected()){
        try{
          ftpClient.disconnect();
        }catch(IOException e){
          e.printStackTrace();
        }
      }
      if(null != inputStream){
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return true;
  }
  //改变目录路径
  public static boolean changeWorkingDirectory(String directory) {
    boolean flag = true;
    try {
      flag = ftpClient.changeWorkingDirectory(directory);
      if (flag) {
        System.out.println("进入文件夹" + directory + " 成功！");
      } else {
        System.out.println("进入文件夹" + directory + " 失败！开始创建文件夹");
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return flag;
  }

  //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
  public static boolean CreateDirecroty(String remote) throws IOException {
    boolean success = true;
    String directory = remote + "/";
    // 如果远程目录不存在，则递归创建远程服务器目录
    if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory))) {
      int start = 0;
      int end = 0;
      if (directory.startsWith("/")) {
        start = 1;
      } else {
        start = 0;
      }
      end = directory.indexOf("/", start);
      String path = "";
      String paths = "";
      while (true) {
        String subDirectory = new String(remote.substring(start, end).getBytes("UTF-8"), "iso-8859-1");
        path = path + "/" + subDirectory;
        if (!existFile(path)) {
          if (makeDirectory(subDirectory)) {
            changeWorkingDirectory(subDirectory);
          } else {
            System.out.println("创建目录[" + subDirectory + "]失败");
            changeWorkingDirectory(subDirectory);
          }
        } else {
          changeWorkingDirectory(subDirectory);
        }

        paths = paths + "/" + subDirectory;
        start = end + 1;
        end = directory.indexOf("/", start);
        // 检查所有目录是否创建完毕
        if (end <= start) {
          break;
        }
      }
    }
    return success;
  }

  //判断ftp服务器文件是否存在
  public static boolean existFile(String path) throws IOException {
    boolean flag = false;
    FTPFile[] ftpFileArr = ftpClient.listFiles(path);
    if (ftpFileArr.length > 0) {
      flag = true;
    }
    return flag;
  }
  //创建目录
  public static boolean makeDirectory(String dir) {
    boolean flag = true;
    try {
      flag = ftpClient.makeDirectory(dir);
      if (flag) {
        System.out.println("创建文件夹" + dir + " 成功！");

      } else {
        System.out.println("创建文件夹" + dir + " 失败！");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }

  /** * 下载文件 *
   * @param pathname FTP服务器文件目录 *
   * @param filename 文件名称 *
   * @param localpath 下载后的文件路径 *
   * @return */
  public static  boolean downloadFile(String hostname,String username,String password,Integer port,String pathname, String filename, String localpath){
    boolean flag = false;
    OutputStream os=null;
    try {
      System.out.println("开始下载文件");
      initFtpClient(hostname,username,password,port);
      //切换FTP目录
      ftpClient.changeWorkingDirectory(pathname);
      FTPFile[] ftpFiles = ftpClient.listFiles();
      for(FTPFile file : ftpFiles){
        if(filename.equalsIgnoreCase(file.getName())){
          File localFile = new File(localpath + "/" + file.getName());
          os = new FileOutputStream(localFile);
          ftpClient.retrieveFile(file.getName(), os);
          os.close();
        }
      }
      ftpClient.logout();
      flag = true;
      System.out.println("下载文件成功");
    } catch (Exception e) {
      System.out.println("下载文件失败");
      e.printStackTrace();
    } finally{
      if(ftpClient.isConnected()){
        try{
          ftpClient.disconnect();
        }catch(IOException e){
          e.printStackTrace();
        }
      }
      if(null != os){
        try {
          os.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return flag;
  }

  /** * 删除文件 *
   * @param pathname FTP服务器保存目录 *
   * @param filename 要删除的文件名称 *
   * @return */
  public static boolean deleteFile(String hostname,String username,String password,Integer port,String pathname, String filename){
    boolean flag = false;
    try {
      System.out.println("开始删除文件");
      initFtpClient(hostname,username,password,port);
      //切换FTP目录
      ftpClient.changeWorkingDirectory(pathname);
      ftpClient.dele(filename);
      ftpClient.logout();
      flag = true;
      System.out.println("删除文件成功");
    } catch (Exception e) {
      System.out.println("删除文件失败");
      e.printStackTrace();
    } finally {
      if(ftpClient.isConnected()){
        try{
          ftpClient.disconnect();
        }catch(IOException e){
          e.printStackTrace();
        }
      }
    }
    return flag;
  }
}
