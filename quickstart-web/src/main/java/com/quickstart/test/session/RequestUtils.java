package com.quickstart.test.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对cookie读写
 */
public class RequestUtils {
    public static String cookieDomain = "";
    public static String cookiePath = "/";

    /**
     * 获取COOKIE
     * 
     * @param request
     * @param name
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }

    /**
     * 设置COOKIE
     * 
     * @param request
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        if (cookieDomain != null && cookieDomain.indexOf('.') != -1) {
            cookie.setDomain('.' + cookieDomain);
        }
        cookie.setPath(cookiePath);
        response.addCookie(cookie);
    }
}
