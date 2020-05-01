/**
 * 项目名称：quickstart-javase 
 * 文件名：BalanceServer.java
 * 版本信息：
 * 日期：2018年6月10日
 * Copyright yangzl Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.load.balance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * BalanceServer
 * 
 * https://mp.weixin.qq.com/s/8ovz_YjaDDDDirG2AxT1fg
 * https://www.cnblogs.com/xrq730/p/5154340.html
 * 
 * @author：youngzil@163.com
 * @2018年6月10日 下午7:45:37
 * @since 1.0
 */
public class BalanceServer {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(BalanceServer.getRoundRobinServer());// 轮询法
            System.out.println(BalanceServer.getRandomServer());// 随机法
            System.out.println(BalanceServer.getSourceAddressHashServer("192.168.0.1"));// 源地址hash法
            System.out.println(BalanceServer.getWeightRoundRobinServer());// 加权轮询法
            System.out.println(BalanceServer.getWeightRandomServer());// 加权随机法
        }
    }

    // 轮询法
    public static List<String> servers = Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.3", "192.168.0.4", "192.168.0.5");
    public static int pos = 0;

    public static String getRoundRobinServer() {
        String server = null;
        if (pos >= servers.size()) {
            pos = 0;
        }
        server = servers.get(pos);
        pos++;
        return server;
    }

    // 随机法
    public static String getRandomServer() {
        String server = null;
        Random random = new Random();
        int randomPos = random.nextInt(servers.size());
        server = servers.get(randomPos);
        return server;
    }

    // 源地址hash法的思想是获取客户端访问的ip地址,通过hash函数计算出一个hash值,用该hash值对服务器列表的大小进行取模运算,得到的值就是要访问的服务器的序号
    // hash法的好处是,在服务器列表不变的情况下,每次客户端访问的服务器都是同一个服务器.利用这个特性可以有状态的session会话.无需额外的操作就可以实现粘性会话.
    public static String getSourceAddressHashServer(String ip) {
        String server = null;
        int hashCode = ip.hashCode();
        pos = hashCode % servers.size();
        server = servers.get(pos);
        return server;
    }

    public static Map<String, Integer> serverMap = new HashMap<String, Integer>();
    static {
        serverMap.put("192.168.0.1", 1);
        serverMap.put("192.168.0.2", 1);
        serverMap.put("192.168.0.3", 4);
        serverMap.put("192.168.0.4", 3);
        serverMap.put("192.168.0.5", 3);
        serverMap.put("192.168.0.6", 2);
    }

    // 加权轮询法
    public static String getWeightRoundRobinServer() {
        Set<String> keySet = serverMap.keySet();
        Iterator<String> it = keySet.iterator();
        List<String> servers = new ArrayList<String>();
        while (it.hasNext()) {
            String server = it.next();
            Integer weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                servers.add(server);
            }
        }
        String server = null;
        if (pos >= servers.size()) {
            pos = 0;
        }
        server = servers.get(pos);
        pos++;
        return server;
    }

    // 加权随机法
    public static String getWeightRandomServer() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(serverMap);
        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> serverList = new ArrayList<String>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++)
                serverList.add(server);
        }
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());
        return serverList.get(randomPos);
    }

}
