/**
 * 项目名称：quickstart-javase 
 * 文件名：LoginFilter.java
 * 版本信息：
 * 日期：2018年6月29日
 * Copyright asiainfo Corporation 2018
 * 版权所有 *
 */
package org.quickstart.javase.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * LoginFilter
 * 
 * @author：youngzil@163.com
 * @2018年6月29日 下午3:36:35
 * @since 1.0
 */
@WebFilter(filterName = "loginFilter", urlPatterns = "/*",
        initParams = {@WebInitParam(name = "loginUI", value = "/home/loginUI"), @WebInitParam(name = "loginProcess", value = "home/login"), @WebInitParam(name = "encoding", value = "utf-8")})
public class LoginFilter implements Filter {
    private FilterConfig config;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        // 获取配置参数
        String loginUI = config.getInitParameter("loginUI");
        String loginProcess = config.getInitParameter("loginProcess");
        String encoding = config.getInitParameter("encoding");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // 设置请求的字符集（post请求方式有效）
        request.setCharacterEncoding(encoding);

        // 不带http://域名:端口的地址
        String uri = request.getRequestURI();
        if (uri.contains(loginUI) || uri.contains(loginProcess)) {
            // 请求的登录，放行
            chain.doFilter(request, response);
        } else {
            if (request.getSession().getAttribute("user") == null) {
                // 重定向到登录页面
                response.sendRedirect(request.getContextPath() + loginUI);
            } else {
                // 已经登录，放行
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        this.config = null;
    }

}
