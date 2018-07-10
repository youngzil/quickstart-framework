package com.quickstart.test.session;

import java.io.Serializable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quickstart.web.mem.session.MemSession;
import com.quickstart.web.util.RequestUtils;

public class SessionUtil {
    public static UserInfo getUserInfo(HttpServletRequest request) {
        Cookie cookie = RequestUtils.getCookie(request, "sid");

        if (cookie != null) {
            String sid = cookie.getValue();
            MemSession session = MemSession.getSession(sid);
            UserInfo userInfo = (UserInfo) session.getAttribute("user_session_flag");

            if (userInfo == null) {
                // 缓存中已经不存在，则要从数据库中读取，同时存放在缓存中
                userInfo = new UserInfo("hpt321", 1, "hpt321");// 要从数据库中读取
                session.setAttribute("user_session_flag", userInfo);

                return userInfo;
            }

        }
        return null;
    }

    public static void setUserInfo(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        setAttribute(request, "user_session_flag", userInfo);
    }

    /**
     * 登录返回true;未登录返回false;
     * 
     * @param request
     * @return
     */
    public static boolean valideLogin(HttpServletRequest request) {
        Cookie cookie = RequestUtils.getCookie(request, "sid");
        if (cookie == null) {
            return false;
        } else {
            return true;
        }
    }

    public static void setAttribute(String sid, String key, Object value) {
        // 把置放入缓存中
        MemSession session = MemSession.getSession(sid);
        session.setAttribute(key, value);
    }

    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        Cookie cookie = RequestUtils.getCookie(request, "sid");
        if (cookie != null) {
            // 取出session的ID
            String sid = cookie.getValue();
            // 把置放入缓存中
            MemSession session = MemSession.getSession(sid);
            session.setAttribute(key, value);
        }
    }

    public static Object getAttribute(String sid, String key) {
        // 把置放入缓存中
        MemSession session = MemSession.getSession(sid);
        return session.getAttribute(key);
    }

    public static Object getAttribute(HttpServletRequest request, String key) {
        Cookie cookie = RequestUtils.getCookie(request, "sid");

        if (cookie != null) {
            // 取出session的ID
            String sid = cookie.getValue();
            // 把置放入缓存中
            MemSession session = MemSession.getSession(sid);
            return session.getAttribute(key);
        }
        return null;
    }

    public static void removeAttribute(HttpServletRequest request, String key) {
        Cookie cookie = RequestUtils.getCookie(request, "sid");

        if (cookie != null) {
            // 取出session的ID
            String sid = cookie.getValue();
            // 把置放入缓存中
            MemSession session = MemSession.getSession(sid);
            session.removeAttribute(key);
        }

    }

    public static void invalidate(HttpServletRequest request) {
        Cookie cookie = RequestUtils.getCookie(request, "sid");

        if (cookie != null) {
            // 取出session的ID
            String sid = cookie.getValue();
            // 把置放入缓存中
            MemSession session = MemSession.getSession(sid);
            session.invalidate();
        }
    }

}
