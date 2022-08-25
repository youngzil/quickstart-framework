package org.quickstart.linux.scp.example.basic.scp;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class SCPToExample {

    public static final String SFTP_REQ_HOST = "172.16.48.179"; // ip
    public static final String SFTP_REQ_USERNAME = "appweb"; // username
    public static final String password = null; // username
    public static final String keyFilePath = "/Users/lengfeng/.ssh/id_rsa.pub"; // username

    public static void main(String[] args) throws JSchException {
        final JSch jsch = new JSch();
        // 获取session
        Session session = jsch.getSession(SFTP_REQ_USERNAME, SFTP_REQ_HOST, 22);
        jsch.addIdentity(keyFilePath);

        if (StringUtils.isNotBlank(password)) {
            session.setPassword(password);
        }
        final Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        session.connect(60000);

        String command = "scp /Users/lengfeng/topics.log appweb@172.16.48.179:/data/program";

        Channel channel = session.openChannel("exec");
        channel.connect(10000);
        ((ChannelExec)channel).setCommand(command);

        // channel.disconnect();
        // session.disconnect();

    }
}
