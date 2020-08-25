package org.quickstart.javase.utils.ip;

import org.junit.Test;
import sun.net.util.IPAddressUtil;

import java.util.regex.Pattern;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/25 09:48
 * @version v1.0
 */
public class IpTest2 {
    public static void main(String[] args) {
        String ipv4 = "1.1.1.1";
        String ipv6 = "ABCD:EF01:2345:6789:ABCD:EF01:2345:6789";

        boolean iptest1 = ipTest(ipv4);
        System.out.println(iptest1);//true

        boolean iptest2 = ipTest(ipv6);
        System.out.println(iptest2);//true
    }

    public static boolean ipTest(String ipStr) {
        boolean iPv4LiteralAddress = IPAddressUtil.isIPv4LiteralAddress(ipStr);
        boolean iPv6LiteralAddress = IPAddressUtil.isIPv6LiteralAddress(ipStr);
        //ip有可能是v4,也有可能是v6,滿足任何一种都是合法的ip
        if (!(iPv4LiteralAddress || iPv6LiteralAddress)) {
            return false;
        }
        return true;
    }

    @Test
    public void testIp() {

        //IPV6测试
        String ip1 = "240e:c0:f4a0:82dc:e009:b4a8:266b:8e98";
        //ipv4 测试
        String ip2 = "225.54.3.1";
        System.out.println("ip1:" + validIPAddress(ip1));
        System.out.println("ip2:" + validIPAddress(ip2));

    }

    @Test
    public void testIPV4() {

        // ipv6
        Pattern pattern = Pattern.compile(
            "^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$");
        // ipv4
        Pattern ptipv4 = Pattern
            .compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$");

        System.out.println(pattern.matcher("2001:0DB8:0000:0023:0008:0800:200C:417A").matches());
        System.out.println(pattern.matcher("2001:DB8:0:23:8:800:200C:417A").matches());
        System.out.println(pattern.matcher("2001:DB8:0:23:8:800:192.1.0.0").matches());
        System.out.println(pattern.matcher("2001:DB8:0::800:192.1.0.0").matches());
        System.out.println(pattern.matcher("2001:DB8:0:23::192.1.0.0").matches());
        System.out.println(pattern.matcher("::192.1.0.0").matches());
        System.out.println(pattern.matcher("1:af::3").matches());
        System.out.println(pattern.matcher("1:af::").matches());
        System.out.println(pattern.matcher("::1:af:0").matches());
        System.out.println(pattern.matcher("::0").matches());
        System.out.println("---------------------");
        System.out.println(pattern.matcher("+2001:0DB8:0000:0023:0008:0800:200C:417A").matches());
        System.out.println(pattern.matcher("2001:0DB8:0z00:0023:0008:0800:200C:417A").matches());
        System.out.println(pattern.matcher("2001:DB8:0:23:800::192.1.0.0.1").matches());
        System.out.println(pattern.matcher("2001:DB8::23::800:192.1.0.0").matches());
        System.out.println(pattern.matcher(":::").matches());
        System.out.println(pattern.matcher("1:::2").matches());

        System.out.println("---------------------");
        System.out.println(ptipv4.matcher("1.1.0.1").matches());
        System.out.println(ptipv4.matcher("123.1.0.19").matches());
        System.out.println(ptipv4.matcher("255.255.255.255").matches());
        System.out.println(ptipv4.matcher("0.0.0.0").matches());
        System.out.println("---------------------");
        System.out.println(ptipv4.matcher("-1.1.0.1").matches());
        System.out.println(ptipv4.matcher("1.1b.0.1").matches());
        System.out.println(ptipv4.matcher("1.01.0.1").matches());
        System.out.println(ptipv4.matcher("1.1.300.1").matches());
        System.out.println(ptipv4.matcher("1.1..1").matches());

    }

    public static String validIPAddress(String ip) {
        String ipv4 = "^((\\d|[1-9]\\d|1\\d\\d|2([0-4]\\d|5[0-5]))\\.){4}$";
        //8个1-4位+:
        String ipv6 = "^(([\\da-fA-F]{1,4}):){8}$";
        return String.format("%s.", ip).matches(ipv4) ? "IPv4" :
            String.format("%s:", ip).matches(ipv6) ? "IPv6" : "Neither";
    }
}
