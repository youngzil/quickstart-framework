package org.quickstart.javase.utils.ip;

/**
 * <p>描述: [功能描述] </p >
 *
 * @author yangzl
 * @date 2020/8/25 09:58
 * @version v1.0
 */

import sun.net.util.IPAddressUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JudgeIpAddress {
    //标准IPv4地址的正则表达式：
    private static final Pattern IPV4_REGEX =
        Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    //无全0块，标准IPv6地址的正则表达式
    private static final Pattern IPV6_STD_REGEX = Pattern.compile("^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    //压缩正则表达式
    private static final Pattern IPV6_COMPRESS_REGEX =
        Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?)$");

    //判断是否为合法IPv4地址
    public static boolean isIPv4Address(final String input) {
        return IPV4_REGEX.matcher(input).matches();
    }

    //以下为测试代码
    //判断是否为合法IPv6地址
    public static Map isIPv6Address(String input) {
        Map resultMap = new HashMap();
        // 8*4 位数字，加上七个: ipv6地址长度不可能超过39位
        if (input.length() > 39) {//这里娶个巧，因为当压缩发生在ip地址的中间时，左右两边的非压缩段数尤其不好估计，
            resultMap.put("value", "false");     //起码几十种，不可能为了它写几十个正则吧？有便宜干嘛不占呢，嘿嘿
            resultMap.put("method", "长度非法");
        } else if (IPV6_STD_REGEX.matcher(input).matches()) {//标准格式判断
            resultMap.put("value", "true");
            resultMap.put("method", "标准格式");
        } else if (IPV6_COMPRESS_REGEX.matcher(input).matches()) {//压缩发生在IP地址内部；唯一确定内容，IP地址首尾无冒号 (:)
            resultMap.put("value", "true");                     //压缩位置未知，压缩的连续全0块数量未知
            resultMap.put("method", "压缩格式");
        } else {
            resultMap.put("value", "false");
            resultMap.put("method", "匹配错误");
        }
        return resultMap;
    }

    public static void main(String args[]) {
        String JudegeType = "IPv6";
        if (JudegeType.equals("IPv6")) {
            String[] ipAddrs = getParams();
            for (String ipAddr : ipAddrs) {
                Map result = isIPv6Address(ipAddr);
                boolean selfMethod = Boolean.valueOf(result.get("value").toString());
                //官方判断IPv6地址方法
                boolean sunMethod = IPAddressUtil.isIPv6LiteralAddress(ipAddr);
                if (selfMethod == sunMethod) {
                    System.out.print("##############自定义正则匹配结果与官方结果相同----判断一致！");
                } else {
                    System.out.print("##############自定义正则匹配结果与官方结果相同----正则错误！");
                }
                System.out.print("  " + result.get("method"));
                System.out.print("  IPAddressUtil:" + sunMethod);
                if (selfMethod) {
                    System.out.print("  IPv6合法");
                } else {
                    System.out.print("  IPv6非法");
                }
                System.out.println("  " + ipAddr);
            }
        } else if (JudegeType.equals("IPv4")) {
            String[] examples = {"192.168.1.1", "192.0.1.1", "999.168.1.1", "192.168.1.1.0", "192.168.256.1",};
            for (String example : examples) {
                System.out.print("IPAddressUtil:" + IPAddressUtil.isIPv4LiteralAddress(example));
                System.out.print("  selfMethod:" + isIPv4Address(example));
                if (IPAddressUtil.isIPv4LiteralAddress(example) == isIPv4Address(example)) {
                    System.out.print("自定义正则判断无误!");
                } else {
                    System.out.print("自定义正则判断失准!");
                }
                System.out.println("  " + example);
            }
        }
    }

    //测试数据
    public static String[] getParams() {
        return new String[] {"::", "a:b:c:D:e:f:f:F", "a:b:c:D:G:f:f:F", "a:b:c:D:g:f:f:F",
            "fe80:1295:8030:49ec::1fc6:57fa:2222:0000", "fe80:1295:8030:49ec:1fc6:57fa:0000:",
            ":1295:8030:49ec:1fc6:57fa:0000:0000", "fe80:1295:8030:1fc6:57fa:0000:0000",
            "fe80:1295:8030:49ec:1fc6:57fa::0000", "fe80::0000", "fe80::", "::0000", "fe80:1295:8030:0000",
            "fe80:1295:8030::0000:0000", "fe80:1295:8030:49ec:1fc6:57fa:::0000:0000:0000:0000:0000:0000:0000:0000",
            "::8030:49ec:1fc6:57fa:0000:0000:0000:0000:0000:0000", "fe80:1295::49ec:1fc6:57fa:0000:0000",
            "fe80:1295:8030:49ec::1fc6:57fa:0000:0000:0000:0000:0000:0000:0000:0000:0000",
            "fe80::1295:8030:49ec:1fc6:57fa:0000:0000:0000",
            "fe80:1295:8030:49ec::1fc6:57fa:0000:0000:0000:0000:0000:0000:0000:0000:0000:0000:0000",
            "fe80:1295:8030:49ec:1fc6::57fa:0000:0000:0000:0000:0000",
            "fe80:1295:8030:49ec:1fc6:57fa::0000:0000:0000:0000:0000",
            "fe80:1295:8030:49ec:1fc6:57fa:0000::0000:0000:0000:0000",
            "fe80:0000:0000:0000:1fc6:57fa::1241:0000:0000:0000:0000:0000:0000",
            "::fe80:1295:8030:49ec:1fc6:57fa:2222:0000", ":1295:8030:49ec:1fc6:57fa:2222:0000",
            "fe80:1295:8030:49ec:1fc6:57fa:2222:0000:", "fe80:1295:8030:49ec:1fc6:57fa:2222:0000::",
            "::fe80:1295:8030:49ec:1fc6:57fa:2222:0000", "fe80:1295:8030:49ec:1fc6:57fa:2222:",
            ":1295:8030:49ec:1fc6:57fa:2222:0000", "fe80:1295:8030:49ec:1fc6:57fa::", "::fe80:1295:8030:49ec:1fc6:57fa",
            "fe80:1295:8030:49ec:1fc6:57fa:a::", "::b:fe80:1295:8030:49ec:1fc6:57fa",
            "fe80:1295:8030:49ec:1fc6:57fa:2222::", "::1295:8030:49ec:1fc6:57fa:2222:0000",
            "::8030:49ec:1fc6:57fa:2222:0000", "fe80:1295:8030:49ec:1fc6:57fa:0000:0000"};
    }
}
