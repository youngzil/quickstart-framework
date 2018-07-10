package com.quickstart.test.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;

public class TestSpyMemcache {

    public static void main(String[] args) {
        // 保存对象
        try {

            /* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */

            MemcachedClient mc = new MemcachedClient(new InetSocketAddress("10.11.15.222", 10000));

            Future<Boolean> b = null;
            /* 将key值，过期时间(秒)和要缓存的对象set到memcached中 */
            b = mc.set("neea:testDaF:ksIdno", 900, "someObject");
            if (b.get().booleanValue() == true) {
                mc.shutdown();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 取得对象
        try {
            /* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */
            MemcachedClient mc = new MemcachedClient(new InetSocketAddress("10.11.15.222", 10000));
            /* 按照key值从memcached中查找缓存，不存在则返回null */
            Object b = mc.get("neea:testDaF:ksIdno");
            System.out.println(b.toString());
            mc.shutdown();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
