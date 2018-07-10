package com.quickstart.test.session;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;
import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 对memcached的操作
 */
public class SessionService {
    private static SessionService instance = null;
    private SockIOPool pool = null;
    private String poolName = "sidsock";

    private int expiryTime = 60;// minutes

    private Date getExpiryDate() {
        Calendar calendar = Calendar.getInstance();

        long time = calendar.getTimeInMillis();
        time += expiryTime * 60 * 1000;
        calendar.setTimeInMillis(time);

        return calendar.getTime();
    }

    public static synchronized SessionService getInstance() {
        if (instance == null) {
            instance = new SessionService();
        }
        return instance;
    }

    private SessionService() {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream input = cl.getResourceAsStream("memcached.properties");
        Properties props = new Properties();
        String serverlist = "127.0.0.1:11211";
        try {
            props.load(input);
            serverlist = props.getProperty("serverlist", "127.0.0.1:11211");
            poolName = props.getProperty("poolname", "sidsock");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] servers = serverlist.split(",");
        pool = SockIOPool.getInstance(poolName);
        pool.setServers(servers);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setAliveCheck(true);
        pool.initialize();
    }

    public boolean sessionExists(String id) {
        MemCachedClient mc = this.getMemCachedClient();
        return mc.keyExists(id);
    }

    public Map getSession(String id, boolean create) {
        MemCachedClient mc = this.getMemCachedClient();

        Map session = (Map) mc.get(id);
        if (session == null) {
            if (create) {
                session = new HashMap(5);
                mc.add(id, session, getExpiryDate());
            }
        }
        return session;
    }

    public void saveSession(String id, Map session) {
        MemCachedClient mc = this.getMemCachedClient();
        if (mc.keyExists(id)) {
            mc.replace(id, session);
        } else {
            mc.add(id, session);
        }
    }

    public void saveSession(String id, Map session, Date expiryDate) {
        MemCachedClient mc = this.getMemCachedClient();
        if (mc.keyExists(id)) {
            mc.replace(id, session, expiryDate);
        } else {
            mc.add(id, session, expiryDate);
        }
    }

    public void removeSession(String id) {
        MemCachedClient mc = this.getMemCachedClient();
        mc.delete(id);
    }

    public void updateExpiryDate(String id) {
        MemCachedClient mc = this.getMemCachedClient();
        Map session = (Map) mc.get(id);
        if (session != null) {
            mc.replace(id, session, getExpiryDate());
        }
    }

    private MemCachedClient getMemCachedClient() {
        MemCachedClient mc = new MemCachedClient();
        mc.setPoolName(poolName);
        mc.setCompressEnable(false);
        mc.setCompressThreshold(0);
        return mc;
    }

    protected void finalize() {
        if (this.pool != null) {
            this.pool.shutDown();
        }
    }
}
