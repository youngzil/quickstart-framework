package com.quickstart.test.session;

import java.util.Map;

/**
 * 对会话的操作
 */
public class MemSession {
    // 会话ID
    private String sid = "";
    // 存放本会话的所有信息
    private Map map = null;

    public static MemSession getSession(String sid) {
        MemSession session = null;

        session = new MemSession(sid, true);

        return session;
    }

    public static MemSession getSession(String sid, boolean create) {
        MemSession session = null;

        session = new MemSession(sid, create);

        return session;
    }

    private MemSession(String sid, boolean create) {
        this.sid = sid;
        this.map = SessionService.getInstance().getSession(sid, create);
    }

    public static boolean sessionExists(String sid) {
        return SessionService.getInstance().sessionExists(sid);
    }

    public Object getAttribute(String arg0) {
        return this.map.get(arg0);
    }

    public void invalidate() {
        this.map.clear();
        SessionService.getInstance().removeSession(this.sid);
    }

    public void removeAttribute(String arg0) {
        if (arg0 == null || arg0.trim().length() <= 0) {
            return;
        }
        this.map.remove(arg0);
        SessionService.getInstance().saveSession(this.sid, this.map);
    }

    public void setAttribute(String arg0, Object arg1) {
        if (arg0 == null || arg0.trim().length() <= 0 || arg1 == null) {
            return;
        }
        this.map.put(arg0, arg1);
        SessionService.getInstance().saveSession(this.sid, this.map);
    }

    public void updateExpiryDate(String sid) {
        SessionService.getInstance().updateExpiryDate(sid);
    }

}
