package com.quickstart.test.memcached;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

public class TestSpymemcached extends Thread {

    /**
     * @param args
     */
    private int count;

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("begin:" + System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            TestJavaClient test = new TestJavaClient(i);
            test.start();
        }
        System.out.println("end:" + System.currentTimeMillis());
    }

    public TestJavaClient(int i) {
		count = i;
	}

    public void run() {
        System.out.println(count + "start:" + System.currentTimeMillis());
        MemcachedClient c = MemCachedManager.getInstance();
        for (int i = 0; i < 1000; i++) {
            // Store a value (async) for one hour
            c.set(count + "000" + i, 3600, "Hello World " + count + "000" + i + "!");
            // Retrieve a value (synchronously).
            Object myObject = c.get("liusong" + count);

        }
        System.out.println(count + "end:" + System.currentTimeMillis());

    }

}


class MemCachedManager {
    private static MemcachedClient c;

    public static synchronized MemcachedClient getInstance() {
        if (c == null) {
            try {
                c = new MemcachedClient(AddrUtil.getAddresses("10.148.11.112:11211"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return c;
    }
}
