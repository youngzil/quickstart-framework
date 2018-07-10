package com.quickstart.test.session;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对每次动态页面的请求时，更新会话cookie的有效期
 */
public class SessionCookieFilter extends HttpServlet implements Filter {
    /**
    * 
    */
    private static final long serialVersionUID = -6516046520244652987L;
    private String sessionId = "sid";

    private int expiryDate = 30 * 60;// seconeds

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie cookie = RequestUtils.getCookie(request, sessionId);
        if (cookie != null) {
            String sid = cookie.getValue();
            // 设置cookie的有效期
            RequestUtils.setCookie(request, response, sessionId, sid, expiryDate);
        }
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.sessionId = filterConfig.getInitParameter("sessionId");

        RequestUtils.cookieDomain = filterConfig.getInitParameter("cookieDomain");
        if (RequestUtils.cookieDomain == null) {
            RequestUtils.cookieDomain = "";
        }
        RequestUtils.cookiePath = filterConfig.getInitParameter("cookiePath");
        if (RequestUtils.cookiePath == null || RequestUtils.cookiePath.length() == 0) {
            RequestUtils.cookiePath = "/";
        }
    }
}
