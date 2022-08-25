package org.quickstart.linux.scp.example;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * ssh工具类
 *
 * @author duhai
 * @date 2021年3月15日
 */
public class FTPUtil {

    private final static Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    public static final String SFTP_REQ_HOST = "172.16.48.179"; // ip
    public static final String SFTP_REQ_USERNAME = "appweb"; // username
    // public static final String SFTP_REQ_PASSWORD = "123456"; // password
    public static final String SFTP_REQ_PASSWORD = null; // password
    public static final int SFTP_DEFAULT_PORT = 22; // 端口

    /**
     * 参考实例
     *
     * @param args
     */
    public static void main(final String[] args) {
        File file = new File("/Users/lengfeng/topics.log");
        FTPUtil ft = new FTPUtil();
        final Session session = ft.getSession(SFTP_REQ_HOST, SFTP_DEFAULT_PORT, SFTP_REQ_USERNAME, SFTP_REQ_PASSWORD);
        final Channel channel = ft.getChannel(session);
        final ChannelSftp sftp = (ChannelSftp)channel;
        // 上传文件
        String upload = ft.uploadFile(sftp, "/data/program", file);
        System.out.println(upload);
        // 关闭连接
        ft.closeAll(sftp, channel, session);
    }

    /**
     * 获取session
     *
     * @param host
     * @param port
     * @param username
     * @param password
     * @return
     */
    public Session getSession(final String host, final int port, final String username, final String password) {
        Session session = null;
        try {
            final JSch jsch = new JSch();
            // 获取session
            // jsch.getSession(username, host, port);
            session = jsch.getSession(username, host, port);
            if(StringUtils.isNotBlank(password)){
                session.setPassword(password);
            }
            final Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect(60000);
            logger.info("Session connected!");
        } catch (JSchException e) {
            logger.info("get Channel failed!", e);
        }
        return session;
    }

    /**
     * 获取sftp通道
     *
     * @param session
     * @return
     */
    public Channel getChannel(final Session session) {
        Channel channel = null;
        try {
            channel = session.openChannel("sftp");
            channel.connect(10000);
            logger.info("get sftp Channel success!");
        } catch (JSchException e) {
            logger.info("get sftp Channel fail!", e);
        }
        return channel;
    }

    /**
     * @param sftp
     * @param dir 上传目录
     * @param file 上传文件
     * @return
     */
    public String uploadFile(final ChannelSftp sftp, final String dir, final File file) {
        String result = "";
        try {
            cdDir(dir, sftp);
            if (file != null) {
                sftp.put(new FileInputStream(file), file.getName());
                result = "上传成功！";
            } else {
                result = "文件为空！不能上传！";
            }
            logger.info(result);
        } catch (Exception e) {
            logger.info("上传失败！", e);
            result = "上传失败！";
        }
        return result;
    }

    /**
     * 创建一个文件目录
     *
     * @param createpath
     * @param sftp
     * @throws SystemException
     */
    public boolean cdDir(final String createpath, final ChannelSftp sftp) throws SystemException {
        try {
            // 进入初始目录
            sftp.cd("/");
            // 切分目录
            String pathArry[] = createpath.split("/");
            // 是否是目录或文件
            boolean isFile = true;
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                try {
                    sftp.cd(path);
                    isFile = true;
                } catch (SftpException e) {
                    isFile = false;
                }
                if (isFile == false) {
                    try {
                        sftp.mkdir(path);
                        sftp.cd(path);
                    } catch (SftpException e) {
                        e.printStackTrace();
                    }
                }
            }
            logger.info("##### 进入目录:" + sftp.pwd());
        } catch (SftpException e) {
            logger.error("##### create dir error !!! createpath:" + createpath + "##### ", e);
            return false;
        }
        return true;
    }

    /**
     * 下载文件
     *
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     * @param sftp
     */
    public String download(final String directory, final String downloadFile, final String saveFile, final ChannelSftp sftp) {
        String result = "";
        try {
            sftp.cd(directory);
            sftp.get(downloadFile, saveFile);
            result = "下载成功！";
        } catch (Exception e) {
            result = "下载失败！";
            logger.info("下载失败！", e);
        }
        return result;
    }

    /**
     * 删除文件
     *
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public String delete(final String directory, final String deleteFile, final ChannelSftp sftp) {
        String result = "";
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
            result = "删除成功！";
        } catch (Exception e) {
            result = "删除失败！";
            logger.info("删除失败！", e);
        }
        return result;
    }

    /**
     * 关闭连接
     *
     * @param sftp
     * @param channel
     * @param session
     */
    public void closeAll(final ChannelSftp sftp, final Channel channel, final Session session) {
        try {
            closeChannel(sftp);
            closeChannel(channel);
            closeSession(session);
        } catch (Exception e) {
            logger.info("closeAll", e);
        }
    }

    private void closeChannel(final Channel channel) {
        if (channel != null) {
            if (channel.isConnected()) {
                channel.disconnect();
            }
        }
    }

    private void closeSession(final Session session) {
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

}
