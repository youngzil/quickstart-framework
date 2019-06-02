package org.quickstart.linux.sftp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SFTP工具类
 * 包含两个方法：
 * 一个创建一个sftp通道对象，并返回这个对象，通过这 个对象可以直接发送文件。
 * 另一个是用于关闭回话和通道的。
 */
public class SFTPUtils2 {
  static private final Logger log = LoggerFactory.getLogger(SFTPUtils2.class);

  static private Session session = null;
  static private Channel channel = null;
  static private int timeout = 60000; //超时数,一分钟

  /**
   * 传入一个通道对象
   * @param username 远程要连接的服务器的用户名
   * @param password 远程要连接的服务器的密码
   * @param ip 远程服务器ip
   * @param port 远程服务器的ssh服务端口
   * @return ChannelSftp返回指向这个通道指定的地址的channel实例
   * @throws JSchException
   */
  public static ChannelSftp getChannel(String username, String password, String ip, String port) throws JSchException {
    JSch jsch = new JSch(); // 创建JSch对象
    // 根据用户名，主机ip，端口获取一个Session对象
    session = jsch.getSession(username, ip, Integer.valueOf(22));
    log.info("Session created...");
    if (password != null) {
      session.setPassword(password); // 设置密码
    }
    Properties config = new Properties();
    config.put("StrictHostKeyChecking", "no");
    session.setConfig(config); // 为Session对象设置properties
    session.setTimeout(timeout); // 设置timeout时间
    session.connect(); // 通过Session建立链接
    log.info("Session connected, Opening Channel...");
    channel = session.openChannel("sftp"); // 打开SFTP通道
    channel.connect(); // 建立SFTP通道的连接
    log.info("Connected successfully to ip :{}, ftpUsername is :{}, return :{}",
        ip,username, channel);
    return (ChannelSftp) channel;
  }

  /**
   * 关闭channel和session
   * @throws Exception
   */
  public static void closeChannel() throws Exception {
    if (channel != null) {
      channel.disconnect();
    }
    if (session != null) {
      session.disconnect();
    }
  }



  /**
   * 文件推送的测试方法。
   * destDirPath 远程服务器要保存的文件夹路径
   * file  本地要推送的文件对象
   * username 远程服务器的用户名
   * password  远程服务器的密码
   * ip  远程服务器ip
   * port 远程服务器ssh服务端口
   */
  public void testSftp() throws SftpException, JSchException, FileNotFoundException {
    // 假设参数值
    String dstDirPath = "E:\\target";
    String username = "admin";
    String password = "admin";
    String ip = "127.0.0.1";
    String port = 21 + "";
    File file = new File("");

    ChannelSftp channelSftp = null;
    String dstFilePath; // 目标文件名(带路径)，如： D:\\file\\file.doc,这个路径应该是远程目标服务器下要保存的路径
    try {
      // 一、 获取channelSftp对象
      channelSftp = SFTPUtils2.getChannel(username, password, ip, port);
      // 二、 判断远程路径dstDirPath是否存在(通道配置的路径)
      try {
        Vector dir = channelSftp.ls(dstDirPath);
        if (dir == null) { // 如果路径不存在，则创建
          channelSftp.mkdir(dstDirPath);
        }
      } catch (SftpException e) { // 如果dstDirPath不存在，则会报错，此时捕获异常并创建dstDirPath路径
        channelSftp.mkdir(dstDirPath); // 此时创建路o如果再报错，即创建失败，则抛出异常
        e.printStackTrace();
      }
      // 三、 推送文件
      try {
        log.info("send the file : {}", file.getName());
        dstFilePath = dstDirPath + "\\" + file.getName();
        log.info("the file all path is :{}", dstFilePath);
        // 推送: dstFilePath——传送过去的文件路径(全路径),采用默认的覆盖式推送
        channelSftp.put(new FileInputStream(file), dstFilePath); // jsch触发推送操作的方法
      }  catch (SftpException e) {
        log.debug("An error occurred during sftp push, send data fail, the target path is :{}", dstDirPath);
        if (log.isDebugEnabled()) {
          e.printStackTrace();
        }
      }
    }  finally {
      // 处理后事
      if (channelSftp != null)
        channelSftp.quit();
      try {
        SFTPUtils2.closeChannel();
      } catch (Exception e) {
        if (log.isDebugEnabled())
          e.printStackTrace();
      }
    }
  }


}
