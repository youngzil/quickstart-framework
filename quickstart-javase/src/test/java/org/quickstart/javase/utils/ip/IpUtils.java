package org.quickstart.javase.utils.ip;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/17 23:23
 * @version v1.0
 */
public class IpUtils {

    public static String getIpAddr(HttpServletRequest httpServletRequest) {
        String ipAddress;
        try {
            ipAddress = httpServletRequest.getHeader("x-forwarded-for");
            if (StringUtils.isBlank(ipAddress) || StringUtils.equalsIgnoreCase(ipAddress, "unknown")) {
                ipAddress = httpServletRequest.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipAddress) || StringUtils.equalsIgnoreCase(ipAddress, "unknown")) {
                ipAddress = httpServletRequest.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isBlank(ipAddress) || StringUtils.equalsIgnoreCase(ipAddress, "unknown")) {
                ipAddress = httpServletRequest.getRemoteAddr();
                if (StringUtils.equals(ipAddress, "127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    try {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (StringUtils.length(ipAddress) > 15) { // "***.***.***.***".length()
                // = 15
                ipAddress = StringUtils.substringBefore(ipAddress, ",");
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}
