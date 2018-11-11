/**
 * 项目名称：quickstart-javase 
 * 文件名：FileUtils.java
 * 版本信息：
 * 日期：2017年8月6日
 * Copyright asiainfo Corporation 2017
 * 版权所有 *
 */
package org.quickstart.javase.ssh.ganymed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * FileUtils
 * 
 * @author：youngzil@163.com
 * @2017年8月6日 上午12:23:08
 * @version 2.0
 */
public class FileUtils {

    /**
     * 获取Nginx的配置信息
     * 
     * @return ret List<String[]>
     */
    public static List<String[]> getNginxConfig() {
        List<String[]> ret = new ArrayList<String[]>();
        Properties p = new Properties();
        try {
            p.load(FileUtils.class.getResourceAsStream("/com/xx/util/common.properties"));
            String userName = p.getProperty("nginx_username", "root").trim();
            String password = p.getProperty("nginx_password", "root").trim();
            String serverIp = p.getProperty("nginx_serverIp", "172.18.121.72").trim();
            String serverPath = p.getProperty("nginx_serverPath", "/root/nginx").trim();
            if (-1 != userName.indexOf(',')) {
                String[] userNames = userName.split(",");
                String[] passwords = password.split(",");
                String[] serverIps = serverIp.split(",");
                String[] serverPaths = serverPath.split(",");
                for (int i = 0; i < userNames.length; i++) {
                    String[] data = new String[4];
                    data[0] = userNames[i];
                    data[1] = passwords[i];
                    data[2] = serverIps[i];
                    data[3] = serverPaths[i];
                    ret.add(data);
                }
            } else {
                String[] data = new String[4];
                data[0] = userName;
                data[1] = password;
                data[2] = serverIp;
                data[3] = serverPath;
                ret.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
