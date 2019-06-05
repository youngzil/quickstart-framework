/**
 * 项目名称：quickstart-javase 
 * 文件名：ScpTest.java
 * 版本信息：
 * 日期：2017年8月5日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.ssh.ganymed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * ScpTest
 * 
 * @author：youngzil@163.com
 * @2017年8月5日 下午11:58:07
 * @version 2.0
 */
public class ScpTest {

    public static void main(String[] args) throws Exception {

        String ip = "10.21.20.154";
        int port = 22;
        String username = "msgframe";
        String password = "msgframe";
        String remoteDir = "/home/msgframe/100";
        String remoteFile = "/home/msgframe/100/100.jpg";
        String localFile = "/Users/yangzl/100.jpg";
        String localDir = "/Users/yangzl/101";

        try {
            Connection conn = new Connection(ip, port);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(username, password);
            if (isAuthenticated == false) {
                throw new Exception("SSH authentication failed with [ userName: " + username + ", password: " + password + "]");
            }

            System.out.println("isAuthed====" + isAuthenticated);

            SCPClient scpClient = conn.createSCPClient();
            scpClient.put(localFile, remoteDir); // 从本地复制文件到远程目录
            scpClient.get(remoteFile, localDir); // 从远程获取文件

            // 重命名
            // ch.ethz.ssh2.Session sess = conn.openSession();
            // String tmpPathName = remoteTargetDirectory +File.separator+ remoteFileName;
            // String newPathName = tmpPathName.substring(0, tmpPathName.lastIndexOf("."));
            // sess.execCommand("mv " + remoteFileName + " " + newPathName);//重命名回来

            /**
             * SFTPv3Client sftpClient = new SFTPv3Client(conn); sftpClient.mkdir(remoteDir, 6); //远程新建目录 //sftpClient.rmdir(remoteDir); //远程删除目录 sftpClient.createFile(remoteFile); //远程新建文件
             * sftpClient.openFileRW(remoteFile); //远程打开文件，可进行读写
             */

            Session session = conn.openSession();
            session.execCommand("uname -a && date && uptime && who"); // 远程执行命令

            // 显示执行命令后的信息
            System.out.println("Here is some information about the remote host:");
            InputStream stdout = new StreamGobbler(session.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            }

            /* Show exit status, if available (otherwise "null") */

            System.out.println("ExitCode: " + session.getExitStatus());

            session.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
