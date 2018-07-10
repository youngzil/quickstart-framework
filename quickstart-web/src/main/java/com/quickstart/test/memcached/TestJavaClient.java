package com.quickstart.test.memcached;

import java.util.Date;

import com.quickstart.web.meetup.memcached.MemcachedClient;
import com.quickstart.web.meetup.memcached.SockIOPool;

public class TestJavaClient extends Thread {
    private int count;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("begin:" + System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            TestDangaClient test = new TestDangaClient(i);
            test.start();
        }
        System.out.println("end:" + System.currentTimeMillis());
    }

    public TestDangaClient(int i) {
		count = i;
	}

    public void run() {
        System.out.println(count + "start:" + System.currentTimeMillis());

        MemCachedManage cache = MemCachedManage.getInstance();

        for (int i = 0; i < 1000; i++) {
            // Store a value (async) for one hour

            cache.add(count + "000" + i, "Hello World " + count + "000" + i + "!");
            // Retrieve a value (synchronously).
            Object myObject = cache.get("liusong" + count);

        }
        System.out.println(count + "end:" + System.currentTimeMillis());

    }
}


class MemCachedManage {

    private static MemcachedClient mcc = new MemcachedClient();

    private static MemCachedManage memCachedManager = new MemCachedManage();

    static {

        String[] servers = {"10.148.71.215:11211"};
        Integer[] weights = {3};

        SockIOPool pool = SockIOPool.getInstance();

        pool.setServers(servers);
        pool.setWeights(weights);

        pool.setInitConn(100);
        pool.setMinConn(100);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        pool.setMaintSleep(30);

        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);

        pool.initialize();

        mcc.setCompressEnable(true);
        mcc.setCompressThreshold(64 * 1024);
    }

    protected MemCachedManage() {

    }

    public static MemCachedManage getInstance() {
        return memCachedManager;
    }

    public boolean add(String key, Object value) {
        return mcc.add(key, value);
    }

    public boolean add(String key, Object value, Date expiry) {
        return mcc.add(key, value, expiry);
    }

    public boolean replace(String key, Object value) {
        return mcc.replace(key, value);
    }

    public boolean replace(String key, Object value, Date expiry) {
        return mcc.replace(key, value, expiry);
    }

    public Object get(String key) {
        return mcc.get(key);
    }

    public static void main(String[] args) {
        MemCachedManage cache = MemCachedManage.getInstance();
        cache.add("hello", 234);
        System.out.print("get value : " + cache.get("hello"));
    }
}
