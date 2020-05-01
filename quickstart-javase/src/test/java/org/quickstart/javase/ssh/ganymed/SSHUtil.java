package org.quickstart.javase.ssh.ganymed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.quickstart.javase.ssh.ganymed.SSHTemplate.Result;
import org.quickstart.javase.ssh.ganymed.SSHTemplate.SSHCallback;
import org.quickstart.javase.ssh.ganymed.SSHTemplate.SSHSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yijunzhang on 14-6-20.
 */
public class SSHUtil {

    private static final Logger logger = LoggerFactory.getLogger(SSHUtil.class);

    private final static String COMMAND_TOP = "top -b -n 1 | head -5";
    private final static String COMMAND_DF_LH = "df -lh";
    private final static String LOAD_AVERAGE_STRING = "load average: ";
    private final static String COMMAND_MEM = "cat /proc/meminfo | grep -E -w 'MemTotal|MemFree|Buffers|Cached'";
    private final static String MEM_TOTAL = "MemTotal";
    private final static String MEM_FREE = "MemFree";
    private final static String MEM_BUFFERS = "Buffers";
    private final static String MEM_CACHED = "Cached";

    // 使用 @SSHTemplate 重构SSHUtil
    private final static SSHTemplate sshTemplate = new SSHTemplate();

    /**
     * SSH 方式登录远程主机，执行命令,方法内部会关闭所有资源，调用方无须关心。
     *
     * @param ip 主机ip
     * @param username 用户名
     * @param password 密码
     * @param command 要执行的命令
     */
    public static String execute(String ip, int port, String username, String password, final String command) throws Exception {

        if (StringUtils.isBlank(command)) {
            return "";
        }
        port = port > 0 ? port : ConstUtils.SSH_PORT_DEFAULT;

        Result rst = sshTemplate.execute(ip, port, username, password, new SSHCallback() {
            public Result call(SSHSession session) {
                return session.executeCommand(command);
            }
        });

        return rst.getResult();
        /* if(rst.isSuccess()) {
        	return rst.getResult();
        }
        return "execute false";*/
    }

    /**
     * @param ip
     * @param port
     * @param username
     * @param password
     * @param localPath
     * @param remoteDir
     * @return
     * @throws Exception
     */
    public static boolean scpFileToRemote(String ip, int port, String username, String password, final String localPath, final String remoteDir) throws Exception {
        Result rst = sshTemplate.execute(ip, port, username, password, new SSHCallback() {
            public Result call(SSHSession session) {
                return session.scpToDir(localPath, remoteDir, "0644");
            }
        });
        if (rst.isSuccess()) {
            return true;
        }
        if (rst.getExcetion() != null) {
            throw new Exception(rst.getExcetion());
        }
        return false;
    }

    /**
     * 重载，使用默认端口、用户名和密码
     *
     * @param ip
     * @param localPath
     * @param remoteDir
     * @return
     * @throws Exception
     */
    public static boolean scpFileToRemote(String ip, String localPath, String remoteDir) throws Exception {
        int sshPort = SSHUtil.getSshPort(ip);
        return scpFileToRemote(ip, sshPort, ConstUtils.USERNAME, ConstUtils.PASSWORD, localPath, remoteDir);
    }

    /**
     * 重载，使用默认端口、用户名和密码
     *
     * @param ip
     * @param cmd
     * @return
     * @throws Exception
     */
    public static String execute(String ip, String cmd) throws Exception {
        int sshPort = SSHUtil.getSshPort(ip);
        return execute(ip, sshPort, ConstUtils.USERNAME, ConstUtils.PASSWORD, cmd);
    }

    /**
     * 查看机器ip上的端口port是否已被占用；
     *
     * @param ip 机器ip
     * @param port 要检查的端口
     * @return 如果被占用返回true，否则返回false；
     * @throws Exception
     */
    public static boolean isPortUsed(String ip, int port) throws Exception {
        /**
         * 执行ps命令，查看端口，以确认刚才执行的shell命令是否成功，返回一般是这样的： root 12510 12368 0 14:34 pts/0 00:00:00 redis-server *:6379
         */
        String psCmd = "/bin/ps -ef | grep %s | grep -v grep";
        psCmd = String.format(psCmd, port);
        String psResponse = execute(ip, psCmd);
        boolean isUsed = false;

        if (StringUtils.isNotBlank(psResponse)) {
            String[] resultArr = psResponse.split(System.lineSeparator());
            for (String resultLine : resultArr) {
                if (resultLine.contains(String.valueOf(port))) {
                    isUsed = true;
                    break;
                }
            }
        }
        return isUsed;
    }

    /**
     * 通过ip来判断ssh端口
     *
     * @param ip
     * @return
     */
    public static int getSshPort(String ip) {
        /**
         * 如果ssh默认端口不是22,请自行实现该逻辑
         */
        return ConstUtils.SSH_PORT_DEFAULT;
    }

    /**
     * 匹配字符串中的数字
     * 
     * @param content
     * @return
     */
    private static String matchMemLineNumber(String content) {
        String result = "";
        if (content == null || "".equals(content.trim())) {
            return result;
        }
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    /**
     * 从top的cpuLine解析出us
     * 
     * @param cpuLine
     * @return
     */
    public static double getUsCpu(String cpuLine) {
        if (cpuLine == null || "".equals(cpuLine.trim())) {
            return 0;
        }
        String[] items = cpuLine.split(",");
        if (items.length < 1) {
            return 0;
        }
        String usCpuStr = items[0];
        return NumberUtils.toDouble(matchCpuLine(usCpuStr));
    }

    private static String matchCpuLine(String content) {
        String result = "";
        if (content == null || "".equals(content.trim())) {
            return result;
        }
        Pattern pattern = Pattern.compile("(\\d+).(\\d+)");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            result = matcher.group();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String dd = SSHUtil.execute("10.21.20.154", 22, "msgframe", "msgframe", "mkdir haha");
        String dd2 = SSHUtil.execute("10.21.20.154", 22, "msgframe", "msgframe", "tar -xzvf apache-activemq-5.14.5-bin.tar.gz -C ./haha");
        System.out.println(dd);

        // while(true){
        //
        // }
    }

}
