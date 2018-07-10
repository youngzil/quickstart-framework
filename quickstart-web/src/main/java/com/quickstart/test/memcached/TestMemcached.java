package com.quickstart.test.memcached;

import com.alisoft.xplatform.asf.cache.memcached.client.MemCachedClient;
import com.alisoft.xplatform.asf.cache.memcached.client.SockIOPool;

public class TestMemcached {

    public static void main(String[] args) {
        String[] server = {"192.168.83.1:12345"};
        // 初始化SockIOPool,管理memcached连接池
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(server);

        pool.setFailover(true);

        pool.setInitConn(10);

        pool.setMinConn(5);

        pool.setMaxConn(100);

        pool.setMaintSleep(30);

        pool.setNagle(false);

        pool.setSocketTO(3000);

        pool.setAliveCheck(true);

        pool.initialize();

        // 建立memcachedclient 对象
        MemCachedClient client = new MemCachedClient();

        for (int i = 0; i < 100; i++) {
            /* 将对象加入到memcached缓存 */
            boolean success = client.set("" + i, "Hello!");
            /* 从memcached缓存中按key值取对象 */
            String result = (String) client.get("" + i);
            System.out.println(String.format("set( %d ): %s", i, success));
            System.out.println(String.format("get( %d ): %s", i, result));

        }

        for (int i = 0; i < 10; i++) {
            client.delete("" + i);
        }

    }
}
