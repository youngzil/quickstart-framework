/**
 * 项目名称：quickstart-javase 
 * 文件名：TestJsch.java
 * 版本信息：
 * 日期：2017年8月5日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.ssh.jsch;

import org.quickstart.javase.ssh.ganymed.SSHUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * TestJsch
 * 
 * @author：yangzl@asiainfo.com
 * @2017年8月5日 下午11:46:18
 * @version 2.0
 */

public class TestJsch {
    public static final UserInfo defaultUserInfo = new UserInfo() {

        public String getPassphrase() {
            return null;
        }

        public String getPassword() {
            return null;
        }

        public boolean promptPassword(String arg0) {
            return false;
        }

        public boolean promptPassphrase(String arg0) {
            return false;
        }

        public boolean promptYesNo(String arg0) {
            return true;
        }

        public void showMessage(String arg0) {}
    };

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String hostname = "10.21.20.154";
        String username = "msgframe";
        String password = "msgframe";
        String remoteFile = "/home/msgframe/100.jpg";
        String localFile = "/Users/yangzl/100.jpg";
        String localFile2 = "/Users/yangzl/101.jpg";

        JSch jsch = new JSch();

        Session session = jsch.getSession(username, hostname, 22);
        session.setPassword(password);
        session.setUserInfo(defaultUserInfo);
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp c = (ChannelSftp) channel;

        c.put(localFile, remoteFile);

        c.get(remoteFile, localFile2);

        session.disconnect();
    }
}
