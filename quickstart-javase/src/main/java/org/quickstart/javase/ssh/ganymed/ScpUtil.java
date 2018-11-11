/**
 * 项目名称：quickstart-javase 
 * 文件名：SSH2Util.java
 * 版本信息：
 * 日期：2017年8月6日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.ssh.ganymed;

/**
 * SSH2Util 
 *  
 * @author：youngzil@163.com
 * @2017年8月6日 上午12:22:08 
 * @version 2.0
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 远程操作linux服务器，包括连接，删除文件或者目录，创建文件或目录，修改文件等
 * 
 * @author Administrator
 * 
 */
public class ScpUtil {
    // 远端服务器IP地址
    private String[] serverIp;

    // 远端服务器登录用户名
    private String[] userName;

    // 远端服务器登录密码
    private String[] password;

    // 远端服务器的基准目录
    private String[] baseDir;

    // 连接对象
    private Connection[] connection;

    /**
     * 默认构造函数，初始化服务IP地址，用户名，密码 基准目录等实例变量
     */
    public ScpUtil() {
        List<String[]> retList = FileUtils.getNginxConfig();
        this.serverIp = new String[retList.size()];
        this.userName = new String[retList.size()];
        this.password = new String[retList.size()];
        this.baseDir = new String[retList.size()];
        this.connection = new Connection[retList.size()];
        for (int i = 0; i < retList.size(); i++) {
            String[] array = retList.get(i);
            this.serverIp[i] = array[2];
            this.userName[i] = array[0];
            this.password[i] = array[1];
            this.baseDir[i] = array[3];
        }
    }

    /**
     * 根据传入的参数初始化服务IP地址，用户名，密码基准目录等实例变量
     * 
     * @param serverIp
     * @param userName
     * @param password
     * @param baseDir
     */
    public ScpUtil(String[] serverIp, String[] userName, String[] password, String[] baseDir) {
        this.serverIp = serverIp;
        this.userName = userName;
        this.password = password;
        this.baseDir = baseDir;
        this.connection = new Connection[serverIp.length];
    }

    /**
     * 根据传入的参数初始化服务IP地址，用户名，密码基准目录等实例变量
     * 
     * @param serverIp
     * @param userName
     * @param password
     * @param baseDir
     */
    public ScpUtil(String serverIp, String userName, String password, String baseDir) {
        this.serverIp = new String[1];
        this.userName = new String[1];
        this.password = new String[1];
        this.baseDir = new String[1];
        this.connection = new Connection[1];
        this.serverIp[0] = serverIp;
        this.userName[0] = userName;
        this.password[0] = password;
        this.baseDir[0] = baseDir;
    }

    /**
     * 连接和认证远程Linux主机
     * 
     * @return boolean
     */
    public boolean connectAndAuth() {
        boolean isConnAndAuth = false;
        if (null != serverIp) {
            for (int i = 0; i < serverIp.length; i++) {
                connection[i] = new Connection(serverIp[i]);
                try {
                    connection[i].connect();
                    isConnAndAuth = connection[i].authenticateWithPassword(userName[i], password[i]);
                } catch (Exception e) {
                    System.out.println("无法连接和认证以下主机,IP:" + serverIp[i] + ",用户名:userName[i]，请检查IP，用户名或者密码是否正确!");
                    e.printStackTrace();
                    // 如果存在主机连接或者认证失败，那么设置连接是否的标识为false
                    isConnAndAuth = false;
                }
            }
        }
        return isConnAndAuth;
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的remoteTargetDirectory目录下
     * 
     * @param localFile
     * @param remoteTargetDirectory
     */
    public void uploadFile(String localFile, String remoteTargetDirectory) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SCPClient scpClient = conn.createSCPClient();
                    scpClient.put(localFile, remoteTargetDirectory, "0644");
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的remoteTargetDirectory目录下
     * 
     * @param localFiles
     * @param remoteTargetDirectory
     */
    public void uploadFile(String[] localFiles, String remoteTargetDirectory) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SCPClient scpClient = conn.createSCPClient();
                    for (String localFile : localFiles) {
                        scpClient.put(localFile, remoteTargetDirectory, "0644");
                    }
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的remoteTargetDirectory目录下
     * 
     * @param localFileList
     * @param remoteTargetDirectory
     */
    public void uploadFile(List<String> localFileList, String remoteTargetDirectory) {
        uploadFile(localFileList.toArray(new String[] {}), remoteTargetDirectory);
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的配置的目录下
     * 
     * @param localFile
     */
    public void uploadFile(String localFile) {
        if (connectAndAuth()) {
            for (int i = 0; i < connection.length; i++) {
                try {
                    SCPClient scpClient = connection[i].createSCPClient();
                    scpClient.put(localFile, baseDir[i], "0644");
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的配置的目录下
     * 
     * @param localFiles
     */
    public void uploadFile(String[] localFiles) {
        if (connectAndAuth()) {
            for (int i = 0; i < connection.length; i++) {
                try {
                    SCPClient scpClient = connection[i].createSCPClient();
                    for (String localFile : localFiles) {
                        scpClient.put(localFile, baseDir[i], "0644");
                    }
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的配置的目录下
     * 
     * @param localFileList
     */
    public void uploadFile(List<String> localFileList) {
        uploadFile(localFileList.toArray(new String[] {}));
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的remoteTargetDirectory目录下
     * 
     * @param localFile
     * @param remoteTargetDirectory
     * @param mode
     */
    public void uploadFile(String localFile, String remoteTargetDirectory, String mode) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SCPClient scpClient = conn.createSCPClient();
                    scpClient.put(localFile, remoteTargetDirectory, mode);
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的remoteTargetDirectory目录下
     * 
     * @param localFiles
     * @param remoteTargetDirectory
     * @param mode
     */
    public void uploadFile(String[] localFiles, String remoteTargetDirectory, String mode) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SCPClient scpClient = conn.createSCPClient();
                    for (String localFile : localFiles) {
                        scpClient.put(localFile, remoteTargetDirectory, mode);
                    }
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的remoteTargetDirectory目录下
     * 
     * @param localFileList
     * @param remoteTargetDirectory
     * @param mode
     */
    public void uploadFile(List<String> localFileList, String remoteTargetDirectory, String mode) {
        uploadFile(localFileList.toArray(new String[] {}), remoteTargetDirectory, mode);
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的配置的目录下
     * 
     * @param localFile
     */
    public void uploadFileUseMode(String localFile, String mode) {
        if (connectAndAuth()) {
            for (int i = 0; i < connection.length; i++) {
                try {
                    SCPClient scpClient = connection[i].createSCPClient();
                    scpClient.put(localFile, baseDir[i], mode);
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的配置的目录下
     * 
     * @param localFiles
     */
    public void uploadFileUseMode(String[] localFiles, String mode) {
        if (connectAndAuth()) {
            for (int i = 0; i < connection.length; i++) {
                try {
                    SCPClient scpClient = connection[i].createSCPClient();
                    for (String localFile : localFiles) {
                        scpClient.put(localFile, baseDir[i], mode);
                    }
                } catch (Exception e) {
                    System.out.println("获取ssh上传客户端失败!");
                    e.printStackTrace();
                }
            }
            closeConnection();
        }
    }

    /**
     * 上传本地文件到远程服务器端，即将本地的文件localFile上传到远程Linux服务器中的配置的目录下
     * 
     * @param localFileList
     */
    public void uploadFileUseMode(List<String> localFileList, String mode) {
        uploadFileUseMode(localFileList.toArray(new String[] {}), mode);
    }

    /**
     * 从远程服务器端下载文件到本地指定的目录中
     * 
     * @param remoteFile
     * @param localTargetDirectory
     */
    public void downloadFile(String remoteFile, String localTargetDirectory) {
        if (connectAndAuth()) {
            try {
                SCPClient scpClient = connection[0].createSCPClient();
                scpClient.get(remoteFile, localTargetDirectory);
            } catch (Exception e) {
                System.out.println("获取ssh下载客户端失败!");
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    /**
     * 从远程服务器端下载多个文件到本地指定的目录中
     * 
     * @param remoteFiles
     * @param localTargetDirectory
     */
    public void downloadFile(String[] remoteFiles, String localTargetDirectory) {
        if (connectAndAuth()) {
            try {
                SCPClient scpClient = connection[0].createSCPClient();
                scpClient.get(remoteFiles, localTargetDirectory);
            } catch (Exception e) {
                System.out.println("获取ssh下载客户端失败!");
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    /**
     * 从远程服务器端下载文件一个输出流中
     * 
     * @param remoteFile
     * @param target
     */
    public void downloadFile(String remoteFile, OutputStream target) {
        if (connectAndAuth()) {
            try {
                SCPClient scpClient = connection[0].createSCPClient();
                scpClient.get(remoteFile, target);
            } catch (Exception e) {
                System.out.println("获取ssh下载客户端失败!");
                e.printStackTrace();
            }
            closeConnection();
        }
    }

    /**
     * 在远端linux上创建文件夹
     * 
     * @param dirName 文件夹名称
     * @param posixPermissions 目录或者文件夹的权限
     */
    public void mkDir(String dirName, int posixPermissions) {
        if (connectAndAuth()) {
            System.out.println("************开始创建目录:" + dirName + "************");
            for (Connection conn : connection) {
                try {
                    SFTPv3Client sftpClient = new SFTPv3Client(conn);
                    sftpClient.mkdir(dirName, posixPermissions);
                } catch (Exception e) {
                    System.out.println("************目录:" + dirName + "已经存在!************");
                    e.printStackTrace();
                }

            }
            closeConnection();
        }
    }

    /**
     * 删除远端Linux服务器上的文件
     * 
     * @param filePath
     */
    public void rmFile(String filePath) {
        if (connectAndAuth()) {
            System.out.println("************开始删除文件:" + filePath + "************");
            for (Connection conn : connection) {
                try {
                    SFTPv3Client sftpClient = new SFTPv3Client(conn);
                    sftpClient.rm(filePath);
                } catch (Exception e) {
                    System.out.println("************文件:" + filePath + "不存在!************");
                    e.printStackTrace();
                }

            }
            closeConnection();
        }
    }

    /**
     * 删除远端Linux服务器上的多个文件
     * 
     * @param filePaths
     */
    public void rmFile(String[] filePaths) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SFTPv3Client sftpClient = new SFTPv3Client(conn);
                    for (String filePath : filePaths) {
                        sftpClient.rm(filePath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            closeConnection();
        }
    }

    /**
     * 删除远端Linux服务器上的一个空文件夹
     * 
     * @param dirName
     */
    public void rmEmptyDir(String dirName) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SFTPv3Client sftpClient = new SFTPv3Client(conn);
                    sftpClient.rmdir(dirName);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            closeConnection();
        }
    }

    /**
     * 在远程Linux服务器端移动文件或者文件夹到新的位置
     * 
     * @param oldPath
     * @param newPath
     */
    public void moveFileOrDir(String oldPath, String newPath) {
        if (connectAndAuth()) {
            for (Connection conn : connection) {
                try {
                    SFTPv3Client sftpClient = new SFTPv3Client(conn);
                    sftpClient.mv(oldPath, newPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            closeConnection();
        }
    }

    /**
     * 在远程Linux服务器上执行命令
     * 
     * @param cmd
     * @param isQuery
     */
    public String execCommand(String cmd, boolean isQuery) {
        StringBuffer sb = new StringBuffer();
        if (connectAndAuth()) {
            Session session = null;
            if (isQuery) {
                try {
                    session = connection[0].openSession();
                    InputStream stdout = new StreamGobbler(session.getStdout());
                    BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                    String content = null;
                    while ((content = br.readLine()) != null) {
                        sb.append(content);
                    }
                    // 获得推出状态
                    System.out.println("ExitCode: " + session.getExitStatus());
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                for (Connection conn : connection) {
                    try {
                        session = conn.openSession();
                        session.execCommand(cmd);
                        session.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            closeConnection();
        }
        return sb.toString();
    }

    /**
     * 关闭连接
     * 
     */
    public void closeConnection() {
        if (null != connection && connection.length > 0) {
            for (Connection conn : connection) {
                conn.close();
            }
        }
    }
}
