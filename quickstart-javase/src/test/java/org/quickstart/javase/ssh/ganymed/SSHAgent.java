/**
 * 项目名称：quickstart-javase 
 * 文件名：SSHAgent.java
 * 版本信息：
 * 日期：2017年8月5日
 * Copyright yangzl Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.ssh.ganymed;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 1.确保所连接linux机器安装ssh，并且服务打开; 2.密码登陆，需配置文件： ssh配置文件： /ect/ssh/sshd_config 配置项：PasswordAuthentication yes
 * 
 * 验证登陆成功否：ssh 127.0.0.1（/other）
 * 
 * @see http://www.ganymed.ethz.ch/ssh2/FAQ.html http://www.programcreek.com/java-api-examples/index.php?api=ch.ethz.ssh2.StreamGobbler http://www.javawebdevelop.com/3240343/
 *      http://www.programcreek.com/java-api-examples/index.php?api=ch.ethz.ssh2.SCPClient
 * @author doctor
 * 
 * @time 2015年8月5日
 */
public final class SSHAgent {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Connection connection;

    public void initSession(String hostName, String userName, String passwd) throws IOException {
        connection = new Connection(hostName);
        connection.connect();

        boolean authenticateWithPassword = connection.authenticateWithPassword(userName, passwd);
        if (!authenticateWithPassword) {
            throw new RuntimeException("Authentication failed. Please check hostName, userName and passwd");
        }
    }

    /**
     * Why can't I execute several commands in one single session?
     * 
     * If you use Session.execCommand(), then you indeed can only execute only one command per session. This is not a restriction of the library, but rather an enforcement by the underlying SSH-2
     * protocol (a Session object models the underlying SSH-2 session).
     * 
     * There are several solutions:
     * 
     * Simple: Execute several commands in one batch, e.g., something like Session.execCommand("echo Hello && echo again"). Simple: The intended way: simply open a new session for each command - once
     * you have opened a connection, you can ask for as many sessions as you want, they are only a "virtual" construct. Advanced: Don't use Session.execCommand(), but rather aquire a shell with
     * Session.startShell().
     * 
     * @param command
     * @return
     * @throws IOException
     */

    public String execCommand(String command) throws IOException {
        Session session = connection.openSession();
        // session.execCommand(command, StandardCharsets.UTF_8.toString());
        session.execCommand(command); // 远程执行命令
        InputStream streamGobbler = new StreamGobbler(session.getStdout());

        String result = IOUtils.toString(streamGobbler, StandardCharsets.UTF_8);

        session.waitForCondition(ChannelCondition.EXIT_SIGNAL, Long.MAX_VALUE);

        if (session.getExitStatus().intValue() == 0) {
            log.info("execCommand: {} success ", command);
        } else {
            log.error("execCommand : {} fail", command);
        }

        IOUtils.closeQuietly(streamGobbler);
        session.close();
        return result;
    }

    /**
     * 远程传输单个文件
     * 
     * @param localFile
     * @param remoteTargetDirectory
     * @throws IOException
     */

    public void transferFile(String localFile, String remoteTargetDirectory) throws IOException {
        File file = new File(localFile);
        if (file.isDirectory()) {
            throw new RuntimeException(localFile + "  is not a file");
        }
        String fileName = file.getName();
        execCommand("cd " + remoteTargetDirectory + ";rm " + fileName + "; touch " + fileName);

        SCPClient sCPClient = connection.createSCPClient();
        // SCPOutputStream scpOutputStream = sCPClient.put(fileName, file.length(), remoteTargetDirectory, "0744");
        // String content = IOUtils.toString(new FileInputStream(file));
        // scpOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
        // scpOutputStream.flush();
        // scpOutputStream.close();

        sCPClient.put(localFile, remoteTargetDirectory);

    }

    /**
     * 传输整个目录
     * 
     * @param localFile
     * @param remoteTargetDirectory
     * @throws IOException
     */
    public void transferDirectory(String localDirectory, String remoteTargetDirectory) throws IOException {
        File dir = new File(localDirectory);
        if (!dir.isDirectory()) {
            throw new RuntimeException(localDirectory + " is not directory");
        }

        String[] files = dir.list();
        for (String file : files) {
            if (file.startsWith(".")) {
                continue;
            }
            String fullName = localDirectory + "/" + file;
            if (new File(fullName).isDirectory()) {
                String rdir = remoteTargetDirectory + "/" + file;
                execCommand("mkdir -p " + remoteTargetDirectory + "/" + file);
                transferDirectory(fullName, rdir);
            } else {
                transferFile(fullName, remoteTargetDirectory);
            }
        }

    }

    public void close() {
        connection.close();
    }

    public static void main(String[] args) throws IOException {
        SSHAgent sshAgent = new SSHAgent();
        sshAgent.initSession("10.21.20.154", "msgtest", "msgtest");

        String execCommand = sshAgent.execCommand("pwd ; date");
        System.out.println("pwd ; date:" + execCommand);
        String execCommand2 = sshAgent.execCommand("who  ");
        System.out.println("who  :" + execCommand2);

        sshAgent.transferFile("/Users/yangzl/100.jpg", "/home/msgtest");
        // sshAgent.transferDirectory("/home/xx/Documents", "/home/xx/book");

        // 执行bash脚本
        // System.out.println(sshAgent.execCommand("cd /home/xx/book; ./test.sh"));
        sshAgent.close();
    }
}
