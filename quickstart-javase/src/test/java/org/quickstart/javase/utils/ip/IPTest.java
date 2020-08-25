package org.quickstart.javase.utils.ip;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-11 11:58
 */
public class IPTest {

  public static boolean isInRange(String network, String mask) {
    String[] networkips = network.split("\\.");
    int ipAddr = (Integer.parseInt(networkips[0]) << 24)//
        | (Integer.parseInt(networkips[1]) << 16)//
        | (Integer.parseInt(networkips[2]) << 8)//
        | Integer.parseInt(networkips[3]);
    int type = Integer.parseInt(mask.replaceAll(".*/", ""));
    int mask1 = 0xFFFFFFFF << (32 - type);
    String maskIp = mask.replaceAll("/.*", "");
    String[] maskIps = maskIp.split("\\.");
    int cidrIpAddr = (Integer.parseInt(maskIps[0]) << 24)//
        | (Integer.parseInt(maskIps[1]) << 16)//
        | (Integer.parseInt(maskIps[2]) << 8)//
        | Integer.parseInt(maskIps[3]);

    return (ipAddr & mask1) == (cidrIpAddr & mask1);
  }

  public static boolean ipIsInNet(String iparea, String ip) {
    if (iparea == null)
      throw new NullPointerException("IP段不能为空！");
    if (ip == null)
      throw new NullPointerException("IP不能为空！");
    iparea = iparea.trim();
    ip = ip.trim();
    final String REGX_IP = "((25[0-5]|2[0-4]//d|1//d{2}|[1-9]//d|//d)//.){3}(25[0-5]|2[0-4]//d|1//d{2}|[1-9]//d|//d)";
    final String REGX_IPB = REGX_IP + "//-" + REGX_IP;
    if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP))
      return false;
    int idx = iparea.indexOf('-');
    String[] sips = iparea.substring(0, idx).split("//.");
    String[] sipe = iparea.substring(idx + 1).split("//.");
    String[] sipt = ip.split("//.");
    long ips = 0L, ipe = 0L, ipt = 0L;
    for (int i = 0; i < 4; ++i) {
      ips = ips << 8 | Integer.parseInt(sips[i]);
      ipe = ipe << 8 | Integer.parseInt(sipe[i]);
      ipt = ipt << 8 | Integer.parseInt(sipt[i]);
    }
    if (ips > ipe) {
      long t = ips;
      ips = ipe;
      ipe = t;
    }
    return ips <= ipt && ipt <= ipe;
  }

  public static void main(String[] args) {
    System.out.println(isInRange("10.153.48.127", "10.153.48.0/26"));
    System.out.println(isInRange("10.168.1.2", "10.168.0.224/23"));
    System.out.println(isInRange("192.168.0.1", "192.168.0.0/24"));
    System.out.println(isInRange("10.168.0.0", "10.168.0.0/32"));

    //
    System.out.println(ipIsInNet("10.153.48.127", "10.153.48.0/26"));
    System.out.println(ipIsInNet("10.168.1.2", "10.168.0.224/23"));
    System.out.println(ipIsInNet("192.168.0.1", "192.168.0.0/24"));
    System.out.println(ipIsInNet("10.168.0.0", "10.168.0.0/32"));
  }

}
